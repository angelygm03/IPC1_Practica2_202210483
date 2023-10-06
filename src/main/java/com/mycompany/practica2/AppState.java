package com.mycompany.practica2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AppState {
    private static String ARCHIVO_PRODUCTOS = "./DatosSerializados/appState.bin";

    // Método para guardar la lista de productos en un archivo binario
    public static void guardarProductos(List<Producto> productos) {
        File file = new File(ARCHIVO_PRODUCTOS);
        if(!file.exists()) {
            file.getParentFile().mkdir();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRODUCTOS))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PRODUCTOS))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                productos = (List<Producto>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return productos;
    }
}