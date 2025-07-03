package trial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class EtudiantsFrame extends JFrame {

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTable table;

    Connecter conn = new Connecter();
    java.sql.Statement stmn;
    ResultSet Rs;
    DefaultTableModel model = new DefaultTableModel();

    public EtudiantsFrame() {
        /* Partie pour charger  la base de données des étudiants */
        model.addColumn("cin");
        model.addColumn("nom");
        model.addColumn("prenom");
        model.addColumn("adresse");
        model.addColumn("classe");
        model.addColumn("enseignant_cin");
        model.addColumn("encadreur_de_stage_cin");
        model.addColumn("projetpfe_id");
        model.addColumn("projetpfe_grpjury_id");
        model.addColumn("projetpfe_personneladmin_cin_adm");

        try {
            stmn = conn.obtenirconnexion().createStatement();
            ResultSet Rs = ((java.sql.Statement) stmn).executeQuery("Select * from Etudiant");
            while (Rs.next()) {
                model.addRow(new Object[]{
                        Rs.getString("cin"),
                        Rs.getString("nom"),
                        Rs.getString("prenom"),
                        Rs.getString("adresse"),
                        Rs.getString("classe"),
                        Rs.getString("enseignant_cin"),
                        Rs.getString("encadreur_de_stage_cin"),
                        Rs.getString("projetpfe_id"),
                        Rs.getString("projetpfe_grpjury_id"),
                        Rs.getString("projetpfe_personneladmin_cin_adm")
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

        String[] labels = {"CIN:", "Nom:", "Prénom:", "Classe:", "Projet ID:", "Encadreur Nom:", "Groupe Jury Num:"};
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
        SwingUtilities.invokeLater(EtudiantsFrame::new);
    }
}

