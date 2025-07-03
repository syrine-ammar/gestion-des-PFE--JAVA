package trial;


import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationFormDialog extends JDialog {

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField carteCinField;
    private JTextField mailField;
    private JTextField passField;
    private JButton registerButton;

    public RegistrationFormDialog(JFrame parent) {
        super(parent, "Registration Form", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        add(panel);

        panel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        panel.add(nomField);

        panel.add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        panel.add(prenomField);
        


        panel.add(new JLabel("Carte CIN (8 digits):"));
        carteCinField = new JTextField();
        panel.add(carteCinField);

        panel.add(new JLabel("Mail (@gmail.com):"));
        mailField = new JTextField();
        panel.add(mailField);
        
        panel.add(new JLabel("Password:"));
        passField = new JTextField();
        panel.add(passField);

        registerButton = new JButton("Register");
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the input from the fields
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String carteCin = carteCinField.getText();
                String mail = mailField.getText();
                String Password = passField.getText();

                // Validate the input
                try {
                    // Validate carteCin to have exactly 8 digits
                    if (carteCin.length() != 8) {
                        throw new IllegalArgumentException("Carte CIN must have exactly 8 digits.");
                    }
                    Long.parseLong(carteCin); // Try parsing carteCin as a Long to ensure it contains only digits

                    // Validate mail to contain "@gmail.com"
                    if (!mail.toLowerCase().endsWith("@gmail.com")) {
                        throw new IllegalArgumentException("Mail must end with '@gmail.com'.");
                    }

                    // Create a new User object and add it to the userList
                    User user = new User( carteCin,nom, prenom, mail,Password);
                    ((LoginScreen) parent).addUser(user);

                    // Close the dialog
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegistrationFormDialog.this, "Carte CIN must be a valid 8-digit number.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(RegistrationFormDialog.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}
