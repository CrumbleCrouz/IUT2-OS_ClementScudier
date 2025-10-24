import java.net.*;
import java.io.*;
import java.util.*;

public class ServeurChat {
    // Liste thread-safe des flux de sortie
    private static final List<PrintWriter> clients =
            Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Serveur Chat démarré");

        while (true) {
            Socket client = serverSocket.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            synchronized (clients) {
                clients.add(out);
            }

            System.out.println("Client connecté. Total : " + clients.size());
            new Thread(new ChatHandler(client, out)).start();
        }
    }

    static class ChatHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;

        public ChatHandler(Socket socket, PrintWriter out) {
            this.socket = socket;
            this.out = out;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                String ligne;
                while ((ligne = in.readLine()) != null) {
                    if (!ligne.isBlank()) {
                        System.out.println("Message reçu de " + socket.getInetAddress() + ": " + ligne);
                        broadcast(ligne, socket.getInetAddress().toString());
                    } else {
                        System.out.println("Message vide de :" + socket.getInetAddress());
                    }
                }
            } catch (IOException e) {
                System.err.println("Erreur : " + e.getMessage());
            } finally {
                synchronized (clients) {
                    clients.remove(out);
                }
                try { socket.close(); } catch (IOException ignored) {}
                System.out.println("Client déconnecté. Total : " + clients.size());
            }
        }

        private void broadcast(String message, String ip) {
            List<PrintWriter> snapshot;
            synchronized (clients) {
                snapshot = new ArrayList<>(clients);
            }

            for (PrintWriter writer : snapshot) {
                try {
                    if (writer != out) {  // Exclut l'émetteur
                        writer.println(ip + " >>> " + message);
                    }
                } catch (Exception e) {
                    // Client déconnecté, sera nettoyé par son thread
                }
            }
        }
    }
}
