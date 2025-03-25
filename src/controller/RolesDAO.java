/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Edwin
 */
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
                    rs.getString("descripcion")
                );
                lista.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                        rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }

    @Override
    public boolean insert(Object object) {
        if (object instanceof RolesDTO) {
            RolesDTO rol = (RolesDTO) object;
            String sql = "INSERT INTO roles (nombre, descripcion) VALUES (?, ?)";

            try (Connection con = ConexionBD.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                
                ps.setString(1, rol.getNombre());
                ps.setString(2, rol.getDescripcion());
                return ps.executeUpdate() > 0;
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Object object) {
        if (object instanceof RolesDTO) {
            RolesDTO rol = (RolesDTO) object;
            String sql = "UPDATE roles SET nombre = ?, descripcion = ? WHERE id = ?";

            try (Connection con = ConexionBD.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                
                ps.setString(1, rol.getNombre());
                ps.setString(2, rol.getDescripcion());
                ps.setInt(3, rol.getId());
                return ps.executeUpdate() > 0;
                
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
    }


    
    
}