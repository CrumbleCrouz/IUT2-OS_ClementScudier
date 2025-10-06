import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ProcessController pc = new ProcessController();

        pc.executeSimple("dir", new String[]{" S:"});
    }
}
