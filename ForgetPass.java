import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ForgetPass implements ActionListener {
    // Declare frame and components for Forget Password screen
    private JFrame fp;
    private JLabel lbl, label2, label3, label4;
    private JTextField tf1, tf2, tf3;
    private JButton svbtn, bcbtn;
    private ImageIcon img;

    public ForgetPass() {
        // Initialize frame for forget password screen
        fp = new JFrame("Forget Password");

        // Label for title
        lbl = new JLabel("Forget Password");
        lbl.setFont(new Font("Default", Font.BOLD, 19));
        lbl.setBounds(50, 50, 350, 40);
        fp.add(lbl);

        // Load logo
        img = new ImageIcon("logo.png");

        // Frame setup
        fp.setSize(400, 400);
        fp.setLayout(null);
        fp.setVisible(true);
        fp.setBounds(450, 80, 600, 650);

        // Username label
        label2 = new JLabel("User Name");
        label2.setBounds(80, 150, 200, 50);
        label2.setFont(new Font("Default", Font.BOLD, 17));

        // New password label
        label3 = new JLabel("New Password");
        label3.setBounds(80, 200, 200, 50);
        label3.setFont(new Font("Default", Font.BOLD, 17));

        // Re-type new password label
        label4 = new JLabel("Re-type New Password");
        label4.setBounds(80, 250, 200, 50);
        label4.setFont(new Font("Default", Font.BOLD, 17));

        // TextField for Username input
        tf1 = new JTextField();
        tf1.setBounds(300, 160, 150, 25);

        // TextField for New Password input
        tf2 = new JTextField();
        tf2.setBounds(300, 215, 150, 25);

        // TextField for Re-typed Password input
        tf3 = new JTextField();
        tf3.setBounds(300, 265, 150, 25);

        // Save button to save the new password
        svbtn = new JButton("Save");
        svbtn.setBounds(315, 320, 90, 30);
        svbtn.setForeground(Color.WHITE);
        svbtn.setBackground(Color.decode("#2E75B6"));
        svbtn.setFont(new Font("Default", Font.BOLD, 13));

        // Back button to go back to login page
        bcbtn = new JButton("Back");
        bcbtn.setBounds(1, 1, 90, 30);
        bcbtn.setForeground(Color.BLUE);
        bcbtn.setFont(new Font("Default", Font.BOLD, 13));
        bcbtn.addActionListener(this); // Set action listener for back button

        // Add all components to the frame
        fp.add(label2);
        fp.add(label3);
        fp.add(label4);
        fp.add(tf1);
        fp.add(tf2);
        fp.add(tf3);
        fp.add(svbtn);
        fp.add(bcbtn);
        fp.add(lbl);

        // Set frame icon
        fp.setIconImage(img.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If back button is clicked, go back to login page
        if (e.getSource() == bcbtn) {
            new LogInPage(); // Assuming LogInPage is a defined class in your project
            fp.setVisible(false); // Close current frame
        }
    }

    public static void main(String[] args) {
        new ForgetPass(); // Create an instance of ForgetPass and display the frame
    }
}
