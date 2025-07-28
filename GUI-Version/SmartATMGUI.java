// Smart ATM with Java Swing GUI
// File: SmartATMGUI.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class User {
    String cardNumber, pin;
    double balance;
    ArrayList<String> transactions = new ArrayList<>();

    public User(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
    }
}

public class SmartATMGUI {
    static Map<String, User> users = new HashMap<>();
    static User currentUser;

    public static void main(String[] args) {
        users.put("123456", new User("123456", "1234", 5000));
        users.put("987654", new User("987654", "5678", 10000));

        SwingUtilities.invokeLater(() -> showLoginScreen());
    }

    static void showLoginScreen() {
        JFrame loginFrame = new JFrame("ATM Login");
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(new GridLayout(4, 1));

        JTextField cardField = new JTextField();
        JPasswordField pinField = new JPasswordField();

        loginFrame.add(new JLabel("Card Number:"));
        loginFrame.add(cardField);
        loginFrame.add(new JLabel("PIN:"));
        loginFrame.add(pinField);

        JButton loginBtn = new JButton("Login");
        loginFrame.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String card = cardField.getText();
            String pin = new String(pinField.getPassword());
            if (users.containsKey(card) && users.get(card).pin.equals(pin)) {
                currentUser = users.get(card);
                loginFrame.dispose();
                showDashboard();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }

    static void showDashboard() {
        JFrame frame = new JFrame("ATM Dashboard");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 2));

        JButton checkBalanceBtn = new JButton("Check Balance");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton miniStmtBtn = new JButton("Mini Statement");
        JButton changePinBtn = new JButton("Change PIN");
        JButton exitBtn = new JButton("Exit");

        frame.add(checkBalanceBtn);
        frame.add(depositBtn);
        frame.add(withdrawBtn);
        frame.add(miniStmtBtn);
        frame.add(changePinBtn);
        frame.add(exitBtn);

        checkBalanceBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(frame, "Balance: ₹" + currentUser.balance));

        depositBtn.addActionListener(e -> {
            String amtStr = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
            try {
                double amt = Double.parseDouble(amtStr);
                currentUser.balance += amt;
                currentUser.transactions.add("Deposited ₹" + amt);
                JOptionPane.showMessageDialog(frame, "Deposited Successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Amount");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String amtStr = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
            try {
                double amt = Double.parseDouble(amtStr);
                if (amt <= currentUser.balance) {
                    currentUser.balance -= amt;
                    currentUser.transactions.add("Withdrew ₹" + amt);
                    JOptionPane.showMessageDialog(frame, "Withdrawn Successfully");
                } else {
                    JOptionPane.showMessageDialog(frame, "Insufficient Balance");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Amount");
            }
        });

        miniStmtBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("Mini Statement:\n");
            for (String txn : currentUser.transactions) {
                sb.append(txn).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.toString());
        });

        changePinBtn.addActionListener(e -> {
            String newPin = JOptionPane.showInputDialog(frame, "Enter new PIN:");
            if (newPin != null && newPin.length() >= 4) {
                currentUser.pin = newPin;
                JOptionPane.showMessageDialog(frame, "PIN Changed Successfully");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid PIN");
            }
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for using Smart ATM");
            System.exit(0);
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
