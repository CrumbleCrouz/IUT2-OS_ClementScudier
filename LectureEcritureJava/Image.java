import java.io.*;
import java.util.Scanner;

public class Image {
    private int width;
    private int height;
    // pixels[y][x][0=R,1=G,2=B]
    private int[][][] pixels; // pixels[y][x][0=R,1=G,2=B]

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    /**
     * Constructeur : initialise une image vide.
     */
    public Image(int largeur, int hauteur) {
        this.width = largeur;
        this.height = hauteur;
        pixels = new int[hauteur][largeur][3];
    }

    /**
     * Définit la couleur d'un pixel à la position (x, y)
     */
    public void setPixel(int x, int y, int r, int g, int b) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixels[y][x][0] = r;
            pixels[y][x][1] = g;
            pixels[y][x][2] = b;
        }
    }

    /**
     * Sauvegarde l'image au format texte PPM (P3)
     */
    public void save(String filename) throws IOException {
        FileWriter writer = new FileWriter("LectureEcritureJava/out/" +  filename);
        writer.write("P3\n");
        writer.write(width + " " + height + "\n");
        writer.write("255\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                writer.write(pixels[y][x][0] + " " + pixels[y][x][1] + " " + pixels[y][x][2]);
                if (x != width - 1) {
                    writer.write(" ");
                }
            }
            writer.write("\n");
        }
        writer.close();
    }

    public static void read_txt(String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        Scanner sc = new Scanner(fileInputStream);
        String line;
        String[] header = new String[3];
        String[] data = {}; // Compilator validator
        int i = 0;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            System.out.println(line);
            if (i < 3) {
                header[i] = line;
            } else {
                if (i == 3) {
                    data = new String[Integer.parseInt(header[1].split(" ")[1])];
                }
                data[i - 3] = line;
            }
            i++;
        }
    }
}