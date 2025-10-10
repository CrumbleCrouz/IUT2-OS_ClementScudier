public class ProblemeVisibilite {
    private static boolean arret = false;

    public static void main(String[] args) throws InterruptedException {
        Thread travailleur = new Thread(() -> {
            while (!arret) {
                // Travail...
            }
            System.out.println("Thread arrêté");
        });

        travailleur.start();
        Thread.sleep(2000);

        arret = true; // Peut ne pas être visible immédiatement !
        System.out.println("Demande d'arrêt envoyée");
    }
}