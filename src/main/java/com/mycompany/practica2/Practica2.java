package com.mycompany.practica2;
import gui.RestauranteMainFrame;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class Practica2 {

    public static List<Producto> listaProductos = new ArrayList<>();
    
    public static void main(String[] args) {
        List<Producto> listaProductos = AppState.cargarProductos();
        
        // Si no se cargaron productos, crea una nueva lista
        if (listaProductos == null) {
            listaProductos = new ArrayList<>();
        }
        
        
        RestauranteMainFrame mainFrame = new RestauranteMainFrame(listaProductos);
        mainFrame.setVisible(true);
    } 
}   
    