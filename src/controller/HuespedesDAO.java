/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import bd.ConexionBD;
import dto.HuespedesDTO;
import model.Huespedes;


/**
 *
 * @author Edwin
 */
public class HuespedesDAO implements BDOperations{


    @Override
    public List<Object> getAll(){
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM huespedes";
        try (Connection con = ConexionBD.getConnection() ){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                lista.add(new Huespedes(
                        rs.getInt("id_huesped"),
                        rs.getString("Nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getInt("activo")
                ));
            }           
            
        }catch(SQLException e){
            System.err.println("Error al listar Huespedes: " + e.getMessage());
        }     
        return lista;
    }
    
@Override
public boolean insert(Object object) {
    if (object instanceof HuespedesDTO) {
        // Convertir HuespedesDTO a Huespedes
        HuespedesDTO dto = (HuespedesDTO) object;
        Huespedes huesped = new Huespedes(
            dto.getId_huesped(),
            dto.getNombre(),
            dto.getEmail(),
            dto.getTelefono(),
            dto.getActivo()
        );

        // Ahora que tenemos un objeto Huespedes, realizamos la inserción en la base de datos
        String sql = "INSERT INTO huespedes (nombre, email, telefono, activo) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, huesped.getNombre());
            pst.setString(2, huesped.getEmail());
            pst.setString(3, huesped.getTelefono());
            pst.setInt(4, huesped.getActivo());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar huesped: " + e.getMessage());
            return false;
        }
    } else {
        System.err.println("Error: El objeto no es de tipo HuespedesDTO");
        return false;
    }
}
    
    @Override
    public boolean update(Object object) {
        Huespedes huesped = (Huespedes) object;
        String sql = "UPDATE huespedes SET nombre=?, email=?, telefono=?, activo=? WHERE id_huesped=?";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, huesped.getNombre());
            pst.setString(2, huesped.getEmail());
            pst.setString(3, huesped.getTelefono());
            pst.setInt(4, huesped.getActivo());
            pst.setInt(5, huesped.getId_huesped());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar huesped: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM huespedes WHERE id_huesped=?";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar huesped: " + e.getMessage());
            return false;
        }
    }

    public Object getById(int id) {
        Huespedes huesped = null;
        String sql = "SELECT * FROM huespedes WHERE id_huesped = ?";
        try (Connection con = ConexionBD.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                huesped = new Huespedes(
                        rs.getInt("id_huesped"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getInt("activo")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener huesped por ID: " + e.getMessage());
        }
        return huesped;
    }
    public List<HuespedesDTO> obtenerHuespedes() {
    List<HuespedesDTO> listaHuespedes = new ArrayList<>();
    String query = "SELECT id_huesped, nombre FROM huespedes"; // Consulta para obtener ambos campos

    try (Connection con = ConexionBD.getConnection();
         PreparedStatement stmt = con.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            HuespedesDTO huesped = new HuespedesDTO();
            huesped.setId_huesped(rs.getInt("id_huesped"));
            huesped.setNombre(rs.getString("nombre"));
            listaHuespedes.add(huesped);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Maneja el error
    }
    return listaHuespedes;
}
}
