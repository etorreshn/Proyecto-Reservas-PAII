/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dto.ReservasDTO; // Usamos ReservasDTO en lugar de Reservas

/**
 *
 * @author Edwin
 */
public class ReservasDAO implements BDOperations {
    
@Override
public List<Object> getAll() {
    List<Object> lista = new ArrayList<>();
    String sql = "SELECT * FROM reservas";
    try (Connection con = ConexionBD.getConnection()) {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            // Usamos ReservasDTO para agregarlo a la lista de Object
            lista.add(new ReservasDTO(
                    rs.getInt("id_reserva"),
                    rs.getInt("id_huesped"),
                    rs.getInt("id_habitacion"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getInt("activo")
            ));
        }
    } catch (SQLException e) {
        System.err.println("Error al listar Reservas: " + e.getMessage());
    }
    return lista;
}

    
    @Override
    public boolean insert(Object object) {
        ReservasDTO oReservas = (ReservasDTO) object;
        String sql = "INSERT INTO reservas (id_reserva, id_huesped, id_habitacion, fecha_inicio, fecha_fin, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, oReservas.getId_reserva());
            pst.setInt(2, oReservas.getId_huesped());
            pst.setInt(3, oReservas.getId_habitacion());
            pst.setDate(4, new java.sql.Date(oReservas.getFecha_inicio().getTime()));
            pst.setDate(5, new java.sql.Date(oReservas.getFecha_fin().getTime()));
            pst.setInt(6, oReservas.getActivo());
            
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar reserva: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean update(Object object) {
        ReservasDTO oReservas = (ReservasDTO) object;
        String sql = "UPDATE reservas SET id_huesped=?, id_habitacion=?, fecha_inicio=?, fecha_fin=?, activo=? WHERE id_reserva=?";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, oReservas.getId_huesped());
            pst.setInt(2, oReservas.getId_habitacion());
            pst.setDate(3, new java.sql.Date(oReservas.getFecha_inicio().getTime()));
            pst.setDate(4, new java.sql.Date(oReservas.getFecha_fin().getTime()));
            pst.setInt(5, oReservas.getActivo());
            pst.setInt(6, oReservas.getId_reserva());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM reservas WHERE id_reserva=?";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar reserva: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Object getById(int id) {
        ReservasDTO reserva = null;
        String sql = "SELECT * FROM reservas WHERE id_reserva = ?";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                reserva = new ReservasDTO(
                        rs.getInt("id_reserva"),
                        rs.getInt("id_huesped"),
                        rs.getInt("id_habitacion"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getInt("activo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la reserva: " + e.getMessage());
        }
        return reserva;
    }
}
