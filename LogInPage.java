import javax.swing.*; // Import Swing components for GUI
import java.awt.*;    // Import AWT classes for layout and color
import java.awt.event.*; // Import event handling classes

// Main class extending JFrame and implementing ActionListener
public class LogInPage extends JFrame implements ActionListener {

    // Declaring components
    JFrame frame;
    JLabel label1, label2, header, imgLabel1, imgLabel2, imgLabel3;
    JTextField tf1; // For username input
    JPasswordField pf1; // For password input
    JButton lgbtn, fpbtn, sgbtn, exbtn, adminbtn; // Buttons for various actions
    JCheckBox cb1; // Checkbox to show/hide password

    // Users for authentication
    User un1, un2; // Sample user instances
    User[] users;  // Array to hold multiple users

    User sl1; // Sample seller instance
    User[] salesman; // Array to hold multiple sellers

    // Constructor for LogInPage
    LogInPage() {
        // Initializing user data
        un1 = new User("buyer", "123");
        un2 = new User("user", "456");
        users = new User[6]; 
        users[0] = un1;
        users[1] = un2;

        // Initializing seller data
        sl1 = new User("seller", "123");
        salesman = new User[3];
        salesman[0] = sl1;

        // Setting up the main frame
        frame = new JFrame("LoginPage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        frame.setResizable(false); // Prevent resizing

        // Header label
        header = new JLabel("Log In");
        header.setBounds(680, 50, 100, 100);
        header.setFont(new Font("Default", Font.BOLD, 27));

        // Username label and text field
        label1 = new JLabel("Username:");
        label1.setBounds(590, 150, 150, 20);
        tf1 = new JTextField(); // Input for username
        tf1.setBounds(680, 150, 150, 30);

        // Password label and password field
        label2 = new JLabel("Password:");
        label2.setBounds(590, 190, 150, 20);
        pf1 = new JPasswordField(); // Input for password
        pf1.setBounds(680, 185, 150, 30);

        // Checkbox to toggle password visibility
        cb1 = new JCheckBox("Show Password");
        cb1.setBounds(680, 220, 120, 20);
        cb1.addActionListener(this); // Action listener for toggle

        // Login button
        lgbtn = new JButton("Login");
        lgbtn.setBounds(670, 250, 90, 40);
        lgbtn.addActionListener(this); // Handle login

        // Forget Password button
        fpbtn = new JButton("Forget Password");
        fpbtn.setBounds(570, 320, 140, 40);
        fpbtn.addActionListener(this); // Handle forgot password

        // Signup button
        sgbtn = new JButton("Sign Up");
        sgbtn.setBounds(735, 320, 120, 40);
        sgbtn.addActionListener(this); // Handle sign-up action

        // Exit button
        exbtn = new JButton("Exit");
        exbtn.setBounds(810, 1, 80, 30);
        exbtn.addActionListener(this); // Handle exit

        // Admin login button
        adminbtn = new JButton("Log in As Admin");
        adminbtn.setBounds(640, 440, 160, 40);
        adminbtn.addActionListener(this); // Handle admin login

        // Images for labels and background
        imgLabel1 = new JLabel(new ImageIcon("user.png"));
        imgLabel1.setBounds(517, 111, 100, 100); // Username icon

        imgLabel2 = new JLabel(new ImageIcon("lock.png"));
        imgLabel2.setBounds(517, 147, 100, 100); // Password icon

        imgLabel3 = new JLabel(new ImageIcon("wepik-photo-mode.png"));
        imgLabel3.setBounds(0, 0, 500, 650); // Background image

        // Adding components to frame
        frame.setLayout(null); 
        frame.setBounds(350, 90, 900, 650);
        frame.add(header);
        frame.add(label1);
        frame.add(label2);
        frame.add(tf1);
        frame.add(pf1);
        frame.add(lgbtn);
        frame.add(fpbtn);
        frame.add(sgbtn);
        frame.add(exbtn);
        frame.add(imgLabel1);
        frame.add(imgLabel2);
        frame.add(imgLabel3);
        frame.add(adminbtn);
        frame.add(cb1);

        frame.setVisible(true); // Make the frame visible
    }

    // Action listener for handling button clicks and checkbox state
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lgbtn) { // Login button logic
            String user = tf1.getText(); 
            String pass = new String(pf1.getPassword()); 

            for (User u : users) {
                if (u != null && u.getUsername().equals(user) && u.getPassword().equals(pass)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    return; // Exit method after successful login
                }
            }
            JOptionPane.showMessageDialog(null, "Invalid Login!"); // Error if no match
        }

        if (e.getSource() == cb1) { // Toggle password visibility
            pf1.setEchoChar(cb1.isSelected() ? (char) 0 : '*');
        }

        if (e.getSource() == exbtn) { // Exit button
            System.exit(0);
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new LogInPage(); // Create an instance of LogInPage
    }
}
