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
    private int idRol;
    private int activo;

    public UsuariosDTO() {}

    public UsuariosDTO(int id, String usuario,String nombre, String email, String contrasena, int idRol, int activo) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.idRol = idRol;
        this.activo = activo;
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
        this.nombre = usuario;
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

    public void setContraseña(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    
            public int getActivo() {
       return activo;
    }

    public void setActivo(int activo) {
      this.id = activo;
   }
}

