import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessController pc = new ProcessController();

        System.out.println("=== Étape 1 : Lancement simple");
        Process p1 = pc.executeSimple("cmd", new String[]{"/c", "echo Hello World"});
        int code1 = pc.waitForProcess(p1, 5);
        System.out.println("Code de sortie (simple) : " + code1);
        System.out.println("Sortie : " + pc.readOutput(p1));

        System.out.println("\n=== Étape 2 : Redirection des flux");
        File output = new File("ProcessManager/out/output.txt");
        File error = new File("ProcessManager/out/error.txt");

        Process p2 = pc.executeWithRedirection("cmd", output, error, new String[]{"/c", "dir"});
        int code2 = pc.waitForProcess(p2, 10);
        System.out.println("Code de sortie (redirection) : " + code2);
        System.out.println("Résultats enregistrés dans : " + output.getAbsolutePath());

        Process p3 = pc.executeWithRedirection("cmd", output, error, new String[]{"/c", "commande_inexistante"});
        int code3 = pc.waitForProcess(p3, 10);
        System.out.println("Code de sortie (erreur redirigée) : " + code3);
        System.out.println("Erreur enregistrée dans : " + error.getAbsolutePath());

        System.out.println("\n=== Étape 3 : Processus interactif");
        Process p4 = pc.executeInteractive("C:/python39-32/python.exe", new String[]{""});
        pc.sendInput(p4, "print(\"echo Ceci est un test interactif\")");
        System.out.println("Sortie interactive : " + pc.readOutput(p4));
        pc.sendInput(p4, "exit()");
//        Thread.sleep(5000);
        pc.waitForProcess(p4, 5);

        System.out.println("\n=== Étape 4 : Test de timeout");
        Process p5 = pc.executeSimple("cmd", new String[]{"/c", "timeout 10"});
        int code5 = pc.waitForProcess(p5, 2);
        System.out.println("Code de sortie (timeout attendu) : " + code5);

    }
}
