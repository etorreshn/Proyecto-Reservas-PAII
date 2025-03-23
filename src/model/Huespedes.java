/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Objects;

/**
 *
 * @author Edwin
 */
public class Huespedes {

    private int id_huesped;
    private String nombre;
    private String email;
    private String telefono;
    private int activo;

    public Huespedes() {
    }

    public Huespedes(int id_huesped, String nombre, String email, String telefono, int activo) {
        this.id_huesped = id_huesped;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.activo = activo;
      } 
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_huesped;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.telefono);
        hash = 29 * hash + this.activo;
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
        final Huespedes other = (Huespedes) obj;
        if (this.id_huesped != other.id_huesped) {
            return false;
        }
        if (this.activo != other.activo) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.telefono, other.telefono);
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getId_huesped() {
        return id_huesped;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getActivo() {
        return activo;
    }
   
}

