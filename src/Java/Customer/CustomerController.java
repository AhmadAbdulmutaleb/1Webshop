package Java.Customer;

import Java.Customer.CustomerRepository;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerController {

    CustomerService customerService;

    Scanner scanner;

    public CustomerController() {
        this.customerService = new CustomerService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        // ANSI färger och formatering
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String PURPLE = "\u001B[35m";
        final String CYAN = "\u001B[36m";
        final String BOLD = "\u001B[1m";

        // Unicode symboler
        final String CUSTOMER = "👤";
        final String LIST = "📋";
        final String ADD = "➕";
        final String SEARCH = "🔍";
        final String EXIT = "🚪";
        final String ARROW = "➜";
        final String SUCCESS = "✅";
        final String ERROR = "❌";
        final String STAR = "⭐";

        while (true) {
            try {
                // Snyggt formaterad meny med symboler
                System.out.println("\n" + BOLD + BLUE + "╔══════════════════════════╗" + RESET);
                System.out.println(BOLD + BLUE + "║    " + PURPLE + "Kundhantering " + CUSTOMER + "    " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "╠══════════════════════════╣" + RESET);
                System.out.println(BOLD + BLUE + "║ " + CYAN + "1. " + LIST + " Visa alla kunder    " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "║ " + CYAN + "2. " + SEARCH + " Visa en kund       " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "║ " + CYAN + "3. " + ADD + " Lägg till en kund   " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "║ " + CYAN + "4. " + ADD + " Uppdatera kundens information   " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "║ " + CYAN + "5. " + SEARCH + " Ta bort en kund       " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "║ " + RED + "0. " + EXIT + " Avsluta           " + BLUE + "║" + RESET);
                System.out.println(BOLD + BLUE + "╚══════════════════════════╝" + RESET);
                System.out.print(YELLOW + ARROW + " Välj ett alternativ: " + RESET);

                String select = scanner.nextLine();

                switch (select) {
                    case "1":
                        System.out.println(BOLD + CYAN + "\n=== Visar alla kunder " + LIST + " ===" + RESET);
                        customerService.showAllUsers();
                        System.out.println(GREEN + SUCCESS + " Alla kunder listade framgångsrikt!" + RESET);
                        break;

                    case "2":
                        System.out.println(BOLD + CYAN + "\n=== Sök kund " + SEARCH + " ===" + RESET);
                        System.out.print(YELLOW + ARROW + " Ange ID: " + RESET);
                        String idInput = scanner.nextLine();
                        int id = Integer.parseInt(idInput);
                        Customer customer1 = customerService.showUserById(id);
                        if (customer1 != null){
                            System.out.println(GREEN + SUCCESS + " Kund hittad!" + RESET);
                            break;
                        }

                        else {
                            System.out.println(" Hittar ingen kund med ID = " + id);
                        }
                        break;
                    case "3":
                        System.out.println(BOLD + CYAN + "\n=== Lägg till ny kund " + ADD + " ===" + RESET);
                        System.out.print(YELLOW + ARROW + " Namn: " + RESET);
                        String name = scanner.nextLine();
                        if (name == null|| name.trim().isEmpty()){
                            System.out.println("Namnet får inte vara tom");
                            break;
                        }
                        System.out.print(YELLOW + ARROW + " Email: " + RESET);
                        String email = scanner.nextLine();
                        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                            System.out.println("E-mail är Ogiltig");
                            break;
                        }
                        System.out.print(YELLOW + ARROW + " Telefon: " + RESET);
                        String phone = scanner.nextLine();
                        if (phone == null || !phone.matches("\\d{6,}")){
                            System.out.println("Ogiltigt Telefonnr.");
                            break;
                        }

                        System.out.print(YELLOW + ARROW + " Adress: " + RESET);
                        String address = scanner.nextLine();
                        if (address == null || address.trim().isEmpty()) {
                            System.out.println("Ogiltigt address!");
                            break;
                        }
                        System.out.print(YELLOW + ARROW + " Lösenord: " + RESET);
                        String password = scanner.nextLine();
                        if (password == null || password.length() < 6 || !password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
                            System.out.println("Lösenordet måste innhålla bokstäver och siffror och vara minst 6 tecken.");
                            break;
                        }

                        customerService.addCustomer(name, email, phone, address, password);
                        System.out.println(GREEN + SUCCESS + " Ny kund tillagd framgångsrikt!" + RESET);
                        break;
                    case "4":
                        System.out.println("Du uppdaterar kundens information");
                        System.out.println(BOLD + CYAN + "\n=== Sök kund " + SEARCH + " ===" + RESET);
                        System.out.print(YELLOW + ARROW + " Ange ID: " + RESET);
                        String idInput2 = scanner.nextLine();
                        int id2 = Integer.parseInt(idInput2);
                        Customer customer = customerService.showUserById(id2);

                        if (customer == null){
                            System.out.println(RED+ ERROR + "ingen kund hittad med ID = "+ id2 + RED + RED);
                            break;
                        }
                        System.out.println(GREEN + SUCCESS + " Kund hittad! ID = " + id2 + RESET);

                        System.out.print( " Nytt namn (" + customer.getName() + "): ");
                        String newCustomerName = scanner.nextLine();
                        if (newCustomerName.isEmpty()) newCustomerName = customer.getName();


                        System.out.print( ARROW + " Ny email (" + customer.getEmail() + "): " + RESET);
                        String newCustomerEmail = scanner.nextLine();
                        if (newCustomerEmail.isEmpty()) newCustomerEmail = customer.getEmail();


                        System.out.print(YELLOW +  " Nytt telefonnummer (" + customer.getPhone() + "): " + RESET);
                        String newCustomerPhone = scanner.nextLine();
                        if (newCustomerPhone.isEmpty()) newCustomerPhone = customer.getPhone();


                        System.out.print(YELLOW + ARROW + " Ny adress (" + customer.getAddress() + "): " );
                        String newCustomerAddress = scanner.nextLine();
                        if (newCustomerAddress.isEmpty()) newCustomerAddress = customer.getAddress();


                        System.out.print(YELLOW + ARROW + " Nytt lösenord: " + RESET);
                        String newCustomerPass = scanner.nextLine();
                        if (newCustomerPass.isEmpty()) newCustomerPass = customer.getPassword();



                        customerService.updateCustomer(id2, newCustomerName, newCustomerEmail, newCustomerPhone, newCustomerAddress, newCustomerPass);

                        System.out.println(GREEN + SUCCESS + " Kundinformation uppdaterad!" + RESET);
                        break;
                    case "5":
                        System.out.println("Välkommen till Delete Customer");
                        System.out.print("Ange Customer ID = ");
                        int ID = scanner.nextInt();
                        customerService.deleteCustomer(ID);
                        System.out.println("Kunden har tagits bort");
                        break;
                    case "0":
                        System.out.println(PURPLE + "\n" + STAR + " Avslutar kundhantering... " + EXIT + RESET);
                        return;
                    default:
                        System.out.println(RED + ERROR + " Ogiltigt val, försök igen" + RESET);
                }
            } catch (SQLException e) {
                System.out.println(RED + ERROR + " Databasfel: " + e.getMessage() + RESET);
            } catch (Exception e) {
                System.out.println(RED + ERROR + " Ett fel uppstod: " + e.getMessage() + RESET);
                scanner.nextLine();
            }
        }
    }
}