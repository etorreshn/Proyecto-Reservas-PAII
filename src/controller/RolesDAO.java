/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bd.ConexionBD;
import dto.RolesDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolesDAO implements BDOperations {

    @Override
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM roles";

        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                RolesDTO rol = new RolesDTO(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("activo")
                );
                lista.add(rol);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los roles: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Object getById(int id) {
        RolesDTO rol = null;
        String sql = "SELECT * FROM roles WHERE id = ?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = new RolesDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("activo")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el rol por ID: " + e.getMessage());
        }
        return rol;
    }

    @Override
    public boolean insert(Object object) {
        if (object instanceof RolesDTO) {
            RolesDTO rol = (RolesDTO) object;
            String sql = "INSERT INTO roles (nombre, descripcion, activo) VALUES (?, ?, ?)";

            try (Connection con = ConexionBD.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                
                ps.setString(1, rol.getNombre());
                ps.setString(2, rol.getDescripcion());
                ps.setInt(3, rol.getActivo());
                
                return ps.executeUpdate() > 0;
                
            } catch (SQLException e) {
                System.err.println("Error al insertar el rol: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        if (object instanceof RolesDTO) {
            RolesDTO rol = (RolesDTO) object;
            String sql = "UPDATE roles SET nombre = ?, descripcion = ?, activo = ? WHERE id = ?";

            try (Connection con = ConexionBD.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                
                ps.setString(1, rol.getNombre());
                ps.setString(2, rol.getDescripcion());
                ps.setInt(3, rol.getActivo());
                ps.setInt(4, rol.getId());
                
                boolean updated = ps.executeUpdate() > 0;
                
                if (updated) {
                    ejecutarProcedimientoActualizarUsuarios(rol.getId());
                }
                
                return updated;
                
            } catch (SQLException e) {
                System.err.println("Error al actualizar el rol: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM roles WHERE id = ?";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar el rol: " + e.getMessage());
        }
        return false;
    }

    private void ejecutarProcedimientoActualizarUsuarios(int idRol) {
        String sql = "{ CALL actualizarActivoUsuariosPorRol(?) }";
        
        try (Connection con = ConexionBD.getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, idRol);
            cs.execute();
            
        } catch (SQLException e) {
            System.err.println("Error al ejecutar el procedimiento almacenado: " + e.getMessage());
        }
    }
}