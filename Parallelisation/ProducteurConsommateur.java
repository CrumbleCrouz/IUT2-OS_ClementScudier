import java.util.concurrent.Semaphore;

public class ProducteurConsommateur {
    public static final int TAILLE_BUFFER = 5;
    public final Integer[] buffer = new Integer[TAILLE_BUFFER];


    public final Semaphore placesLibres = new Semaphore(TAILLE_BUFFER);
    public final Semaphore elementsDisponibles = new Semaphore(0);
    public final Semaphore mutexBuffer = new Semaphore(1);

    public class Producteur implements Runnable {

        private int genererProduit() {
            return (int) (Math.random() * 100);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                int produit = genererProduit();

                try {
                    // Attendre une place libre
                    placesLibres.acquire();

                    // Accéder au buffer en exclusif
                    mutexBuffer.acquire();

                    // Ajouter l'élément au buffer
                    int p = buffer.length - placesLibres.availablePermits() - 1;
                    System.out.println("Produit ajouté : " + produit + " à i=" + i + " et j=" + p);
                    buffer[p] = produit;

                    // Libérer le mutex
                    mutexBuffer.release();

                    // Signaler qu'un élément est disponible
                    elementsDisponibles.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
