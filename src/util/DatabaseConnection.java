
/*
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/ecomove";
    private static final String USER = "postgres";
    private static final String PASSWORD = "User_Password";

    private DatabaseConnection() {
        // Constructeur privé pour empêcher l'instanciation
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
*/





package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/ecomove";
    private static final String USER = "postgres";
    private static final String PASSWORD = "User_Password";

    private static Connection connection;

    // Constructeur privé pour empêcher l'instanciation
    private DatabaseConnection() {
        // Vous pouvez éventuellement charger le driver JDBC ici si nécessaire
    }

    // Méthode statique pour obtenir la connexion
    public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                // Vous pouvez lancer une exception ou gérer l'erreur d'une autre manière
            }
        }
        return connection;
    }

    // Méthode statique pour fermer la connexion
    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Réinitialiser la connexion après la fermeture
            } catch (SQLException e) {
                e.printStackTrace();
                // Vous pouvez lancer une exception ou gérer l'erreur d'une autre manière
            }
        }
    }
}
