
package com.Titan.atmapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private Bank theBank;
    private User currentUser;
    
    public GUI() {
        // Initialize the bank and add a user
        theBank = new Bank("Bank of Dayspring");
        User aUser = theBank.addUser("Dayspring", "Idahosa", "1234");
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);
        
        // Setup the frame
        setTitle("ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Show login screen initially
        showLoginScreen();
    }
    
    private void showLoginScreen() {
        getContentPane().removeAll();
        setLayout(new GridLayout(3, 2));
        
        JLabel userIdLabel = new JLabel("User ID:");
        JTextField userIdField = new JTextField();
        
        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField();
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = userIdField.getText();
                String pin = new String(pinField.getPassword());
                currentUser = theBank.userLogin(userID, pin);
                if (currentUser != null) {
                    showMainMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid user ID or PIN.");
                }
            }
        });
        
        add(userIdLabel);
        add(userIdField);
        add(pinLabel);
        add(pinField);
        add(loginButton);
        
        revalidate();
        repaint();
    }
    
    private void showMainMenu() {
        getContentPane().removeAll();
        setLayout(new GridLayout(5, 1));
        
        JButton showHistoryButton = new JButton("Show Account Transaction History");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer");
        JButton quitButton = new JButton("Quit");
        
        showHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactionHistory();
            }
        });
        
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWithdrawScreen();
            }
        });
        
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDepositScreen();
            }
        });
        
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransferScreen();
            }
        });
        
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginScreen();
            }
        });
        
        add(showHistoryButton);
        add(withdrawButton);
        add(depositButton);
        add(transferButton);
        add(quitButton);
        
        revalidate();
        repaint();
    }
    
    private void showTransactionHistory() {
        // Implement the transaction history display logic here
    }
    
    private void showWithdrawScreen() {
        // Implement the withdraw screen logic here
    }
    
    private void showDepositScreen() {
        // Implement the deposit screen logic here
    }
    
    private void showTransferScreen() {
        // Implement the transfer screen logic here
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}

