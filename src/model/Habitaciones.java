/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Habitaciones {
    private int id_habitacion;
    private String tipo;
    private double precio;
    private int capacidad;
    private Integer id_huesped;
    private int activo;

    public Habitaciones(int id_habitacion, String tipo, double precio, int capacidad, Integer id_huesped, int activo) {
        this.id_habitacion = id_habitacion;
        this.tipo = tipo;
        this.precio = precio;
        this.capacidad = capacidad;
        this.id_huesped = id_huesped;
        this.activo = activo;
    }

    // Getters
    public int getId_habitacion() {
        return id_habitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Integer getId_huesped() {
        return id_huesped;
    }

    public int getActivo() {
        return activo;
    }
}