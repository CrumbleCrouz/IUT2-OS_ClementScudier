public class Calcule extends Thread {
    private long resultat = 0;
    private int valeur;
    private int multiplicateur;
    private int index;

    public Calcule(int valeur, int multiplicateur, int index) {
        this.valeur = valeur;
        this.multiplicateur = multiplicateur;
        this.index = index;
    }

    @Override
    public void run() {
        // Calcul intensif
        for (int i = 0; i < multiplicateur; i++) {
            resultat += (long) valeur * valeur + valeur;
        }
        // Même logique que dans la boucle interne de CalculSequentiel
    }

    public long getResultat() {
        return resultat;
    }
}