package trial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    // ArrayList to store registration details
    private List<User> userList;

    public LoginScreen() {
        setTitle("Login / Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        add(panel);

        panel.add(new JLabel("Email:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);
        registerButton = new JButton("Register");
        panel.add(registerButton); 
        
        loginButton = new JButton("Login");
        panel.add(loginButton);

       

        // Initialize the ArrayList for storing registration details
        userList = new ArrayList<>();

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                // Check if the email exists in the userList
                boolean userFound = false;
                for (User user : userList) {
                    if (user.getMail().equals(email) && user.getPassword().equals(password)) {
                        // Login successful
                        userFound = true;
                        JOptionPane.showMessageDialog(LoginScreen.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        openMainApplicationWindow();
                        break;
                    }
                }
                
                if (!userFound) {
                    // Display an error message if user not found or password incorrect
                    JOptionPane.showMessageDialog(LoginScreen.this, "Incorrect email or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the registration form dialog
                new RegistrationFormDialog(LoginScreen.this);
            }
        });

        setVisible(true);
    }

    // Method to add a user to the userList
    public void addUser(User user) {
        userList.add(user);
    }

    // Method to open the main application window
    private void openMainApplicationWindow() {
        Connecter connecter = new Connecter();
        Connection connection = connecter.obtenirconnexion();
        MainApplicationWindow mainApplicationWindow = new MainApplicationWindow(connection);
        mainApplicationWindow.setVisible(true);
        dispose(); // Close the login screen after opening the main application window
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen();
            }
        });
    }
}