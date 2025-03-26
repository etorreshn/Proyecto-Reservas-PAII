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
import dto.UsuariosDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    
    public List<Object> getAll() {
    List<Object> lista = new ArrayList<>();
    String sql = "SELECT id, usuario, nombre, email, contrasena, id_rol, activo FROM usuarios";
    
    try (Connection con = ConexionBD.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            UsuariosDTO usuario = new UsuariosDTO();
            usuario.setId(rs.getInt("id"));
            usuario.setUsuario(rs.getString("usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setEmail(rs.getString("email"));
            usuario.setContraseña(rs.getString("contrasena"));
            usuario.setIdRol(rs.getInt("id_rol"));
            usuario.setActivo(rs.getInt("activo"));
            
            lista.add(usuario);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return lista;
}
    public boolean insertar(UsuariosDTO usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, contrasena, id_rol) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());
            ps.setInt(4, usuario.getIdRol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<UsuariosDTO> listar() {
        List<UsuariosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new UsuariosDTO(
                    rs.getInt("id"),
                        rs.getString("usuario"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("contrasena"),
                    rs.getInt("id_rol"),
                        rs.getInt("activo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
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

