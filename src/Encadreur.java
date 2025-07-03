package trial;


import java.util.*;
import java.sql.*;

public class Encadreur extends Personne {
    private String entreprise;
    private Map<Etudiant, String> etudiantsEncadres;
    private Connection connection;

    public Encadreur(Connection connection, String nom, String prenom, int cin, String adresse, String entreprise) {
        super(nom, prenom, cin, adresse);
        this.connection = connection;
        this.entreprise = entreprise;
        this.etudiantsEncadres = new HashMap<>();
    }
    protected void addToDatabase() throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO Encadreur (cin, nom, prenom, adresse, entreprise) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, getCin());
            statement.setString(2, getNom());
            statement.setString(3, getPrenom());
            statement.setString(4, getAdresse());
            statement.setString(5, entreprise);
            statement.executeUpdate();
            System.out.println("Encadreur added successfully to the database!");
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    
    public void setEntreprise(String entreprise) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/GestionPfe", "root", "nadaAysql15-03");
            PreparedStatement statement = connection.prepareStatement("UPDATE Encadreur SET entreprise = ? WHERE cin = ?");
            statement.setString(1, entreprise);
            statement.setInt(2, this.getCin());
            statement.executeUpdate();
            statement.close();
            connection.close();
            this.entreprise = entreprise;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public String getEntreprise() {
        String entreprise = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/GestionPfe", "root", "nadaAysql15-03");
            PreparedStatement statement = connection.prepareStatement("SELECT entreprise FROM Encadreur WHERE cin = ?");
            statement.setInt(1, getCin());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entreprise = resultSet.getString("entreprise");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getting entreprise from the database: " + e.getMessage());
        }
        return entreprise;
    }



    public Map<Etudiant, String> getEtudiantsEncadres() {
        Map<Etudiant, String> etudiantsEncadres = new HashMap<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/GestionPfe", "root", "nadaAysql15-03");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Encadrer WHERE encadreur_cin = ?");
            statement.setInt(1, getCin());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cinEtudiant = resultSet.getInt("etudiant_cin");
                String periodeStage = resultSet.getString("periodeStage");
                // Retrieve Etudiant object using cinEtudiant
                Etudiant etudiant = null;
                etudiant.retrieveEtudiant(cinEtudiant);
                etudiantsEncadres.put(etudiant, periodeStage);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getting etudiants encadres from the database: " + e.getMessage());
        }
        return etudiantsEncadres;
    }

    public void updateEtudiantsEncadres(Map<Etudiant, String> etudiantsEncadres) {
        try {
            // Clear existing entries in the Encadrer table for this Encadreur
            String clearQuery = "DELETE FROM Encadrer WHERE encadreur_cin = ?";
            Connection connection = null;
            PreparedStatement clearStatement = connection.prepareStatement(clearQuery);
            clearStatement.setInt(1, getCin());
            clearStatement.executeUpdate();

            // Add new entries to the Encadrer table
            String addQuery = "INSERT INTO Encadrer (encadreur_cin, etudiant_cin, periodeStage) VALUES (?, ?, ?)";
            PreparedStatement addStatement = connection.prepareStatement(addQuery);
            for (Map.Entry<Etudiant, String> entry : etudiantsEncadres.entrySet()) {
                addStatement.setInt(1, getCin());
                addStatement.setInt(2, entry.getKey().getCin());
                addStatement.setString(3, entry.getValue());
                addStatement.executeUpdate();
            }

            // Update the local map
            this.etudiantsEncadres = etudiantsEncadres;
        } catch (SQLException e) {
            System.out.println("Error updating etudiants encadres in the database: " + e.getMessage());
        }
    }


    public void addEtudiantEncadre(Etudiant etudiant, String periodeStage) {
    	Connection connection=null;
        try {
            // Check if the student is already added
            if (etudiantsEncadres.containsKey(etudiant)) {
                throw new RuntimeException("Etudiant déjà ajouté !");
            } else {
                // Add the student to the database table `Encadrer`
                String query = "INSERT INTO Encadrer (encadreur_cin, etudiant_cin, periodeStage) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, getCin());
                preparedStatement.setInt(2, etudiant.getCin());
                preparedStatement.setString(3, periodeStage);
                preparedStatement.executeUpdate();

                // Add the student to the map
                etudiantsEncadres.put(etudiant, periodeStage);
            }
        } catch (SQLException e) {
            System.out.println("Error adding student to the Encadrer table in the database: " + e.getMessage());
        }
    }


    public void removeEtudiantEncadre(Etudiant etudiant) {
        try {
            // Remove the student from the local map
            etudiantsEncadres.remove(etudiant);

            // Delete the student from the Encadrer table in the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/GestionPfe", "root", "nadaAysql15-03");
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Encadrer WHERE encadreur_cin = ? AND etudiant_cin = ?");
            stmt.setInt(1, getCin());
            stmt.setInt(2, etudiant.getCin());
            stmt.executeUpdate();
            System.out.println("Student removed successfully from the Encadrer table in the database!");
            conn.close();
        } catch (NullPointerException e) {
            System.out.println("Erreur !");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur !");
            e.printStackTrace();
        }
    }


}