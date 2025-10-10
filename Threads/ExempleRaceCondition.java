class CompteurDangereux {
    private int count = 0;

    public void incrementer() {
        count++; // Opération NON atomique !
    }

    public int getCount() {
        return count;
    }
}

public class ExempleRaceCondition {
    public static void main(String[] args) throws InterruptedException {
        CompteurDangereux compteur = new CompteurDangereux();

        Runnable tache = () -> {
            for (int i = 0; i < 1000; i++) {
                compteur.incrementer();
            }
        };

        Thread t1 = new Thread(tache);
        Thread t2 = new Thread(tache);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Résultat attendu : 2000
        // Résultat réel : souvent < 2000 !
        System.out.println("Count final : " + compteur.getCount());
    }
}
