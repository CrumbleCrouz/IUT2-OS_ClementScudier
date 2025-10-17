class Consommateur implements Runnable {
    public void run() {
        for (int i = 0; i < 7; i++) {
            // TODO: Attendre qu'un élément soit disponible
            // TODO: Acquérir l'accès exclusif au buffer
            // TODO: Retirer un élément du buffer  
            // TODO: Libérer l'accès au buffer
            // TODO: Signaler qu'une place est libre
        }
    }

    private int genererProduit() {
        // TODO: Générer un nombre avec calcul intensif (pas de Random !)
        // Exemple : somme des carrés de 1 à 1000
        return 1;
    }

    private void traiterProduit(int produit) {
        // TODO: Simulation de traitement avec calcul intensif
        // Exemple : vérifier si le produit est premier
    }
}