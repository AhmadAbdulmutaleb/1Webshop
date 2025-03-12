package Java.Login;

import java.sql.SQLException;
import java.util.Scanner;


public class LoginController {

    LoginService loginService;

    // Scanner för användarinput
    Scanner scanner;

    public LoginController() {
        // Skapa instanser av nödvändiga objekt
        this.loginService = new LoginService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            try {
                System.out.println("\n=== Java.Login ===");
                System.out.println("1. Logga in som kund");
                System.out.println("2. Logga in som Java.Admin");
                System.out.println("0. Avsluta");
                System.out.print("Välj ett alternativ: ");

                String select = scanner.nextLine();

                switch (select) {
                    case "1":
                        System.out.println("Enter email:");
                        String email = scanner.nextLine();
                        System.out.println("Enter password");
                        String password = scanner.nextLine();
                        loginService.loginAsCustomer(email, password);
                        break;
                    case "2":
                        System.out.println("Enter name:");
                        String userName = scanner.nextLine();
                        System.out.println("Enter password");
                        String adminPassword = scanner.nextLine();
                        loginService.loginAsAdmin(userName, adminPassword);
                        break;
                    case "0":
                        System.out.println("Avslutar kundhantering...");
                        return;
                    default:
                        System.out.println("Ogiltigt val, försök igen");
                }
            } catch (SQLException e) {
                // Hantera databasfel
                System.out.println("Ett fel uppstod vid databasanrop: " + e.getMessage());
            } catch (Exception e) {
                // Hantera övriga fel (t.ex. felaktig input)
                System.out.println("Ett oväntat fel uppstod: " + e.getMessage());
                scanner.nextLine(); // Rensa scanner-bufferten vid felinmatning
            }
        }
    }
}
