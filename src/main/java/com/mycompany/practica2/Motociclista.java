package com.mycompany.practica2;

/**
 *
 * @author Usuario
 */
public class Motociclista {
    private String nombreMoto;
    private boolean disponible;
    private Orden ordenAsignada;
    private String identificador;
    
    public Motociclista(String nombre, String identificador) {
        this.nombreMoto = nombre;
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombreMoto;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Orden getOrdenAsignada() {
        return ordenAsignada;
    }

    public void asignarOrden(Orden orden) {
        this.ordenAsignada = orden;
    }

    public void desasignarOrden() {
        this.ordenAsignada = null;
    }
    public boolean estaDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
