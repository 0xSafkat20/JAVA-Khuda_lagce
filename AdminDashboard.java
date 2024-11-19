import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class AdminDashboard implements ActionListener {

    // Frame for the dashboard
    JFrame frame;

    // Buttons for various operations
    JButton insertBtn, deleteBtn, dispBtn, searchBtn, logoutBtn, lcUser;
    JButton proceedInsert, proceedDelete, proceedDisplay, proceedSearch;

    // Labels for input fields
    JLabel lano, lbalance, opName;

    // Text fields for input
    JTextField accNo, balance;

    // Text area for displaying messages
    JTextArea display;

    // Table to display account data
    JTable jt;

    // Image icon for the logo
    ImageIcon i1;

    // Array to store account objects
    Account[] accounts = new Account[100];

    // Constructor
    public AdminDashboard(String user) {
        frame = new JFrame("Admin Dashboard");
        frame.setBounds(280, 100, 800, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        // Logo
        i1 = new ImageIcon("logo.png");
        frame.setIconImage(i1.getImage());

        // Initialize and add components
        initializeComponents(user);
        frame.setVisible(true);
    }

    private void initializeComponents(String user) {
        // Labels
        lano = new JLabel("Product Name");
        lbalance = new JLabel("Price");
        opName = new JLabel("Default!");
        setLabelProperties();

        // Text fields
        accNo = new JTextField();
        balance = new JTextField();

        // Text area
        display = new JTextArea();
        display.setVisible(false);

        // Buttons
        initializeButtons(user);

        // Table
        jt = new JTable();

        // Add components to frame
        frame.add(lano);
        frame.add(lbalance);
        frame.add(opName);
        frame.add(accNo);
        frame.add(balance);
        frame.add(display);
        frame.add(jt);
    }

    private void setLabelProperties() {
        lano.setBounds(200, 150, 200, 25);
        lano.setFont(new Font("Default", Font.BOLD, 15));
        lbalance.setBounds(200, 200, 100, 25);
        lbalance.setFont(new Font("Default", Font.BOLD, 15));
        opName.setForeground(Color.BLACK);
    }

    private void initializeButtons(String user) {
        insertBtn = createButton("Insert Product", 35, 130, 130, 30, this);
        proceedInsert = createButton("Add", 555, 170, 120, 30, this, false);

        deleteBtn = createButton("Delete Product", 35, 190, 130, 30, this);
        proceedDelete = createButton("Delete", 555, 170, 120, 30, this, false);

        dispBtn = createButton("Search Product", 35, 250, 130, 30, this);
        proceedDisplay = createButton("Show", 555, 170, 120, 30, this, false);

        logoutBtn = createButton("Logout", 350, 450, 120, 30, this);
        logoutBtn.setBackground(Color.decode("#f53131"));

        lcUser = createButton(user, 300, 5, 100, 30, this);
    }

    private JButton createButton(String text, int x, int y, int width, int height, ActionListener listener) {
        return createButton(text, x, y, width, height, listener, true);
    }

    private JButton createButton(String text, int x, int y, int width, int height, ActionListener listener, boolean visible) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, width, height);
        btn.addActionListener(listener);
        btn.setVisible(visible);
        frame.add(btn);
        return btn;
    }

    // Adds a new account to the array
    public void addAccount(Account a) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = a;
                display.setText("Product added successfully.");
                return;
            }
        }
        display.setText("Product could not be added.");
    }

    // Searches for an account by account number
    public Account searchAccount(String accNo) {
        for (Account a : accounts) {
            if (a != null && a.getAccNo().equals(accNo)) {
                return a;
            }
        }
        return null;
    }

    // Deletes an account by account number
    public boolean deleteAccount(String accNo) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getAccNo().equals(accNo)) {
                accounts[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutBtn) {
            new LogInPage();
            frame.dispose();
        } else if (e.getSource() == insertBtn) {
            toggleInsertMode();
        } else if (e.getSource() == proceedInsert) {
            handleInsert();
        } else if (e.getSource() == deleteBtn) {
            toggleDeleteMode();
        } else if (e.getSource() == proceedDelete) {
            handleDelete();
        }
    }

    private void toggleInsertMode() {
        setVisibleForInsertMode(true);
    }

    private void handleInsert() {
        String accNum = accNo.getText();
        String bal = balance.getText();
        if (!accNum.isEmpty() && !bal.isEmpty()) {
            if (searchAccount(accNum) == null) {
                addAccount(new Account(accNum, bal));
            } else {
                JOptionPane.showMessageDialog(null, "Product already exists with this number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Fill all fields.");
        }
    }

    private void toggleDeleteMode() {
        setVisibleForDeleteMode(true);
    }

    private void handleDelete() {
        String accNum = accNo.getText();
        if (deleteAccount(accNum)) {
            display.setText("Product deleted successfully.");
        } else {
            display.setText("Product not found.");
        }
    }

    private void setVisibleForInsertMode(boolean visible) {
        lano.setVisible(visible);
        lbalance.setVisible(visible);
        accNo.setVisible(visible);
        balance.setVisible(visible);
        proceedInsert.setVisible(visible);
        display.setVisible(visible);
    }

    private void setVisibleForDeleteMode(boolean visible) {
        lano.setVisible(visible);
        accNo.setVisible(visible);
        proceedDelete.setVisible(visible);
        display.setVisible(visible);
    }
}
