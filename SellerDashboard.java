import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SellerDashboard implements ActionListener {

    //--------- Frame and components -----
    private JFrame frame; // Main frame
    private JButton insertBtn, deleteBtn, dispBtn, searchBtn, logoutBtn, lcUser, proceedInsert, proceedDelete, proceedDisplay, proceedSearch; // Buttons
    private JLabel lano, lbalance, opName; // Labels
    private JTextField accNo, balance; // Text Fields
    private JTextArea display; // Text Area for displaying messages
    private ImageIcon i1; // Image Icon for frame
    private JTable jt; // Table for product listing
    private Account[] accounts = new Account[100]; // Array to hold product accounts

    // Constructor to set up the GUI components
    public SellerDashboard() {

        // Create the main frame
        frame = new JFrame("Seller Dashboard");

        // Set frame properties
        frame.setBounds(350, 90, 900, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        // Set icon for the frame
        i1 = new ImageIcon("logo.png");

        // Initialize labels
        lano = new JLabel("Product Name");
        lbalance = new JLabel("Price");
        opName = new JLabel("Default!");
        opName.setForeground(Color.BLACK);

        // Initialize text fields
        accNo = new JTextField(10);
        balance = new JTextField(10);

        // Initialize text area for displaying messages
        display = new JTextArea(10, 10);

        // Initialize buttons
        insertBtn = new JButton("Add Product");
        proceedInsert = new JButton("Add");
        deleteBtn = new JButton("Delete Product");
        proceedDelete = new JButton("Delete");
        logoutBtn = new JButton("Logout");

        // Set button properties and add action listeners
        insertBtn.setBounds(250, 70, 120, 30);
        insertBtn.setBackground(Color.decode("#f5f5f5"));
        insertBtn.setForeground(Color.decode("#030202"));
        insertBtn.addActionListener(this);

        proceedInsert.setBounds(350, 300, 120, 30);
        proceedInsert.setBackground(Color.decode("#5b7ccf"));
        proceedInsert.setForeground(Color.white);
        proceedInsert.setVisible(false);
        proceedInsert.addActionListener(this);

        deleteBtn.setBounds(450, 70, 120, 30);
        deleteBtn.setBackground(Color.WHITE);
        deleteBtn.setForeground(Color.BLACK);
        deleteBtn.addActionListener(this);

        proceedDelete.setBounds(350, 300, 120, 30);
        proceedDelete.setBackground(Color.decode("#5b7ccf"));
        proceedDelete.setForeground(Color.white);
        proceedDelete.setVisible(false);
        proceedDelete.addActionListener(this);

        logoutBtn.setBounds(350, 500, 120, 30);
        logoutBtn.setBackground(Color.decode("#f53131"));
        logoutBtn.setForeground(Color.black);
        logoutBtn.addActionListener(this);

        // Set up the table (though not fully implemented in this example)
        jt = new JTable();

        // Add all components to the frame
        frame.add(lano);
        frame.add(lbalance);
        frame.add(accNo);
        frame.add(opName);
        frame.add(balance);
        frame.add(insertBtn);
        frame.add(proceedInsert);
        frame.add(deleteBtn);
        frame.add(proceedDelete);
        frame.add(display);
        frame.add(logoutBtn);
        frame.add(jt);

        // Set component positions and font styling
        lano.setBounds(200, 200, 150, 25);
        lano.setFont(new Font("Default", Font.BOLD, 15));
        lbalance.setBounds(200, 250, 100, 25);
        lbalance.setFont(new Font("Default", Font.BOLD, 15));
        accNo.setBounds(320, 200, 200, 30);
        balance.setBounds(320, 250, 200, 30);
        display.setBounds(300, 400, 250, 30);

        // Initially hide unnecessary components
        lano.setVisible(false);
        lbalance.setVisible(false);
        opName.setVisible(false);
        accNo.setVisible(false);
        balance.setVisible(false);
        display.setVisible(false);

        // Set frame icon
        frame.setIconImage(i1.getImage());
    }

    // Add account to the account array
    public void addAccount(Account a) {
        boolean added = false;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = a;
                added = true;
                break;
            }
        }
        displayMessage(added ? "Product added." : "Product cannot be added.");
    }

    // Search for an account by its account number
    public Account searchAccount(String accNo) {
        for (Account account : accounts) {
            if (account != null && account.getAccNo().equals(accNo)) {
                return account;
            }
        }
        return null;
    }

    // Delete an account by its account number
    public boolean deleteAccount(String accNo) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getAccNo().equals(accNo)) {
                accounts[i] = null;
                return true;
            }
        }
        return false;
    }

    // Display messages in the text area
    private void displayMessage(String message) {
        display.setText(message);
        display.setFont(new Font("Default", Font.BOLD, 20));
    }

    // Handle button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutBtn) {
            new LogInPage();
            frame.setVisible(false);
            System.exit(0);
        } else if (e.getSource() == insertBtn) {
            prepareInsertView();
        } else if (e.getSource() == deleteBtn) {
            prepareDeleteView();
        } else if (e.getSource() == proceedInsert) {
            handleAddProduct();
        } else if (e.getSource() == proceedDelete) {
            handleDeleteProduct();
        }
    }

    // Prepare the UI for the "Add Product" action
    private void prepareInsertView() {
        lano.setVisible(true);
        lbalance.setVisible(true);
        opName.setText("Operation: Add");
        opName.setVisible(true);
        accNo.setVisible(true);
        balance.setVisible(true);
        display.setVisible(true);
        proceedInsert.setVisible(true);
        proceedDelete.setVisible(false);
    }

    // Prepare the UI for the "Delete Product" action
    private void prepareDeleteView() {
        lano.setVisible(true);
        accNo.setVisible(true);
        display.setVisible(true);
        display.setText(null);
        proceedInsert.setVisible(false);
        proceedDelete.setVisible(true);
        opName.setText("Operation: Delete");
        opName.setVisible(true);
        lbalance.setVisible(false);
        balance.setVisible(false);
    }

    // Handle adding a product to the system
    private void handleAddProduct() {
        String accNum = accNo.getText();
        String bal = balance.getText();

        if (!accNum.isEmpty() && !bal.isEmpty()) {
            if (searchAccount(accNum) == null) {
                Account a = new Account(accNum, bal);
                addAccount(a);
            } else {
                JOptionPane.showMessageDialog(null,
                        "A product already exists with that Product No. " + accNum + ". Try again");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter valid information in the fields");
        }
    }

    // Handle deleting a product from the system
    private void handleDeleteProduct() {
        String accNum = accNo.getText();
        boolean isDeleted = deleteAccount(accNum);
        displayMessage(isDeleted ? "Product deleted successfully." : "Product could not be deleted.");
    }

    // Main method to run the Seller Dashboard
    public static void main(String[] args) {
        new SellerDashboard(); // Create an instance of SellerDashboard to launch the GUI
    }
}
