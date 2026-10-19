package co.edu.unilibre.gestorConexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseFacade {
    private static FabricaConexion.TipoDB ambienteActual = FabricaConexion.TipoDB.H2;

    public static void configurarAmbiente(FabricaConexion.TipoDB tipo) {
        ambienteActual = tipo;
    }

    public static Connection getConexion() {
        try {
            return   FabricaConexion.obtenerConexionProp(ambienteActual);
        } catch (SQLException e) {
            System.err.println("Error de fachada al conectar: " + e.getMessage());
            return null;
        }
    }
    public static boolean inicializarBaseDeDatos() {
        String sqlMotos = "CREATE TABLE IF NOT EXISTS motos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "cedulaPropietario VARCHAR(20) NOT NULL UNIQUE, " +
                "placa VARCHAR(20) NOT NULL UNIQUE, " +
                "marca VARCHAR(20) NOT NULL "+")";
        String sqlRegistrosPagos = "CREATE TABLE IF NOT EXISTS registros_pagos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "valorPagado DECIMAL(10,2) NOT NULL, " +
                "valorAPagar DECIMAL(10,2) NOT NULL, " +
                "tipoPago VARCHAR(50) NOT NULL, " +
                "fechaHoraPago TIMESTAMP, " +
                "fechaHoraIngreso TIMESTAMP NOT NULL, " +
                "fechaHoraSalida TIMESTAMP, " +
                "moto_id INT, " +
                "FOREIGN KEY (moto_id) REFERENCES motos(id)" +
                ")";

        try (Connection conn = DatabaseFacade.getConexion();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlMotos);
            System.out.println("Tabla 'motos' verificada/creada.");
            stmt.execute(sqlRegistrosPagos);
            System.out.println("Tabla 'registrosPagos' verificada/creada.");
            return true;
        } catch (SQLException e) {
            System.out.println("hola");
            e.printStackTrace();
        }
        return false;
    }
}
