/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import bd.ConexionBD;
import dto.HabitacionConHuespedDTO;
import dto.HabitacionesDTO;
import model.Habitaciones;

public class HabitacionesDAO implements BDOperations {

    @Override
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones";
        try (Connection con = ConexionBD.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Habitaciones(
                        rs.getInt("id_habitacion"),
                        rs.getString("tipo"),
                        rs.getDouble("precio"),
                        rs.getInt("capacidad"),
                        rs.getObject("id_huesped") != null ? rs.getInt("id_huesped") : null,
                        rs.getInt("activo")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar Habitaciones: " + e.getMessage());
        }
        return lista;
    }
    
    public List<HabitacionConHuespedDTO> getAllHabitacionesConHuespedes() {
    List<HabitacionConHuespedDTO> habitaciones = new ArrayList<>();
    String sql = "SELECT h.*, hs.nombre AS nombre_huesped, hs.email, hs.telefono " +
                 "FROM habitaciones h " +
                 "LEFT JOIN huespedes hs ON h.id_huesped = hs.id_huesped";
    
    try (Connection con = ConexionBD.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            HabitacionConHuespedDTO habitacion = new HabitacionConHuespedDTO(
                rs.getInt("id_habitacion"),
                rs.getString("tipo"),
                rs.getDouble("precio"),
                rs.getInt("capacidad"),
                rs.getObject("id_huesped") != null ? rs.getInt("id_huesped") : null,
                rs.getString("nombre_huesped"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getInt("activo")
            );
            habitaciones.add(habitacion);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener habitaciones con huéspedes: " + e.getMessage());
    }
    return habitaciones;
}

    @Override
    public boolean insert(Object object) {
        if (!(object instanceof HabitacionesDTO)) {
            System.err.println("Error: El objeto no es de tipo HabitacionesDTO");
            return false;
        }
        
        HabitacionesDTO dto = (HabitacionesDTO) object;
        String sql = "INSERT INTO habitaciones (tipo, precio, capacidad, id_huesped, activo) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, dto.getTipo());
            pst.setDouble(2, dto.getPrecio());
            pst.setInt(3, dto.getCapacidad());
            
            if (dto.getId_habitacion() != -1) {
                pst.setInt(4, dto.getId_huesped());
            } else {
                pst.setNull(4, Types.INTEGER);
            }
            
            pst.setInt(5, dto.getActivo());
            
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar habitación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        if (!(object instanceof Habitaciones)) {
            System.err.println("Error: El objeto no es de tipo Habitaciones");
            return false;
        }
        
        Habitaciones habitacion = (Habitaciones) object;
        String sql = "UPDATE habitaciones SET tipo=?, precio=?, capacidad=?, id_huesped=?, activo=? WHERE id_habitacion=?";
        
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, habitacion.getTipo());
            pst.setDouble(2, habitacion.getPrecio());
            pst.setInt(3, habitacion.getCapacidad());
            
            if (habitacion.getId_huesped() != null) {
                pst.setInt(4, habitacion.getId_huesped());
            } else {
                pst.setNull(4, Types.INTEGER);
            }
            
            pst.setInt(5, habitacion.getActivo());
            pst.setInt(6, habitacion.getId_habitacion());
            
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar habitación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM habitaciones WHERE id_habitacion=?";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar habitación: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Object getById(int id) {
        Habitaciones habitacion = null;
        String sql = "SELECT * FROM habitaciones WHERE id_habitacion = ?";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                habitacion = new Habitaciones(
                        rs.getInt("id_habitacion"),
                        rs.getString("tipo"),
                        rs.getDouble("precio"),
                        rs.getInt("capacidad"),
                        rs.getObject("id_huesped") != null ? rs.getInt("id_huesped") : null,
                        rs.getInt("activo")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener habitación por ID: " + e.getMessage());
        }
        return habitacion;
    }

    // Métodos adicionales que no son parte de BDOperations pero pueden ser útiles
    public List<HabitacionesDTO> obtenerHabitacionesDisponibles() {
        List<HabitacionesDTO> lista = new ArrayList<>();
        String sql = "SELECT id_habitacion, tipo FROM habitaciones WHERE activo = 1 AND id_huesped IS NULL";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                HabitacionesDTO dto = new HabitacionesDTO();
                dto.setId_habitacion(rs.getInt("id_habitacion"));
                dto.setTipo(rs.getString("tipo"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener habitaciones disponibles: " + e.getMessage());
        }
        return lista;
    }
    
public boolean insertarHabitacion(HabitacionConHuespedDTO habitacion) {
        String sql = "INSERT INTO habitaciones (tipo, precio, capacidad, id_huesped, activo) " +
                    "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, habitacion.getTipo());
            stmt.setDouble(2, habitacion.getPrecio());
            stmt.setInt(3, habitacion.getCapacidad());
            
            // Manejo de id_huesped que puede ser null
            if (habitacion.getIdHuesped() != null) {
                stmt.setInt(4, habitacion.getIdHuesped());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            
            stmt.setInt(5, habitacion.getActivo());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        habitacion.setIdHabitacion(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar habitación: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizarHabitacion(HabitacionConHuespedDTO habitacion) {
        String sql = "UPDATE habitaciones SET tipo = ?, precio = ?, capacidad = ?, " +
                    "id_huesped = ?, activo = ? WHERE id_habitacion = ?";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, habitacion.getTipo());
            stmt.setDouble(2, habitacion.getPrecio());
            stmt.setInt(3, habitacion.getCapacidad());
            
            // Manejo de id_huesped que puede ser null
            if (habitacion.getIdHuesped() != null) {
                stmt.setInt(4, habitacion.getIdHuesped());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            
            stmt.setInt(5, habitacion.getActivo());
            stmt.setInt(6, habitacion.getIdHabitacion());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar habitación: " + e.getMessage());
        }
        return false;
    }
    
    // Método específico para cambiar el estado
    public boolean cambiarEstadoHabitacion(int idHabitacion, int estado) {
        String sql = "UPDATE habitaciones SET activo = ? WHERE id_habitacion = ?";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, estado);
            stmt.setInt(2, idHabitacion);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al cambiar estado de habitación: " + e.getMessage());
        }
        return false;
    }
}

