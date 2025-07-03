package trial;

import java.util.*;
import java.sql.*;

public class Etudiant extends Personne {
    private Enseignant enseignantEncadreur;
    private ProjetPFE projet;
    private String classe;
    
    public Etudiant(String nom, String prenom, int cin, String adresse, String classe) {
        super(nom, prenom, cin, adresse);
        this.classe = classe;
    }

    public Etudiant retrieveEtudiant(int cin) {
        Etudiant etudiant = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/GestionPfe", "root", "nadaAysql15-03");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Etudiant WHERE cin = ?");
            statement.setInt(1, cin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String adresse = resultSet.getString("adresse");
                String classe = resultSet.getString("classe");
                etudiant = new Etudiant(nom, prenom, cin, adresse, classe);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving etudiant from the database: " + e.getMessage());
        }
        return etudiant;
    }

    // Method to set the supervising teacher
    public void setEnseignantEncadreur(Enseignant enseignant) {
        this.enseignantEncadreur = enseignant;
    }

    public Enseignant getEnseignantEncadreur() {
        return enseignantEncadreur;}

    public void setProjetPFE(ProjetPFE projet) { 
        this.projet = projet;}

    public ProjetPFE getProjetPFE() { 
        return projet;}

    // Method to display student's information
    public void afficherDonnees() {
        System.out.println("Nom: " + getNom());
        System.out.println("Prenom: " + getPrenom());
        System.out.println("CIN: " + getCin());
        System.out.println("Adresse: " + getAdresse());
        System.out.println("Classe: " + classe);
        if (enseignantEncadreur != null) {
            System.out.println("Enseignant Encadreur: " + enseignantEncadreur.getNom() +" " + enseignantEncadreur.getPrenom());
        }
        if (projet != null) {
            System.out.println("Projet PFE: " + projet.getTitre()); 
        }
    }

}
