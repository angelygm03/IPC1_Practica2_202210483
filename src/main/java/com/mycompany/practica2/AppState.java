package com.mycompany.practica2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class AppState {
    public static List<Orden> historialPedidos; 
    public static List<Producto> productos;
    public static List<Motociclista> motociclistas;
    public static String rutaDatosSerializados = "./DatosSerializados/appState.bin";

    public static void serializar() {
        try {
            File file = new File(rutaDatosSerializados);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(rutaDatosSerializados);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            // Serializa los datos que deseas persistir
            oos.writeObject(historialPedidos);
            oos.writeObject(productos);
            oos.writeObject(motociclistas);

            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error al serializar: " + ex.getMessage());
        }
    }

    public static void deserializar() {
        try {
            File file = new File(rutaDatosSerializados);
            if (!file.exists()) {
                return;
            }

            FileInputStream fis = new FileInputStream(rutaDatosSerializados);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Deserializa los datos y asigna a las variables correspondientes
            historialPedidos = (List<Orden>) ois.readObject();
            productos = (List<Producto>) ois.readObject();
            motociclistas = (List<Motociclista>) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("Error al deserializar: " + ex.getMessage());
        }
    }
}
