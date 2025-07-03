package trial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PresidentsFrame extends JFrame {

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTable table;

    Connecter conn = new Connecter();
    java.sql.Statement stmn;
    ResultSet Rs;
    DefaultTableModel model = new DefaultTableModel();

    public PresidentsFrame() {
        /* Partie pour charger la base de données des présidents */
        model.addColumn("cin_pres");
        model.addColumn("nom");
        model.addColumn("prenom");
        model.addColumn("adresse");

        try {
            stmn = conn.obtenirconnexion().createStatement();
            ResultSet Rs = ((java.sql.Statement) stmn).executeQuery("Select * from President");
            while (Rs.next()) {
                model.addRow(new Object[]{
                        Rs.getString("cin_pres"),
                        Rs.getString("nom"),
                        Rs.getString("prenom"),
                        Rs.getString("adresse")
                });
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] labels = {"CIN:", "Nom:", "Prénom:", "Adresse:"};
        for (String label : labels) {
            JLabel jLabel = new JLabel(label);
            JTextField jTextField = new JTextField();
            inputPanel.add(jLabel);
            inputPanel.add(jTextField);
        }

        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton ajouterButton = new JButton("Ajouter");
        JButton modifierButton = new JButton("Modifier");
        JButton supprimerButton = new JButton("Supprimer");
        JButton actualiserButton = new JButton("Actualiser");
        JButton rechercherButton = new JButton("Rechercher");
        JTextField rechercherTextField = new JTextField(15);

        buttonPanel.add(ajouterButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        buttonPanel.add(actualiserButton);
        buttonPanel.add(rechercherTextField);
        buttonPanel.add(rechercherButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PresidentsFrame::new);
    }
}
