package trial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class MainApplicationWindow extends JFrame {

    private Connection connection;

    public MainApplicationWindow(Connection connection) {
        this.connection = connection;

        setTitle("Gestion des Projets PFE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Title label
        JLabel titleLabel = new JLabel("Gestion des Projets PFE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.RED);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Two small divs with buttons on the left and right
        JPanel buttonDivLeft = new JPanel();
        buttonDivLeft.setBackground(Color.CYAN);
        mainPanel.add(buttonDivLeft, BorderLayout.WEST);

        JPanel buttonDivRight = new JPanel();
        buttonDivRight.setBackground(Color.YELLOW);
        mainPanel.add(buttonDivRight, BorderLayout.EAST);

        // Add buttons to the divs
        JButton parcourirButton = new JButton("Parcourir mes données");
        buttonDivLeft.add(parcourirButton);

        JButton rechercherButton = new JButton("Effectuer une recherche");
        buttonDivRight.add(rechercherButton);

        // Add "Statistics" button
        JButton statisticsButton = new JButton("Statistics");
        buttonDivRight.add(statisticsButton);

        // Label for selecting tables
        JLabel tableLabel = new JLabel("Choisir votre tableau:");
        tableLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tableLabel.setForeground(Color.BLUE);
        tableLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(tableLabel, BorderLayout.SOUTH);

        // Dropdown menu for selecting tables
        JPanel tablePanel = new JPanel();
        JComboBox<String> tableComboBox = new JComboBox<>(new String[]{"etudiants", "enseignants", "examinateurs", "presidants", "rapporteurs", "encadreurs", "encadreur_stage", "goupe_jury", "projets_pfe"});
        tablePanel.add(tableComboBox);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Add action listener for "Parcourir mes données" button
        parcourirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedTable = (String) tableComboBox.getSelectedItem();
                if (selectedTable != null) {
                    openTableFrame(selectedTable);
                } else {
                    JOptionPane.showMessageDialog(MainApplicationWindow.this, "Veuillez sélectionner une table.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for "Effectuer une recherche" button
        rechercherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedTable = (String) tableComboBox.getSelectedItem();
                if (selectedTable != null) {
                    openSearchFrame(selectedTable);
                } else {
                    JOptionPane.showMessageDialog(MainApplicationWindow.this, "Veuillez sélectionner une table.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for "Statistics" button
        statisticsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openStatisticsFrame();
            }
        });
    }

    // Method to open the statistics frame
    private void openStatisticsFrame() {
        StatisticsFrame statisticsFrame = new StatisticsFrame();
        statisticsFrame.setVisible(true);
    }

    // Method to open the search frame
    private void openSearchFrame(String selectedTable) {
    	SearchFrame searchFrame = new SearchFrame(selectedTable);
        searchFrame.setVisible(true);
    }

    // Method to open the frame corresponding to the selected table
    private void openTableFrame(String selectedTable) {
        JFrame tableFrame = null;
        switch (selectedTable) {
            case "etudiants":
                tableFrame = new EtudiantsFrame();
                break;
            case "enseignants":
                tableFrame = new EnseignantsFrame();
                break;
            case "examinateurs":
                tableFrame = new ExaminateursFrame();
                break;
            case "presidants":
                tableFrame = new PresidentsFrame();
                break;
            case "rapporteurs":
                tableFrame = new RapporteursFrame();
                break;
            case "encadreurs":
                tableFrame = new TrialFrame();
                break;
           /* case "encadreur_stage":
                tableFrame = new EncadreurStageFrame(); 
                break; */
            case "goupe_jury":
                tableFrame = new GroupeJuryFrame();
                break;
            case "projets_pfe":
                tableFrame = new ProjetsPFEFrame();
                break;
            default:
                break;
        }

        if (tableFrame != null) {
            tableFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen();
            }
        });
    }
}