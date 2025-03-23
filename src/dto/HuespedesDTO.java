/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

public class HuespedesDTO {

    private int id_huesped;  // Identificador del huésped
    private String nombre;   // Nombre del huésped
    private String email;    // Correo electrónico del huésped
    private String telefono; // Teléfono del huésped
    private int activo;      // Estado activo del huésped (1 = activo, 0 = inactivo)

    // Constructor por defecto
    public HuespedesDTO() {}

    // Constructor con todos los parámetros
    public HuespedesDTO(int id_huesped, String nombre, String email, String telefono, int activo) {
        this.id_huesped = id_huesped;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.activo = activo;
    }

    // Getters y setters para los atributos

    public int getId_huesped() {
        return id_huesped;
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
   public String toString() {
       return "HuespedesDTO{" +
               "id_huesped=" + id_huesped +
              ", nombre='" + nombre + '\'' +
               ", email='" + email + '\'' +
               ", telefono='" + telefono + '\'' +
               ", activo=" + activo +
               '}';
    }
    

    
}
