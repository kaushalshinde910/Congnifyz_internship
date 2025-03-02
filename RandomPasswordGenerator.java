import java.security.SecureRandom;
import java.util.Scanner;

public class RandomPasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the desired length of the password: ");
        int length = scanner.nextInt();
        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLower = scanner.next().equalsIgnoreCase("y");
        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUpper = scanner.next().equalsIgnoreCase("y");
        System.out.print("Include numbers? (y/n): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("y");
        System.out.print("Include special characters? (y/n): ");
        boolean includeSpecial = scanner.next().equalsIgnoreCase("y");

        String password = generatePassword(length, includeLower, includeUpper, includeNumbers, includeSpecial);
        System.out.println("Generated Password: " + password);

        scanner.close();
    }

    private static String generatePassword(int length, boolean lower, boolean upper, boolean numbers, boolean special) {
        StringBuilder password = new StringBuilder(length);
        String allowedChars = "";
        SecureRandom random = new SecureRandom();

        if (lower) allowedChars += LOWERCASE;
        if (upper) allowedChars += UPPERCASE;
        if (numbers) allowedChars += NUMBERS;
        if (special) allowedChars += SPECIAL_CHARACTERS;

        if (allowedChars.isEmpty()) {
            System.out.println("No character types selected. Password cannot be generated.");
            return "";
        }

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }

        return password.toString();
    }
}
