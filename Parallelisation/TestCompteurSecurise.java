public class TestCompteurSecurise {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Compteur Dangereux ===");

        long debut = System.nanoTime();

        Thread[] threads = new Thread[9];

        for (int i = 0; i < threads.length; i++) {

            // Calcul intensif simulé par des opérations mathématiques
            threads[i] = new Thread(new CompteurSecurise.Incrementeur("compteur " + i, 1111111));
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        long duree = (System.nanoTime() - debut) / 1_000_000;
        System.out.println("Résultat total : " + CompteurSecurise.getCompteur());
        System.out.println("Durée : " + duree + " ms");
    }
}
