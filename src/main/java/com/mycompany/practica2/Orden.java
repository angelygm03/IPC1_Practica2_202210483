package com.mycompany.practica2;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Orden {
    private List<Producto> productos;
    private Date fechaCreacion;
    private Date fechaEntrega;
    private boolean finalizada;
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

}
