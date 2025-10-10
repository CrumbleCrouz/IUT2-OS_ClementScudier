public class LectureConcurrente {
    static int number = 3; // Variable partagée en lecture seule

    static class ThreadLecteur implements Runnable {
        private final String nom;
        private final int operande;

        public ThreadLecteur(String nom, int operande) {
            this.nom = nom;
            this.operande = operande;
        }

        @Override
        public void run() {
            // Variables locales : chaque thread a sa propre pile
            int x = operande;

            // Lecture seule de la variable partagée : THREAD-SAFE
            int resultat = x + number;

            System.out.println(nom + ": " + x + " + " + number + " = " + resultat);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Test de lecture concurrente (Thread-Safe) ===");

        for (int iteration = 1; iteration <= 5; iteration++) {
            System.out.println("\n--- Itération " + iteration + " ---");

            Thread threadA = new Thread(new ThreadLecteur("Thread-A", 5));
            Thread threadB = new Thread(new ThreadLecteur("Thread-B", 2));

            threadA.start();
            threadB.start();

            threadA.join();
            threadB.join();
        }

        System.out.println("\nRésultat : Toujours A=8, B=5 (déterministe)");
        System.out.println("Seul l'ordre d'affichage peut varier");
    }
}
