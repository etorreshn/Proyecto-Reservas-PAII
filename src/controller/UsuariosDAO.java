/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import utils.PasswordUtils;
import bd.ConexionBD;
import dto.UsuariosDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    
    public List<Object> getAll() throws SQLException {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT id, usuario, nombre, email, contrasena, id_rol, activo, salt FROM usuarios";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                UsuariosDTO usuario = new UsuariosDTO();
                usuario.setId(rs.getInt("id"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setId_Rol(rs.getInt("id_rol"));
                usuario.setActivo(rs.getInt("activo"));
               // usuario.setSalt(rs.getString("salt"));
                
                lista.add(usuario);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener usuarios: " + e.getMessage(), e);
        }
        return lista;
    }
    
    public boolean insertar(UsuariosDTO usuario) {
        String sql = "INSERT INTO usuarios (usuario, nombre, email, contrasena, id_rol, activo, salt) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            String salt = PasswordUtils.generateSalt();
            String contrasenaHash = PasswordUtils.hashPassword(usuario.getContrasena(), salt);
            
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, contrasenaHash);
            ps.setInt(5, usuario.getId_Rol());
            ps.setInt(6, usuario.getActivo());
            System.err.println("Valor de activo " + usuario.getActivo());
            ps.setString(7, salt);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en insertar(): " + e.getMessage());
            return false;
        }
    }
    
public boolean actualizar(UsuariosDTO usuario) {
    // Validar campo activo
    if(usuario.getActivo() != 0 && usuario.getActivo() != 1) {
        System.err.println("Valor inválido para activo: " + usuario.getActivo());
        return false;
    }

    // Consulta que actualiza con o sin contraseña
    boolean actualizarContrasena = (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty());
    
    String sql = "UPDATE usuarios SET usuario = ?, nombre = ?, email = ?, " +
                (actualizarContrasena ? "contrasena = ?, salt = ?, " : "") +
                "id_rol = ?, activo = ? WHERE id = ?";
    
    try (Connection con = ConexionBD.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        
        System.out.println("DEBUG - Valores para actualizar:");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Usuario: " + usuario.getUsuario());
        System.out.println("Activo: " + usuario.getActivo());
        System.out.println("Actualizar contraseña?: " + actualizarContrasena);
        
        int paramIndex = 1;
        ps.setString(paramIndex++, usuario.getUsuario());
        ps.setString(paramIndex++, usuario.getNombre());
        ps.setString(paramIndex++, usuario.getEmail());
        
        if (actualizarContrasena) {
            String salt = PasswordUtils.generateSalt();
            String contrasenaHash = PasswordUtils.hashPassword(usuario.getContrasena(), salt);
            ps.setString(paramIndex++, contrasenaHash);
            ps.setString(paramIndex++, salt);
            
            System.out.println("DEBUG - Nueva contraseña hash: " + contrasenaHash);
            System.out.println("DEBUG - Nuevo salt: " + salt);
        }
        
        ps.setInt(paramIndex++, usuario.getId_Rol());  // Asegúrate que el getter se llame exactamente así
        ps.setInt(paramIndex++, usuario.getActivo());
        ps.setInt(paramIndex, usuario.getId());
        
        int filasActualizadas = ps.executeUpdate();
        System.out.println("DEBUG - Filas afectadas: " + filasActualizadas);
        
        return filasActualizadas > 0;
    } catch (SQLException e) {
        System.err.println("Error en actualizar(): " + e.getMessage());
        e.printStackTrace();  // Para ver el stack trace completo
        return false;
    }
}
    
    // ... (métodos listar y eliminar se mantienen igual)
}