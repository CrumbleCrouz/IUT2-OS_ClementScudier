public class CompteurSecurise {
    private static final Object verrou = new Object();
    private static int compteurGlobal;

    static class Incrementeur implements Runnable {
        private final String nom;
        private final int nombreIncrements;

        public Incrementeur(String nom, int nombreIncrements) {
            this.nom = nom;
            this.nombreIncrements = nombreIncrements;
        }

        @Override
        public void run() {
            synchronized(verrou) {
                // Placer ici l'opération critique
                for (int i = 0; i < nombreIncrements; i++) {
                    // TODO: Expliquer pourquoi cette ligne pose problème
                    compteurGlobal++;  // RACE CONDITION ICI !
                }
            }
            System.out.println(nom + " terminé. Compteur vu : " + compteurGlobal);
        }
    }



    public static int getCompteur() {
        synchronized(verrou) {
            return compteurGlobal;
        }
    }

    public static void resetCompteur() {
        synchronized(verrou) {
            compteurGlobal = 0;
        }
    }
}
