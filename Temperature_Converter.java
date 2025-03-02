import java.util.Scanner;

public class Temperature_Converter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the temperature value: ");
        double temperature = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the unit (C for Celsius, F for Fahrenheit): ");
        String unitInput = scanner.nextLine().trim().toUpperCase();

        if (unitInput.equals("C")) {
            double fahrenheit = (temperature * 9/5) + 32;
            System.out.printf("%.2f°C is %.2f°F%n", temperature, fahrenheit);
        } else if (unitInput.equals("F")) {
            double celsius = (temperature - 32) * 5/9;
            System.out.printf("%.2f°F is %.2f°C%n", temperature, celsius);
        } else {
            System.out.println("Invalid unit entered. Please enter C or F.");
        }

        scanner.close();
    }
}
