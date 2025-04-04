/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Edwin
 */

public class HabitacionConHuespedDTO {
    private int idHabitacion;
    private String tipo;
    private double precio;
    private int capacidad;
    private Integer idHuesped; // Integer para permitir null
    private String nombreHuesped;
    private String emailHuesped;
    private String telefonoHuesped;
    private int activo; // 1 = activo, 0 = inactivo

    // Constructor completo
    public HabitacionConHuespedDTO(int idHabitacion, String tipo, double precio, 
                                 int capacidad, Integer idHuesped, String nombreHuesped,
                                 String emailHuesped, String telefonoHuesped, int activo) {
        this.idHabitacion = idHabitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.capacidad = capacidad;
        this.idHuesped = idHuesped;
        this.nombreHuesped = nombreHuesped;
        this.emailHuesped = emailHuesped;
        this.telefonoHuesped = telefonoHuesped;
        this.setActivo(activo); // Usamos el setter para validación
    }

    // Constructor vacío para frameworks
    public HabitacionConHuespedDTO() {
    }

    // Getters y Setters con validación
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(Integer idHuesped) {
        this.idHuesped = idHuesped;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public void setNombreHuesped(String nombreHuesped) {
        this.nombreHuesped = nombreHuesped;
    }

    public String getEmailHuesped() {
        return emailHuesped;
    }

    public void setEmailHuesped(String emailHuesped) {
        this.emailHuesped = emailHuesped;
    }

    public String getTelefonoHuesped() {
        return telefonoHuesped;
    }

    public void setTelefonoHuesped(String telefonoHuesped) {
        this.telefonoHuesped = telefonoHuesped;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        if (activo != 0 && activo != 1) {
            throw new IllegalArgumentException("El valor de activo debe ser 0 o 1");
        }
        this.activo = activo;
    }

    // Métodos de conveniencia para la interfaz gráfica
    public boolean isActivoAsBoolean() {
        return activo == 1;
    }

    public void setActivoFromBoolean(boolean activo) {
        this.activo = activo ? 1 : 0;
    }

    @Override
    public String toString() {
        return "HabitacionConHuespedDTO{" +
               "idHabitacion=" + idHabitacion +
               ", tipo='" + tipo + '\'' +
               ", precio=" + precio +
               ", capacidad=" + capacidad +
               ", idHuesped=" + idHuesped +
               ", nombreHuesped='" + nombreHuesped + '\'' +
               ", emailHuesped='" + emailHuesped + '\'' +
               ", telefonoHuesped='" + telefonoHuesped + '\'' +
               ", activo=" + activo +
               '}';
    }
}