import java.util.*;

public class VirtualFileSystem {
    private MemoryManager memoryManager;

    public VirtualFileSystem() {
        this.memoryManager = new MemoryManager();
    }

    // TROUVER UN INODE LIBRE
    private int allocateInode() {
        byte[] memory = memoryManager.getFilesystemMemory();
        // TODO: Complétez cette méthode étape par étape

        // ÉTAPE 1: Parcourir tous les inodes possibles (0 à MAX_INODES)
        // ÉTAPE 2: Pour chaque position, lire le numéro d'inode stocké
        // ÉTAPE 3: Si le numéro ne correspond pas à la position, l'inode est libre
        // ÉTAPE 4: Retourner le numéro de l'inode libre trouvé

        /* AIDE: Structure de base
        for (int i = 0; i < MemoryManager.MAX_INODES; i++) {
            int offset = 0; // MemoryManager.INODE_TABLE_OFFSET  Inode.INODE_SIZE;
            int storedInodeNumber = 0;

            if (storedInodeNumber != i) {
                System.out.println("Inode libre trouvé: " + i);
                return i;
            }
        }
        */

        return -1; // Aucun inode libre
    }

    public boolean createFile(String directory, String filename) {
        System.out.println("\n=== Création du fichier: " + filename + " ===");

        // ÉTAPE 1: Trouver un inode libre
        int inodeNum = allocateInode();
        if (inodeNum == -1) {
            System.err.println("Impossible de créer " + filename + " - Plus d'inodes !");
            return false;
        }

        // ÉTAPE 2: Créer l'objet inode
        Inode inode = new Inode(memoryManager, inodeNum);
        long now = System.currentTimeMillis();

        // ÉTAPE 3: Initialiser l'inode avec des valeurs par défaut
        int[] emptyPointers = new int[Inode.DIRECT_POINTERS]; // Tous à 0

        // TODO: Complétez cet appel !
        // inode.writeToMemory(  ....  );

        System.out.println("Fichier " + filename + " créé avec l'inode " + inodeNum);
        return true;
    }

    // 📋 LISTER TOUS LES FICHIERS (= nos inodes utilisés)
    public List<Integer> getRootDirectory() {
        byte[] memory = memoryManager.getFilesystemMemory();
        List<Integer> usedInodes = new ArrayList<>();

        // TODO: Complétez cette méthode !
        // Parcourir tous les inodes et ajouter ceux qui sont utilisés à la liste

        /* AIDE:
        for (int i = 0; i < MemoryManager.MAX_INODES; i++) {
            int offset = 0; // MemoryManager.INODE_TABLE_OFFSET Inode.INODE_SIZE
            int storedInodeNumber = Utils.readInt(memory, offset);

            // Si le numéro correspond à la position, l'inode est utilisé
            if (storedInodeNumber == i) {
                usedInodes.add(i);
            }
        }
        */

        System.out.println("" + usedInodes.size() + " fichier(s) trouvé(s)");
        return usedInodes;
    }

    // TROUVER UN FICHIER PAR "NOM" (version ultra-simplifiée)
    private int findInodeByName(String directory, String filename) {
        System.out.println("🔍 Recherche du fichier: " + filename);

        // On prend le dernier inode créé (pour les tests)
        List<Integer> inodes = getRootDirectory();

        if (!inodes.isEmpty()) {
            int lastInode = inodes.get(inodes.size() - 1);
            System.out.println("Fichier trouvé à l'inode: " + lastInode);
            return lastInode;
        }

        System.err.println("Fichier " + filename + " non trouvé");
        return -1;
    }

    // MÉTHODES UTILITAIRES
    public MemoryManager getMemoryManager() {
        return memoryManager;
    }

    public int getUsedInodesCount() {
        return getRootDirectory().size();
    }
}
