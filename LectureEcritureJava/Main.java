package LectureEcritureJava;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Image img = new Image(200, 100);
        try {
            Image.read_txt("Z:\\.JetBrains\\IntelIiJIdea\\IUT2-JavaVayssade\\LectureEcritureJava\\out\\FirstPPM.ppm");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}