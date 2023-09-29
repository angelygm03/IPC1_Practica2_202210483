package com.mycompany.practica2;
import gui.MenuPrincipalJFrame;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class Practica2 {

    public static List<Producto> listaProductos = new ArrayList<>();
    
    public static void main(String[] args) {
        
        listaProductos.add(new Producto("Pizza", 55.00));
        listaProductos.add(new Producto("Pollo Frito", 130.00));
        listaProductos.add(new Producto("Papas Fritas", 15.00));
        listaProductos.add(new Producto("Gaseosa", 12.00));
        
        MenuPrincipalJFrame mainFrame = new MenuPrincipalJFrame();
        mainFrame.setVisible(true);
    }
    
}
    