package trial;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.sql.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EssaiEnseignant extends JFrame {

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTable table;

    Connecter conn = new Connecter();
    java.sql.Statement stmn;
    ResultSet Rs;
    DefaultTableModel model = new DefaultTableModel();

    public EssaiEnseignant () {
        model.addColumn("cin");
        model.addColumn("nom");
        model.addColumn("prenom");
        model.addColumn("adresse");
        model.addColumn("specialite");

        try {
            stmn = conn.obtenirconnexion().createStatement();
            Rs = stmn.executeQuery("Select * from Enseignant");
            while (Rs.next()) {
                model.addRow(new Object[]{
                        Rs.getString("cin"),
                        Rs.getString("nom"),
                        Rs.getString("prenom"),
                        Rs.getString("adresse"),
                        Rs.getString("specialite")
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

        String[] labels = {"CIN:", "Nom:", "Prénom:", "Adresse:", "Spécialité:"};
        textField = new JTextField();
        textField_1 = new JTextField();
        textField_2 = new JTextField();
        textField_3 = new JTextField();
        textField_4 = new JTextField();
        JTextField[] textFields = {textField, textField_1, textField_2, textField_3, textField_4};

        for (int i = 0; i < labels.length; i++) {
            JLabel jLabel = new JLabel(labels[i]);
            inputPanel.add(jLabel);
            inputPanel.add(textFields[i]);
        }

        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton ajouterButton = new JButton("Ajouter");
        JButton modifierButton = new JButton("Modifier");
        JButton supprimerButton = new JButton("Supprimer");
        JButton actualiserButton = new JButton("Actualiser");
        JButton rechercherButton= new JButton("Rechercher");
        
     
        JTextField rechercherTextField = new JTextField(15);

        buttonPanel.add(ajouterButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        buttonPanel.add(actualiserButton);
        buttonPanel.add(rechercherTextField);
        
 /*       JButton filterButton = new JButton("Filtrer");
        buttonPanel.add(filterButton); */
        
        
         
        buttonPanel.add(rechercherButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton("Filtrer");
        buttonPanel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel filterPanel = new JPanel(new GridLayout(0, 1));
                JTextField teacherCinTextField = new JTextField();
                filterPanel.add(new JLabel("CIN de l'enseignant :"));
                filterPanel.add(teacherCinTextField);

                int result = JOptionPane.showConfirmDialog(null, filterPanel, "Filtrer les étudiants", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String teacherCin = teacherCinTextField.getText();
                    filterStudentsByTeacher(teacherCin);
                }
            }
        });

        buttonPanel.add(btnNewButton);
        setVisible(true);
        ajouterButton.addActionListener(e -> ajouterEnseignant());
        supprimerButton.addActionListener(e -> supprimerEnseignant());
        modifierButton.addActionListener(e -> modifierEnseignant());
        
        setVisible(true);
        rechercherTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(rechercherTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(rechercherTextField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(rechercherTextField.getText());
            }
        });

        setVisible(true);
    }
    private void ajouterEnseignant() {
        String cin = textField.getText();
        String nom = textField_1.getText();
        String prenom = textField_2.getText();
        String adresse = textField_3.getText();
        String specialite = textField_4.getText();

        try {
            stmn = conn.obtenirconnexion().createStatement();
            stmn.executeUpdate("INSERT INTO Enseignant (cin, nom, prenom, adresse, specialite) VALUES ('" +
                    cin + "','" + nom + "','" + prenom + "','" + adresse + "','" + specialite + "')");
            JOptionPane.showMessageDialog(null, "Enseignant ajouté avec succès !");
            actualiser(); // Appel d'une méthode pour actualiser la table après l'ajout
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'enseignant : " + e.getLocalizedMessage());
        }
    }

    private void actualiser() {
        // Actualisation de la table des enseignants
        model.setRowCount(0); // Efface toutes les lignes actuelles
        try {
            Rs = stmn.executeQuery("Select * from Enseignant");
            while (Rs.next()) {
                model.addRow(new Object[]{
                        Rs.getString("cin"),
                        Rs.getString("nom"),
                        Rs.getString("prenom"),
                        Rs.getString("adresse"),
                        Rs.getString("specialite")
                });
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    private void supprimerEnseignant() {
        String cin = JOptionPane.showInputDialog("Entrez le CIN de l'enseignant à supprimer :");
        if (cin != null && !cin.isEmpty()) {
            try {
                stmn = conn.obtenirconnexion().createStatement();
                stmn.executeUpdate("DELETE FROM Enseignant WHERE cin = '" + cin + "'");
                JOptionPane.showMessageDialog(null, "Enseignant supprimé avec succès !");
                actualiser(); // Actualiser la table après la suppression
            } catch (Exception e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'enseignant : " + e.getLocalizedMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un CIN valide.");
        }
    }

    private void modifierEnseignant() {
        String cin = JOptionPane.showInputDialog("Entrez le CIN de l'enseignant à modifier :");
        if (cin != null && !cin.isEmpty()) {
            try {
                stmn = conn.obtenirconnexion().createStatement();
                ResultSet rs = stmn.executeQuery("SELECT * FROM Enseignant WHERE cin = '" + cin + "'");
                if (rs.next()) {
                    JPanel modifyPanel = new JPanel(new GridLayout(0, 1));
                    modifyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    
                    JCheckBox cinCheckBox = new JCheckBox("CIN");
                    JCheckBox nomCheckBox = new JCheckBox("Nom");
                    JCheckBox prenomCheckBox = new JCheckBox("Prénom");
                    JCheckBox adresseCheckBox = new JCheckBox("Adresse");
                    JCheckBox specialiteCheckBox = new JCheckBox("Spécialité");
                    
                    modifyPanel.add(new JLabel("Choisissez l'attribut à modifier :"));
                    modifyPanel.add(cinCheckBox);
                    modifyPanel.add(nomCheckBox);
                    modifyPanel.add(prenomCheckBox);
                    modifyPanel.add(adresseCheckBox);
                    modifyPanel.add(specialiteCheckBox);
                    
                    int result = JOptionPane.showConfirmDialog(null, modifyPanel, "Modification de l'enseignant", JOptionPane.OK_CANCEL_OPTION);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        String newCin = rs.getString("cin");
                        String newNom = rs.getString("nom");
                        String newPrenom = rs.getString("prenom");
                        String newAdresse = rs.getString("adresse");
                        String newSpecialite = rs.getString("specialite");

                        if (cinCheckBox.isSelected()) {
                            newCin = JOptionPane.showInputDialog("Nouveau CIN :", rs.getString("cin"));
                        }
                        if (nomCheckBox.isSelected()) {
                            newNom = JOptionPane.showInputDialog("Nouveau Nom :", rs.getString("nom"));
                        }
                        if (prenomCheckBox.isSelected()) {
                            newPrenom = JOptionPane.showInputDialog("Nouveau Prénom :", rs.getString("prenom"));
                        }
                        if (adresseCheckBox.isSelected()) {
                            newAdresse = JOptionPane.showInputDialog("Nouvelle Adresse :", rs.getString("adresse"));
                        }
                        if (specialiteCheckBox.isSelected()) {
                            newSpecialite = JOptionPane.showInputDialog("Nouvelle Spécialité :", rs.getString("specialite"));
                        }

                        stmn.executeUpdate("UPDATE Enseignant SET cin = '" + newCin + "', nom = '" + newNom + "', prenom = '" +
                                newPrenom + "', adresse = '" + newAdresse + "', specialite = '" + newSpecialite + "' WHERE cin = '" + cin + "'");
                        JOptionPane.showMessageDialog(null, "Enseignant modifié avec succès !");
                        actualiser(); // Actualiser la table après la modification
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun enseignant trouvé avec ce CIN.");
                }
            } catch (Exception e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'enseignant : " + e.getLocalizedMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez entrer un CIN valide.");
        }
    }

    

    private void filterStudentsByTeacher(String teacherCin) {
        model.setRowCount(0);
        try {
            stmn = conn.obtenirconnexion().createStatement();
            ResultSet rs = stmn.executeQuery("SELECT * FROM Etudiant WHERE enseignant_cin = '" + teacherCin + "'");
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("cin"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("classe"),
                        rs.getString("enseignant_cin"),
                        rs.getString("encadreur_de_stage_cin"),
                        rs.getString("projetpfe_id"),
                        rs.getString("projetpfe_grpjury_id"),
                        rs.getString("projetpfe_personneladmin_cin_adm")
                });
            }
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Erreur lors du filtrage des étudiants : " + e.getLocalizedMessage());
        }
    }
    private void filterTable(String query) {
        TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EnseignantsFrame::new);
    }
}
