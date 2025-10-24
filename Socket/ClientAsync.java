import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientAsync {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Veuillez entrer l'ip du serveur :");
                String ip = sc.nextLine();
                System.out.println("Tentative de connection...");
                Socket socket = new Socket(ip, 5000);
                System.out.println("Connecté au serveur" + ip);

                // Thread de lecture (messages du serveur)
                Thread lectureThread = new Thread(new ReceptionHandler(socket));
                lectureThread.start();

                // Thread principal = écriture (envoi au serveur)
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader clavier = new BufferedReader(
                        new InputStreamReader(System.in)
                );

                boolean leaveChat = false;
                String texte;
                while (!leaveChat && (texte = clavier.readLine()) != null) {
                    if (texte.equals("!quit")) {
                        leaveChat = true;
                        continue;
                    }
                    if (texte.equals("!spam")) {
                        for (int i = 0; i < 10; i++) {
                            out.println("N-WORD!");
                        }
                        continue;
                    }
                    out.println(texte);
                }

                socket.close();
            } catch (IOException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }
}

class ReceptionHandler implements Runnable {
    private Socket socket;

    public ReceptionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String ligne;
            while ((ligne = in.readLine()) != null) {
                System.out.println(ligne);
            }

        } catch (IOException e) {
            System.out.println("Connexion perdue : " + e.getMessage());
        }
    }
}