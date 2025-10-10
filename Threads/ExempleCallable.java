import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MaTache2 implements Callable<String> {
    private String nom;

    public MaTache2(String nom) {
        this.nom = nom;
    }

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println(nom + " - étape " + i);
            try {
                Thread.sleep(1000); // simule un travail
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return nom + " terminée !";
    }
}

public class ExempleCallable {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // On soumet deux tâches
        Future<String> f1 = executor.submit(new MaTache2("Tâche-1"));
        Future<String> f2 = executor.submit(new MaTache2("Tâche-2"));

        try {
            // Récupération des résultats (bloque jusqu’à la fin)
            String resultat1 = f1.get();
            String resultat2 = f2.get();

            System.out.println(resultat1);
            System.out.println(resultat2);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown(); // libère les ressources
        System.out.println("Toutes les tâches terminées");
    }
}
