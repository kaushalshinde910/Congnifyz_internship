import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        // Use try-with-resources to handle Scanner automatically
        try (Scanner scanner = new Scanner(System.in)) {

            // Get user input
            System.out.print("Enter temperature value: ");
            double temperature = scanner.nextDouble();

            System.out.print("Enter unit (C for Celsius, F for Fahrenheit): ");
            char unit = scanner.next().toUpperCase().charAt(0);

            double convertedTemp;
            
            // Use switch case instead of if-else
            switch (unit) {
                case 'C' -> {
                    convertedTemp = (temperature * 9 / 5) + 32;
                    System.out.println("Temperature in Fahrenheit: " + convertedTemp + "°F");
                }
                case 'F' -> {
                    convertedTemp = (temperature - 32) * 5 / 9;
                    System.out.println("Temperature in Celsius: " + convertedTemp + "°C");
                }
                default -> System.out.println("Invalid unit entered! Please enter 'C' or 'F'.");
            }
        }
    }
}
