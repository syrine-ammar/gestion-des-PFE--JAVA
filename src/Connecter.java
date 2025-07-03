package trial;

import java.sql.*;

public class Connecter {
    Connection con;

    public Connecter() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/GestionPfe", "root", "nadaAysql15-03");
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    Connection obtenirconnexion() {
        return con;
    }
}
