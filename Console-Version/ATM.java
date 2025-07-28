import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Predefined users
        HashMap<String, User> users = new HashMap<>();
        users.put("123456", new User("123456", "1234", 5000));
        users.put("987654", new User("987654", "5678", 10000));

        System.out.println("Welcome to Smart ATM Simulator");
        System.out.print("Enter Card Number: ");
        String card = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (users.containsKey(card) && users.get(card).getPin().equals(pin)) {
            User currentUser = users.get(card);
            ATMImpl atm = new ATMImpl(currentUser);

            int choice;
            do {
                System.out.println(
                        "\n1. Check Balance\n2. Deposit\n3. Withdraw\n4. View Mini Statement\n5. Change PIN\n6. Exit");
                System.out.print("Choose an option: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> atm.checkBalance();
                    case 2 -> {
                        System.out.print("Enter amount: ");
                        atm.deposit(sc.nextDouble());
                    }
                    case 3 -> {
                        System.out.print("Enter amount: ");
                        atm.withdraw(sc.nextDouble());
                    }
                    case 4 -> atm.viewMiniStatement();
                    case 5 -> {
                        sc.nextLine(); // consume newline
                        System.out.print("Enter new PIN: ");
                        atm.changePin(sc.nextLine());
                    }
                    case 6 -> System.out.println("Thank you for using Smart ATM!");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 6);
        } else {
            System.out.println("Invalid credentials. Exiting.");
        }

        sc.close();
    }
}
