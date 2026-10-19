package co.edu.unilibre.implDAO;

import co.edu.unilibre.datos.Moto;
import co.edu.unilibre.datos.RegistroPago;
import co.edu.unilibre.gestion.GenericDAO;
import co.edu.unilibre.gestorConexion.DatabaseFacade;

import java.sql.*;
import java.util.List;

public class ImplDAO implements GenericDAO {
    public ImplDAO() {
        DatabaseFacade.inicializarBaseDeDatos();
    }

    @Override
    public Object crear(Object o) {
        if(o instanceof Moto){

            String sql = "INSERT INTO motos (cedulaPropietario, marca, placa) VALUES (?, ?, ?) ";

            try (Connection conn = DatabaseFacade.getConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, ((Moto) o).obtenerCedulaPropietario());
                ps.setString(2, ((Moto) o).obtenerMarca());
                ps.setString(3,((Moto) o).obtenerPlaca());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof RegistroPago) {

            int idMoto = obtenerPorCedula(((RegistroPago) o).obtenerMoto().obtenerCedulaPropietario());
            String sql = "INSERT INTO registros_pagos (valorPagado, valorAPagar, tipoPago,fechaHoraPago,fechaHoraIngreso,fechaHoraSalida,moto_id) VALUES (?,?,?,?,?,?,?) ";

            try (Connection conn = DatabaseFacade.getConexion();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, ((RegistroPago) o).obtenerValorPagado());
                ps.setDouble(2, ((RegistroPago) o).obtenerValorAPagar());
                ps.setString(3, ((RegistroPago) o).obtenerTipoPago() !=null
                        ? ((RegistroPago) o).obtenerTipoPago().name()
                        :"N/A"
            );
                ps.setTimestamp(4,
                        ((RegistroPago) o).obtenerFechaHoraPago() != null
                                ? Timestamp.valueOf(((RegistroPago) o).obtenerFechaHoraPago())
                                : null
                );
                ps.setTimestamp(5,
                        ((RegistroPago) o).obtenerFechaHoraIngreso() != null
                                ? Timestamp.valueOf(((RegistroPago) o).obtenerFechaHoraIngreso())
                                : null
                );
                ps.setTimestamp(6,
                        ((RegistroPago) o).obtenerFechaHoraSalida() != null
                                ? Timestamp.valueOf(((RegistroPago) o).obtenerFechaHoraSalida())
                                : null
                );
                ps.setInt(7,idMoto);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return o;
    }

    @Override
    public int obtenerPorCedula(String cedula) {
        String sql = "SELECT id FROM motos WHERE cedulaPropietario = ?";
        try (Connection conn = DatabaseFacade.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)){
                 ps.setString(1,cedula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void editar(Object o) {

    }

    @Override
    public void borrar(Object id) {

    }

    @Override
    public List consultarTodos() {
        return List.of();
    }
}
