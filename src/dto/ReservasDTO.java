/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;
import java.util.Objects;

public class ReservasDTO {

    private int id_reserva;
    private int id_huesped;
    private int id_habitacion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private int activo;

    // Constructor vacío
    public ReservasDTO() { 
    }

    // Constructor con parámetros
    public ReservasDTO(int id_reserva, int id_huesped, int id_habitacion, Date fecha_inicio, Date fecha_fin, int activo) {
        this.id_reserva = id_reserva;  
        this.id_huesped = id_huesped;
        this.id_habitacion = id_habitacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activo = activo;
    }   

    // Getters
    public int getId_reserva() {
        return id_reserva;
    }

    public int getId_huesped() {
        return id_huesped;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public int getActivo() {
        return activo;
    }

    // Setters
    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "ReservasDTO{" +
                "id_reserva=" + id_reserva +
                ", id_huesped=" + id_huesped +
                ", id_habitacion=" + id_habitacion +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", activo=" + activo +
                '}';
    }

    // Método equals y hashCode para comparar objetos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservasDTO that = (ReservasDTO) o;
        return id_reserva == that.id_reserva &&
                id_huesped == that.id_huesped &&
                id_habitacion == that.id_habitacion &&
                activo == that.activo &&
                Objects.equals(fecha_inicio, that.fecha_inicio) &&
                Objects.equals(fecha_fin, that.fecha_fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_reserva, id_huesped, id_habitacion, fecha_inicio, fecha_fin, activo);
    }
}
