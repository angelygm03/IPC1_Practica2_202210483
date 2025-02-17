package com.mycompany.practica2;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Producto implements Serializable {
    
    public String nombre;
    public double precio;

    
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public interface ProductoAgregadoListener {
        void productoAgregado(Producto producto);
    }
}
