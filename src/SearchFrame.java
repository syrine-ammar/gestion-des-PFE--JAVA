package trial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SearchFrame extends JFrame {

    public SearchFrame(String selectedTable) {
        setTitle("Effectuer une recherche");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Panel for filter selection and search value
        JPanel searchPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(searchPanel, BorderLayout.CENTER);

        // Determine filters based on selected table
        String[] filters = getFiltersForTable(selectedTable);

        // Add filter selection dropdown
        JLabel filterLabel = new JLabel("Sélectionner un filtre:");
        JComboBox<String> filterComboBox = new JComboBox<>(filters);
        searchPanel.add(filterLabel);
        searchPanel.add(filterComboBox);

        // Add search value text field
        JLabel searchLabel = new JLabel("Valeur de recherche:");
        JTextField searchTextField = new JTextField();
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);

        // Add search button
        JButton searchButton = new JButton("Rechercher");
        mainPanel.add(searchButton, BorderLayout.SOUTH);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get selected filter and search value
                String selectedFilter = (String) filterComboBox.getSelectedItem();
                String searchValue = searchTextField.getText();

                // Handle search operation (e.g., display search results in a new frame)
                performSearch(selectedTable, selectedFilter, searchValue);
            }
        });
    }

    // Method to get filters based on selected table
    private String[] getFiltersForTable(String selectedTable) {
        switch (selectedTable) {
            case "etudiant":
                return new String[]{"CIN", "Nom", "Prénom", "Classe", "Enseignant CIN", "Encadreur de Stage CIN", "Projet PFE ID", "Projet PFE Groupe Jury ID", "Projet PFE Personnel Admin CIN"};
            case "enseignant":
                return new String[]{"CIN", "Nom", "Prénom", "Adresse", "Spécialité"};
            case "president":
                return new String[]{"CIN", "Nom", "Prénom", "Adresse"};
            case "examinateur":
                return new String[]{"CIN", "Nom", "Prénom", "Adresse"};
            case "rapporteur":
                return new String[]{"CIN", "Nom", "Prénom", "Adresse", "Rapporteur"};
            case "personneladmin":
                return new String[]{"CIN", "Nom", "Prénom", "Adresse"};
            case "encadreur_de_stage":
                return new String[]{"CIN", "Nom", "Prénom", "Adresse", "Entreprise"};
            case "personne_invitee":
                return new String[]{"CIN", "Nom", "Prénom", "Adresse"};
            case "Assistance_au_pfe":
                return new String[]{"Projet PFE ID", "Personne CIN", "Salle ID", "Date PFE"};
            case "grpjury":
                return new String[]{"Spécialité", "Président CIN", "Examinateur CIN", "Rapporteur CIN", "Groupe Jury ID"};
            case "projetpfe":
                return new String[]{"ID", "Date Projet", "Heure", "Type", "Titre", "Valide", "Binôme", "Groupe Jury ID", "Personnel Admin CIN", "Note"};
            case "encadreur":
                return new String[]{"CIN", "Nom", "Prénom", "Adresse", "Entreprise", "Groupe Jury ID"};
            default:
                return new String[]{};
        }
    }

    // Method to handle search operation
    private void performSearch(String selectedTable, String filter, String searchValue) {
        // Initialize a StringBuilder to construct the SQL query
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ");

        // Append the selected table name to the query
        queryBuilder.append(selectedTable);

        // Append the WHERE clause with the specified filter and search value
        queryBuilder.append(" WHERE ").append(filter).append(" = ?");

        // Define variables for database connection and query execution
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Establish a connection to the database
            Connecter connecter = new Connecter();
            connection = connecter.obtenirconnexion();

            // Create a PreparedStatement for executing the query
            preparedStatement = connection.prepareStatement(queryBuilder.toString());

            // Set the search value as a parameter in the PreparedStatement
            preparedStatement.setString(1, searchValue);

            // Execute the query and retrieve the result set
            resultSet = preparedStatement.executeQuery();

            // Create a JTable to display the search results
            JTable resultTable = new JTable(buildTableModel(resultSet));

            // Create a JScrollPane to contain the table
            JScrollPane scrollPane = new JScrollPane(resultTable);

            // Create a new JFrame to display the search results
            JFrame resultFrame = new JFrame("Search Results");
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            resultFrame.setSize(800, 600);
            resultFrame.setLocationRelativeTo(null);
            resultFrame.add(scrollPane);
            resultFrame.setVisible(true);

        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        } finally {
            // Close the database resources in a finally block to ensure they are always closed
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to build a table model from a ResultSet
    public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();

        // Get column names
        int columnCount = metaData.getColumnCount();
        Vector<String> columnNames = new Vector<>();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Get data from the result set
        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> row = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(resultSet.getObject(columnIndex));
            }
            data.add(row);
        }

        // Create and return the table model
        return new DefaultTableModel(data, columnNames);
    }
}

