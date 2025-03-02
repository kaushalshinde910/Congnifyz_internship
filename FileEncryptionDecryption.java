import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileEncryptionDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to (E)ncrypt or (D)ecrypt a file? ");
        char choice = scanner.nextLine().toUpperCase().charAt(0);

        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();
        System.out.print("Enter the output file path: ");
        String outputFilePath = scanner.nextLine();

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String result = (choice == 'E') ? encrypt(content) : decrypt(content);
            try (FileWriter writer = new FileWriter(new File(outputFilePath))) {
                writer.write(result);
            }
            System.out.println("Operation completed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        scanner.close();
    }

    public static String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            encrypted.append((char) (c + 3)); // Simple Caesar Cipher
        }
        return encrypted.toString();
    }

    public static String decrypt(String input) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            decrypted.append((char) (c - 3));
        }
        return decrypted.toString();
    }
}
