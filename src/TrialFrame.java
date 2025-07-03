package trial;

import javax.swing.*;
import java.sql.*;

import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import java.awt.*;
import java.util.Arrays;
import java.util.Vector;
import java.sql.*;

public class TrialFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTable table;

    Connecter conn=new Connecter(); 
    java.sql.Statement stmn;
    ResultSet Rs;
    DefaultTableModel model=new DefaultTableModel();
    
    public TrialFrame() {
    	/* Partie pour charger  la base de données de l'encadreur */
    	model.addColumn("cin");
    	model.addColumn("nom");
    	model.addColumn("prenom");
    	model.addColumn("adresse");
    	model.addColumn("entreprise");
    	try
    	{
    		stmn=conn.obtenirconnexion().createStatement();
    		ResultSet Rs=((java.sql.Statement) stmn).executeQuery("Select * from Encadreur");
    		while (Rs.next())
    		{
    			model.addRow(new Object[]{
    					Rs.getString("cin"),
    					Rs.getString("nom"),
    					Rs.getString("prenom"),
    					Rs.getString("adresse"),
    					Rs.getString("entreprise")
    					//Rs.getString("Projets encadrés")
    				});
    		
    	}
    	}
    	catch(Exception e) {System.err.println(e);}
    	table.setModel(model);
    	
    	
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        getContentPane().setLayout(new BorderLayout()); // Use BorderLayout for the frame

        // Panel for labels and text fields
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] labels = {"CIN:", "Nom:", "Prénom:", "Adresse:", "Entreprise:"};
        for (String label : labels) {
            JLabel jLabel = new JLabel(label);
            JTextField jTextField = new JTextField();
            inputPanel.add(jLabel);
            inputPanel.add(jTextField);
        }

        // Adding additional labels and text fields
        JLabel lblNewLabel = new JLabel("CIN :");
        inputPanel.add(lblNewLabel);

        textField_1 = new JTextField();
        inputPanel.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nom :");
        inputPanel.add(lblNewLabel_1);
        
        textField_2 = new JTextField();
        inputPanel.add(textField_2);
        textField_2.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Prénom");
        inputPanel.add(lblNewLabel_2);
        
        textField_3 = new JTextField();
        inputPanel.add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Adresse");
        inputPanel.add(lblNewLabel_3);
        
        textField_4 = new JTextField();
        inputPanel.add(textField_4);
        textField_4.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Entreprise\r\n");
        inputPanel.add(lblNewLabel_4);
        
        textField_5 = new JTextField();
        inputPanel.add(textField_5);
        textField_5.setColumns(10);
        // Repeat similar steps for the other fields...

        getContentPane().add(inputPanel, BorderLayout.NORTH);

     // Panel for the table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(tablePanel, BorderLayout.CENTER);

        // Panel for buttons
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
        buttonPanel.add(new JLabel("Réalisé par:"));
        buttonPanel.add(rechercherTextField);
        buttonPanel.add(rechercherButton);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TrialFrame::new);
    }
}
