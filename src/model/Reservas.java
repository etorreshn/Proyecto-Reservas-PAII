/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Objects;
import java.util.Date;

/**
 *
 * @author Edwin
 */
public class Reservas {
        private int id_reserva;
    private int id_huesped;
    private int id_habitacion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private int activo;
    
        public Reservas (int id_reserva, int id_huesped, int id_habitacion, Date fecha_inicio, Date fecha_fin, int activo) {
        this.id_huesped = id_huesped;
        this.id_huesped = id_huesped;
        this.id_habitacion = id_habitacion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activo = activo;
    } 

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
     
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_reserva;
        hash = 79 * hash + this.id_huesped;
        hash = 79 * hash + this.id_habitacion;
        hash = 79 * hash + Objects.hashCode(this.fecha_inicio);
        hash = 79 * hash + Objects.hashCode(this.fecha_fin);
        hash = 79 * hash + this.activo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservas other = (Reservas) obj;
        if (this.id_reserva != other.id_reserva) {
            return false;
        }
        if (this.id_huesped != other.id_huesped) {
            return false;
        }
        if (this.id_habitacion != other.id_habitacion) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.fecha_inicio, other.fecha_inicio)) {
            return false;
        }
        return Objects.equals(this.fecha_fin, other.fecha_fin);
    }
        
        
}
