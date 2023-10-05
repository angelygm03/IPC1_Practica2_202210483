package com.mycompany.practica2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Orden implements Serializable {
    public List<Producto> productos;
    public Date fechaCreacion;
    private boolean finalizada;
    private Motociclista motociclistaAsignado;
    private int distancia;
    private double montoTotal;
    public String fechaFormateada;
    private Date fechaLlegadaDerecha;
    private String motociclistaSeleccionado;
    
    public Orden() {
        productos = new ArrayList<>();
        fechaCreacion = new Date();
        finalizada = false;
        motociclistaAsignado = null;
        distancia = 0;
        montoTotal = 0.0;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Motociclista getMotociclistaAsignado() {
        return motociclistaAsignado;
    }

    public void setMotociclistaAsignado(Motociclista motociclistaAsignado) {
        this.motociclistaAsignado = motociclistaAsignado;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void calcularMontoTotal() {
        montoTotal = 0.0;
        for (Producto producto : productos) {
            montoTotal += producto.getPrecio();
        }
        System.out.println("Se calcula el monto total");
    }

    public void setFechaFormateada(String fechaFormateada) {
        this.fechaFormateada = fechaFormateada;
    }
    
    public Date getFechaLlegadaDerecha() {
        return fechaLlegadaDerecha;
    }
    
    public void setFechaLlegadaDerecha(Date fechaLlegadaDerecha) {
        this.fechaLlegadaDerecha = fechaLlegadaDerecha;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaLlegadaDerecha = fechaEntrega;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
    public String getMotociclistaSeleccionado() {
        return motociclistaSeleccionado;
    }

    public void setMotociclistaSeleccionado(String motociclistaSeleccionado) {
        this.motociclistaSeleccionado = motociclistaSeleccionado;
    }
}
