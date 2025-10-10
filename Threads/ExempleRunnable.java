class MaTache implements Runnable {
    private String nom;

    public MaTache(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(nom + " - étape " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ExempleRunnable {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MaTache("Tâche-1"));
        Thread t2 = new Thread(new MaTache("Tâche-2"));

        t1.start();
        t2.start();

        try {
            t1.join(); // Attend la fin de t1
            t2.join(); // Attend la fin de t2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Toutes les tâches terminées");
    }
}