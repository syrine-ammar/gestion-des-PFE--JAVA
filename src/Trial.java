package trial;


import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement; 


public class Trial {
    private Connection connection;

    public Trial() {
        connect();
        performDatabaseOperations();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/GestionPfe", "root", "nadaAysql15-03");
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error loading MySQL driver: " + ex.getMessage());
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
    public void performDatabaseOperations() {
        try {
            Encadreur encadreur = new Encadreur(connection, "Nom", "Prenom", 12345, "Adresse", "Entreprise");
            encadreur.addToDatabase();
            System.out.println("Encadreur added successfully to the database!");
        } catch (SQLException e) {
            System.out.println("Error adding Encadreur to the database: " + e.getMessage());
        }
    
        
        // Perform your database operations here
        // For example, you can insert data into the database using prepared statements
        // Or you can call methods to interact with your database schema
    }

    public static void main(String[] args) {
        new Trial();
    }
}
