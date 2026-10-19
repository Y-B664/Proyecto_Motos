package co.edu.unilibre.gestorConexion;

import co.edu.unilibre.Config.config.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexion {
    public enum TipoDB { H2, MYSQL, POSTGRESQL }

    public static Connection obtenerConexionProp(TipoDB tipo) throws SQLException {
        switch (tipo) {
            case MYSQL:
                try{
                    String url = ConfigReader.getProperty("db.mysql.url");
                    String user = ConfigReader.getProperty("db.mysql.user");
                    String pass = ConfigReader.getProperty("db.mysql.pass");
                    return DriverManager.getConnection(url, user, pass);
                } catch (SQLException e) {
                    System.err.println("Error de conexión: " + e.getMessage());
                    return null;
                }
            case H2:
                try{
                    String url = ConfigReader.getProperty("db.h2.url");
                    String user = ConfigReader.getProperty("db.h2.user");
                    String pass = ConfigReader.getProperty("db.h2.pass");
                    return DriverManager.getConnection(url, user, pass);
                } catch (SQLException e) {
                    System.err.println("Error de conexión: " + e.getMessage());
                    return null;
                }
            default:
                throw new IllegalArgumentException("Base de datos no soportada");
        }
    }

}
