import java.util.concurrent.Semaphore;

public class Consommateur implements Runnable {

    private final Integer[] buffer;
    private final Semaphore elementsDisponibles;
    private final Semaphore placesLibres;
    private final Semaphore mutexBuffer;
    private final int tailleBuffer;

    public Consommateur(Integer[] buffer, Semaphore elementsDisponibles,
                        Semaphore placesLibres, Semaphore mutexBuffer, int tailleBuffer) {
        this.buffer = buffer;
        this.elementsDisponibles = elementsDisponibles;
        this.placesLibres = placesLibres;
        this.mutexBuffer = mutexBuffer;
        this.tailleBuffer = tailleBuffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {  // Comme indiqué dans l'exercice
            try {
                // Attendre qu'un élément soit disponible
                elementsDisponibles.acquire();

                // Acquérir l'accès exclusif au buffer
                mutexBuffer.acquire();

                // Retirer un élément du buffer
                int p = buffer.length - placesLibres.availablePermits() - 1;
                System.out.println("Produit consommé : " + buffer[p] + " à i=" + i + " et j=" + p);
                buffer[p] = null;

                // Libérer l'accès au buffer
                mutexBuffer.release();

                // Signaler qu'une place est libre
                placesLibres.release();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
