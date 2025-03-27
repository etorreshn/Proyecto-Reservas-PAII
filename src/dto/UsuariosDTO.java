/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Edwin
 */
public class UsuariosDTO {
    private int id;
    private String usuario;
    private String nombre;
    private String email;
    private String contrasena;
    private int id_Rol;
    private int activo;
    private String salt;

    public UsuariosDTO() {}

    public UsuariosDTO(int id, String usuario, String nombre, String email, String contrasena, int id_Rol, int activo) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.id_Rol = id_Rol;
        this.activo = activo;
        this.salt = salt;
        
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

        public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
    }
    
    public int getActivo() {
       return activo;
    }

    public void setActivo(int activo) {
      this.activo = activo;
   }
    
       public String getSalt() {
        return contrasena;
    }

    public void setSalt(String contrasena) {
        this.contrasena = contrasena;
    }
}

