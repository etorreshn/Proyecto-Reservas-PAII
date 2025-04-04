/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

public class HabitacionesDTO {
    private int id_habitacion;
    private String tipo;
    private double precio;
    private int capacidad;
    private Integer id_huesped; // Usamos Integer para permitir null
    private int activo;

    // Constructor vacío
    public HabitacionesDTO() {
    }

    // Constructor con parámetros
    public HabitacionesDTO(int id_habitacion, String tipo, double precio, int capacidad, Integer id_huesped, int activo) {
        this.id_habitacion = id_habitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.capacidad = capacidad;
        this.id_huesped = id_huesped;
        this.activo = activo;
    }

    // Getters y Setters
    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
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

    public int getId_huesped() {
        return (id_huesped != null) ? id_huesped : 0; // Si es null, devuelve 0
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}