public class CalculParallele {

    private static final int[] DONNEES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static final int MULTIPLICATEUR = 1000;

    public static void main(String[] args) {
        System.out.println("=== Calcul Parallèle ===");

        long debut = System.nanoTime();
        long sommeTotal = 0;

        Calcule[] threads = new Calcule[DONNEES.length];

        for (int i = 0; i < DONNEES.length; i++) {

            // Calcul intensif simulé par des opérations mathématiques
            threads[i] = new Calcule(DONNEES[i], MULTIPLICATEUR, i);
            threads[i].start();
        }

        for (int i = 0; i < DONNEES.length; i++) {
            long resultat = 0;
            try {
                threads[i].join();
                resultat = threads[i].getResultat();
            } catch (InterruptedException e) {
                System.err.println("[ERROR] " + e.getMessage());
            }
            sommeTotal += resultat;
            System.out.println("Traitement de " + DONNEES[i] + " terminé : " + resultat);

        }

        long duree = (System.nanoTime() - debut) / 1_000_000;
        System.out.println("Résultat total : " + sommeTotal);
        System.out.println("Durée : " + duree + " ms");
    }
}
