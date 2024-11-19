import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class AdminLogin implements ActionListener {
    // Components for the admin login form
    private JFrame frm;
    private JTextField tf1;
    private JPasswordField pf1;
    private ImageIcon i1;
    private JButton lgbtn, exbtn, bkbtn;
    private JLabel label1, label02;

    // Array to store multiple users
    private user[] users;

    public AdminLogin() {
        // Initialize multiple users
        users = new user[6];
        users[0] = new user("admin", "123");
        users[1] = new user("ABC", "456");

        // Set up the frame
        frm = new JFrame("Admin Login");
        frm.setBounds(450, 80, 600, 650);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setResizable(false);
        frm.setLayout(null);

        // Logo
        i1 = new ImageIcon("logo.png");
        frm.setIconImage(i1.getImage());

        // Initialize and add components
        initializeComponents();

        frm.setVisible(true);
    }

    private void initializeComponents() {
        // Username label
        label1 = new JLabel("Username");
        label1.setBounds(170, 140, 150, 20);
        label1.setFont(new Font("Default", Font.BOLD, 17));

        // Password label
        label02 = new JLabel("Password");
        label02.setBounds(170, 230, 150, 20);
        label02.setFont(new Font("Default", Font.BOLD, 17));

        // Text field for username
        tf1 = new JTextField();
        tf1.setBounds(170, 180, 200, 30);

        // Password field
        pf1 = new JPasswordField();
        pf1.setBounds(170, 270, 200, 30);

        // Login button
        lgbtn = createButton("Login", 170, 330, 200, 30, Color.BLACK, Color.WHITE, this);

        // Exit button
        exbtn = createButton("Exit", 513, 0, 80, 30, Color.ORANGE, Color.BLACK, this);

        // Back button
        bkbtn = createButton("Back", 1, 1, 80, 30, Color.BLUE, Color.BLACK, this);

        // Add components to frame
        frm.add(label1);
        frm.add(label02);
        frm.add(tf1);
        frm.add(pf1);
        frm.add(lgbtn);
        frm.add(exbtn);
        frm.add(bkbtn);
    }

    private JButton createButton(String text, int x, int y, int width, int height, Color bg, Color fg, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setFont(new Font("Default", Font.BOLD, 13));
        button.addActionListener(listener);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lgbtn) {
            handleLogin();
        } else if (e.getSource() == exbtn) {
            System.exit(0);
        } else if (e.getSource() == bkbtn) {
            new LogInPage(); // Assuming LogInPage exists in your project
            frm.dispose();
        }
    }

    private void handleLogin() {
        String user = tf1.getText();
        String pass = new String(pf1.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            showMessageDialog(null, "Please fill in both fields.");
            return;
        }

        for (user u : users) {
            if (u != null && user.equals(u.getUsername()) && pass.equals(u.getPassword())) {
                frm.setVisible(false);
                new AdminDashboard(user); // Assuming AdminDashboard exists
                return;
            }
        }
        showMessageDialog(null, "Invalid Username or Password!");
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}

// Mock user class for demonstration purposes
class user {
    private String username;
    private String password;

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
