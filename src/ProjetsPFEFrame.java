package trial;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ProjetsPFEFrame extends JFrame {

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

    public ProjetsPFEFrame() {
        /* Partie pour charger la base de données des projets PFE */
        model.addColumn("id");
        model.addColumn("dateProjet");
        model.addColumn("heure");
        model.addColumn("type");
        model.addColumn("titre");
        model.addColumn("valide");
        model.addColumn("binome");
        model.addColumn("grpjury_id");
        model.addColumn("personneladmin_cin_adm");
        model.addColumn("note");
        try {
            stmn = conn.obtenirconnexion().createStatement();
            ResultSet Rs = ((java.sql.Statement) stmn).executeQuery("Select * from ProjetPFE");
            while (Rs.next()) {
                model.addRow(new Object[]{
                	     Rs.getString("id"),
                	    Rs.getDate("dateProjet"),
                	    Rs.getInt("heure"),
                	    Rs.getString("type"),
                	    Rs.getString("titre"),
                	    Rs.getInt("valide"),
                	   Rs.getInt("binome"),
                	    Rs.getInt("grpjury_id"),
                	    Rs.getInt("personneladmin_cin_adm"),
                	    Rs.getInt("note")
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

        String[] labels = {"Date Projet:", "Heure:", "Type:", "Titre:", "Valide:", "Invités:", "Planificateur:", "Binôme:"};
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
        SwingUtilities.invokeLater(ProjetsPFEFrame::new);
    }
}
