import java.util.ArrayList;

public class User {
    private String cardNumber;
    private String pin;
    private double balance;
    private ArrayList<String> transactions;

    public User(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited ₹" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew ₹" + amount);
            return true;
        } else {
            transactions.add("Failed withdrawal attempt ₹" + amount);
            return false;
        }
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }
}
