public class ATMImpl implements ATMOperations {
    private User user;

    public ATMImpl(User user) {
        this.user = user;
    }

    public void checkBalance() {
        System.out.println("Balance: ₹" + user.getBalance());
    }

    public void deposit(double amount) {
        user.deposit(amount);
        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (user.withdraw(amount)) {
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void viewMiniStatement() {
        System.out.println("Mini Statement:");
        for (String t : user.getTransactions()) {
            System.out.println("- " + t);
        }
    }

    public void changePin(String newPin) {
        user.setPin(newPin);
        System.out.println("PIN updated successfully.");
    }
}
