class MonThread extends Thread {
    private String nom;

    public MonThread(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(nom + " - étape " + i);
            try {
                Thread.sleep(1000); // Pause d'1 seconde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ExempleThread {
    public static void main(String[] args) {
        MonThread t1 = new MonThread("Thread-1");
        MonThread t2 = new MonThread("Thread-2");

        t1.start(); // Démarre l'exécution parallèle
        t2.start();

        try {
            t1.join(); // Attente de fin d'éxécution
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
