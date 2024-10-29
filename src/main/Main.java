package main;
import java.io.IOException;

public class Main {
    static {
        try {
            System.loadLibrary("libUTP_Project");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native library: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Window window = new Window();
        } catch (IOException e) {
            System.err.println("IOException encountered: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
