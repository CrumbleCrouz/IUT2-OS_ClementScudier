public class TestSemaphore {

    public static void main(String[] args) throws InterruptedException {
        ProducteurConsommateur pc = new ProducteurConsommateur();

        System.out.println("===> Scenario started");
        Thread[] producers = new Thread[3];
        Thread[] consumers = new Thread[4];

        // Create producer threads
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(pc.new Producteur());
            producers[i].start();
        }

        // Create consumer threads
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(new Consommateur(
                    pc.buffer, pc.elementsDisponibles, pc.placesLibres, pc.mutexBuffer, ProducteurConsommateur.TAILLE_BUFFER
            ));
            consumers[i].start();
        }

        // Wait for all threads to finish
        for (Thread t : producers) t.join();
        for (Thread t : consumers) t.join();

        System.out.println("===> Scenario ended");
    }
}
