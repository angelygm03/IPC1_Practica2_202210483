package gui;

import com.mycompany.practica2.Motociclista;
import com.mycompany.practica2.Orden;
import com.mycompany.practica2.Producto;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class RestauranteMainFrame extends javax.swing.JFrame {
    
    private int contadorPizza = 0;
    private int contadorPollo = 0;
    private int contadorPapas = 0;
    private int contadorGaseosa = 0;
    private double totalPedido = 0.0;
    private DefaultTableModel modelo;
    public List<Producto> productos = new ArrayList<>();
    private NuevoProductoJFrame nuevoProductoFrame;
    private Set<String> motociclistasAsignados = new HashSet<>();
    public List<Orden> ordenes = new ArrayList<>();
    private Motociclista[] motociclistas = new Motociclista[3]; // Un arreglo de tamaño 3 para los tres motociclistas

    private Motociclista motociclistaAsignado; 
    private Motociclista motociclista1;
    private Motociclista motociclista2;
    private Motociclista motociclista3;
    private String distanciaTemporal;
    private int velocidadConstante = 2;
    private List<Orden> historialPedidos = new ArrayList<>();
    private Map<Orden, String> motocicletasAsignadas = new HashMap<>();
    private Orden ordenActual;
    private boolean enviarMotociclista1Habilitado = true;
    private boolean enviarMotociclista2Habilitado = true;
    private boolean enviarMotociclista3Habilitado = true;

 

    public List<Producto> getProducto() {
        return productos;
    }
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
    
    public List<Orden> getOrdenes() {
        return ordenes;
    }
   
      public RestauranteMainFrame() {
        initComponents();
        
        int rojo = 249; // Valor de rojo (0-255)
        int verde = 243; // Valor de verde (0-255)
        int azul = 204;
        Color colorPersonalizado = new Color(rojo, verde, azul);
        this.getContentPane().setBackground(colorPersonalizado);
        
        
        this.setVisible(true); //Hace visible la ventana
        this.setLocationRelativeTo(null); //La coloca al centro
        this.setResizable(false); //Bloquea el redimensionamiento de la ventana
        this.setTitle("Nuevo pedido"); //Título a la ventana
        
        this.ActualizarTablaProductos(productos);
        
        
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        productosAgregadosJTable.setModel(modelo);
        
        productosJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite seleccionar una sola fila a la vez

        motociclistas[0] = new Motociclista("Motociclista 1", "M1");
        motociclistas[1] = new Motociclista("Motociclista 2", "M2");
        motociclistas[2] = new Motociclista("Motociclista 3", "M3");
    
        vehiculoComboBox.addItem("Motociclista 1");
        vehiculoComboBox.addItem("Motociclista 2");
        vehiculoComboBox.addItem("Motociclista 3");       
        
        vehiculoComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtén el motociclista seleccionado del JComboBox
            String motociclistaSeleccionado = (String) vehiculoComboBox.getSelectedItem();

            // Agrega el motociclista seleccionado a la tabla de historial
            DefaultTableModel modeloHistorial = (DefaultTableModel) historialJTable.getModel();
            modeloHistorial.addRow(new Object[] { motociclistaSeleccionado });
        }
    });

        }

    
    public String getDistanciaTexto() {
        return distanciaTF.getText();
    }

    public void ActualizarTablaProductos(List<Producto> productos){
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Nombre");
            modelo.addColumn("Precio");
            
            
            for (Producto producto: productos){
            Object[] fila = {producto.nombre, producto.precio};
                modelo.addRow(fila);
                
            }

            productosJTable.setModel(modelo);
    }
    
    public void abrirNuevoProductoFrame() {
        nuevoProductoFrame = new NuevoProductoJFrame(this);
        nuevoProductoFrame.setVisible(true);
    }
    
    private void reiniciarContadores() {
        contadorPizza = 0;
        contadorPollo = 0;
        contadorPapas = 0;
        contadorGaseosa = 0;

        //actualizar los botones si 
        agregarPizzaButton.setText("Agregar");
        agregarPolloButton.setText("Agregar");
        agregarPapasButton.setText("Agregar");
        agregarGaseosaButton.setText("Agregar");
    }

    
    private Motociclista encontrarMotociclistaDisponible(String nombre) {
    for (Motociclista motociclista : motociclistas) {
        if (motociclista != null && motociclista.estaDisponible() && motociclista.getNombre().equals(nombre)) {
            return motociclista;
        }
    }
    return null;
}

    private void actualizarTablaHistorial() {
    DefaultTableModel modeloHistorial = (DefaultTableModel) historialJTable.getModel();
    modeloHistorial.setRowCount(0); // Borra todas las filas existentes

    int columnaFecha = 4;

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("es", "ES"));

    for (Orden orden : historialPedidos) {
        String fechaFormateada = formatoFecha.format(orden.getFechaCreacion());
        String motociclistaSeleccionado = orden.getMotociclistaSeleccionado(); // Obtener el motociclista seleccionado del JComboBox
        System.out.println("Monto total: " + orden.getMontoTotal());
        String distanciaConUnidad = orden.getDistancia() + " km";
        String montoConUnidad = "Q. " + orden.getMontoTotal();
        Object[] fila = {
            motociclistaSeleccionado,
            distanciaConUnidad,
            montoConUnidad,
            formatoFecha.format(orden.getFechaCreacion()),
            formatoFecha.format(orden.getFechaLlegadaDerecha())
        };
        modeloHistorial.addRow(fila);
    }
}

private Orden obtenerOrdenActual() {
    return ordenActual;
}

private Motociclista encontrarMotociclistaPorNombre(String nombre) {
    for (Motociclista motociclista : motociclistas) {
        if (motociclista.getNombre().equals(nombre) && motociclista.estaDisponible()) {
            return motociclista;
        }
    }
    return null; // Retorna null si no se encuentra el motociclista
}

public void setDistanciaMotociclista(String motociclista, String distancia) {
    if (motociclista.equals("Motociclista 1")) {
        distanciaM1.setText("Distancia: " + distancia + " km");
    } else if (motociclista.equals("Motociclista 2")) {
        distanciaM2.setText("Distancia: " + distancia + " km");
    } else if (motociclista.equals("Motociclista 3")) {
        distanciaM3.setText("Distancia: " + distancia + " km");
    }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        nuevoProductoButton = new javax.swing.JButton();
        nuevaOrdenButton = new javax.swing.JButton();
        pizzaPanel1 = new gui.PizzaPanel();
        polloPanel1 = new gui.PolloPanel();
        papasPanel1 = new gui.PapasPanel();
        gaseosaPanel1 = new gui.GaseosaPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productosJTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        productosAgregadosJTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        agregarPizzaButton = new javax.swing.JButton();
        agregarPolloButton = new javax.swing.JButton();
        agregarPapasButton = new javax.swing.JButton();
        agregarGaseosaButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        distanciaTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        vehiculoComboBox = new javax.swing.JComboBox<>();
        agregarProductoButton = new javax.swing.JButton();
        confirmarPedidoButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        totalOrdenLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        distanciaM1 = new javax.swing.JLabel();
        distanciaM2 = new javax.swing.JLabel();
        distanciaM3 = new javax.swing.JLabel();
        enviarMotociclista1Button = new javax.swing.JButton();
        enviarMotociclista2Button = new javax.swing.JButton();
        enviarMotociclista3Button = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        movimientoImagen1 = new gui.MovimientoImagen();
        movimientoImagen2 = new gui.MovimientoImagen();
        movimientoImagen3 = new gui.MovimientoImagen();
        enviarTodosButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        historialJTable = new javax.swing.JTable();
        totalPedidoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(249, 243, 204));
        jTabbedPane1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(249, 243, 204));
        jPanel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel9.setText("Restaurante Comida Rápida ");

        nuevoProductoButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        nuevoProductoButton.setText("Nuevo Producto");
        nuevoProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoProductoButtonActionPerformed(evt);
            }
        });

        nuevaOrdenButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        nuevaOrdenButton.setText("Nueva Orden");
        nuevaOrdenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaOrdenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pizzaPanel1Layout = new javax.swing.GroupLayout(pizzaPanel1);
        pizzaPanel1.setLayout(pizzaPanel1Layout);
        pizzaPanel1Layout.setHorizontalGroup(
            pizzaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );
        pizzaPanel1Layout.setVerticalGroup(
            pizzaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout polloPanel1Layout = new javax.swing.GroupLayout(polloPanel1);
        polloPanel1.setLayout(polloPanel1Layout);
        polloPanel1Layout.setHorizontalGroup(
            polloPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        polloPanel1Layout.setVerticalGroup(
            polloPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout papasPanel1Layout = new javax.swing.GroupLayout(papasPanel1);
        papasPanel1.setLayout(papasPanel1Layout);
        papasPanel1Layout.setHorizontalGroup(
            papasPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );
        papasPanel1Layout.setVerticalGroup(
            papasPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gaseosaPanel1Layout = new javax.swing.GroupLayout(gaseosaPanel1);
        gaseosaPanel1.setLayout(gaseosaPanel1Layout);
        gaseosaPanel1Layout.setHorizontalGroup(
            gaseosaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );
        gaseosaPanel1Layout.setVerticalGroup(
            gaseosaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setText("Populares");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setText("Pizza Q55");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setText("Pollo Frito Q130");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel4.setText("Papas Fritas Q15");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel5.setText("Gaseosa Q12");

        productosJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productosJTable);

        productosAgregadosJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Producto", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(productosAgregadosJTable);

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel6.setText("Otros productos");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel7.setText("Orden");

        agregarPizzaButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        agregarPizzaButton.setText("Agregar");
        agregarPizzaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPizzaButtonActionPerformed(evt);
            }
        });

        agregarPolloButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        agregarPolloButton.setText("Agregar");
        agregarPolloButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPolloButtonActionPerformed(evt);
            }
        });

        agregarPapasButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        agregarPapasButton.setText("Agregar");
        agregarPapasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPapasButtonActionPerformed(evt);
            }
        });

        agregarGaseosaButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        agregarGaseosaButton.setText("Agregar");
        agregarGaseosaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarGaseosaButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel8.setText("Distancia");

        distanciaTF.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel10.setText("km");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel11.setText("Vehículo");

        vehiculoComboBox.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        vehiculoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehiculoComboBoxActionPerformed(evt);
            }
        });

        agregarProductoButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        agregarProductoButton.setText("Agregar producto seleccionado a la orden");
        agregarProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoButtonActionPerformed(evt);
            }
        });

        confirmarPedidoButton.setFont(new java.awt.Font("Trebuchet MS", 1, 15)); // NOI18N
        confirmarPedidoButton.setText("Confirmar Pedido");
        confirmarPedidoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarPedidoButtonActionPerformed(evt);
            }
        });

        totalOrdenLabel.setFont(new java.awt.Font("Trebuchet MS", 1, 15)); // NOI18N
        totalOrdenLabel.setText("Total del Pedido:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jLabel2)
                                        .addGap(137, 137, 137))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(agregarPizzaButton)
                                            .addComponent(pizzaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(120, 120, 120)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(polloPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(agregarPolloButton)))
                                        .addGap(118, 118, 118)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(agregarPapasButton))
                                            .addComponent(papasPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)))
                                    .addComponent(jLabel9))
                                .addGap(89, 89, 89))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(nuevaOrdenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(571, 571, 571)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(gaseosaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(agregarGaseosaButton)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(nuevoProductoButton))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(372, 372, 372))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(distanciaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(vehiculoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(confirmarPedidoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(agregarProductoButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(totalOrdenLabel)
                                        .addGap(34, 34, 34)))))))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nuevoProductoButton)
                            .addComponent(nuevaOrdenButton))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel1)
                                .addGap(5, 5, 5)
                                .addComponent(pizzaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(gaseosaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(polloPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(papasPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarPizzaButton)
                    .addComponent(agregarPolloButton)
                    .addComponent(agregarPapasButton)
                    .addComponent(agregarGaseosaButton))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarProductoButton)
                    .addComponent(jLabel12)
                    .addComponent(totalOrdenLabel))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(distanciaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(vehiculoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmarPedidoButton))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Menú", jPanel1);

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel14.setText("Motociclista 1");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel15.setText("Motociclista 2");

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        jLabel16.setText("Motociclista 3");

        distanciaM1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        distanciaM1.setText("Distancia: ");

        distanciaM2.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        distanciaM2.setText("Distancia: ");

        distanciaM3.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        distanciaM3.setText("Distancia: ");

        enviarMotociclista1Button.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        enviarMotociclista1Button.setText("Enviar");
        enviarMotociclista1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMotociclista1ButtonActionPerformed(evt);
            }
        });

        enviarMotociclista2Button.setFont(new java.awt.Font("Trebuchet MS", 1, 16)); // NOI18N
        enviarMotociclista2Button.setText("Enviar");
        enviarMotociclista2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMotociclista2ButtonActionPerformed(evt);
            }
        });

        enviarMotociclista3Button.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        enviarMotociclista3Button.setText("Enviar");
        enviarMotociclista3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarMotociclista3ButtonActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel20.setText("Visualización de Trayectorias");

        javax.swing.GroupLayout movimientoImagen1Layout = new javax.swing.GroupLayout(movimientoImagen1);
        movimientoImagen1.setLayout(movimientoImagen1Layout);
        movimientoImagen1Layout.setHorizontalGroup(
            movimientoImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );
        movimientoImagen1Layout.setVerticalGroup(
            movimientoImagen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout movimientoImagen2Layout = new javax.swing.GroupLayout(movimientoImagen2);
        movimientoImagen2.setLayout(movimientoImagen2Layout);
        movimientoImagen2Layout.setHorizontalGroup(
            movimientoImagen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        movimientoImagen2Layout.setVerticalGroup(
            movimientoImagen2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout movimientoImagen3Layout = new javax.swing.GroupLayout(movimientoImagen3);
        movimientoImagen3.setLayout(movimientoImagen3Layout);
        movimientoImagen3Layout.setHorizontalGroup(
            movimientoImagen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        movimientoImagen3Layout.setVerticalGroup(
            movimientoImagen3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        enviarTodosButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        enviarTodosButton.setText("Enviar Todos");
        enviarTodosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarTodosButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enviarMotociclista1Button)
                    .addComponent(distanciaM1)
                    .addComponent(jLabel14)
                    .addComponent(enviarMotociclista2Button)
                    .addComponent(distanciaM2)
                    .addComponent(jLabel15)
                    .addComponent(distanciaM3)
                    .addComponent(enviarMotociclista3Button)
                    .addComponent(jLabel16))
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(movimientoImagen1, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                    .addComponent(movimientoImagen2, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                    .addComponent(movimientoImagen3, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addComponent(enviarTodosButton)
                .addGap(38, 38, 38))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(enviarTodosButton))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(distanciaM1)
                        .addGap(18, 18, 18)
                        .addComponent(enviarMotociclista1Button))
                    .addComponent(movimientoImagen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(distanciaM2)
                        .addGap(18, 18, 18)
                        .addComponent(enviarMotociclista2Button))
                    .addComponent(movimientoImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(distanciaM3)
                        .addGap(18, 18, 18)
                        .addComponent(enviarMotociclista3Button))
                    .addComponent(movimientoImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Delivery", jPanel3);

        jPanel2.setBackground(new java.awt.Color(249, 243, 204));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel13.setText("Historial de Pedidos");

        historialJTable.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        historialJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Vehiculo", "Distancia", "Monto", "Fecha y Hora de Creación", "Fecha y Hora de Entrega"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(historialJTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addComponent(jLabel13)
                .addContainerGap(368, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel13)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Historial", jPanel2);

        totalPedidoLabel.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalPedidoLabel)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalPedidoLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevaOrdenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaOrdenButtonActionPerformed
        reiniciarContadores();
        // Limpia la tabla productosAgregadosJTable si es necesario
        DefaultTableModel modeloProductosAgregados = (DefaultTableModel) productosAgregadosJTable.getModel();
        modeloProductosAgregados.setRowCount(0); // Borra todas las filas
        totalPedido = 0.0; // Reinicia el total del pedido si es necesario
        System.out.println("Reinica el total para una nueva orden");
        totalOrdenLabel.setText("Total del pedido: Q " + totalPedido);
        // Limpia el text field de distancia
        distanciaTF.setText("");

        // Limpia el combo box
        vehiculoComboBox.setSelectedIndex(0);
    }//GEN-LAST:event_nuevaOrdenButtonActionPerformed

    private void agregarPizzaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPizzaButtonActionPerformed
        contadorPizza++;
        agregarPizzaButton.setText("Agregar (" + contadorPizza + ")");
        double precioPizza = 55;
        Object[] fila = {"Pizza", precioPizza};
        modelo.addRow(fila);
        totalPedido += precioPizza;
        System.out.println("Se suma el precio de la pizza");
        totalOrdenLabel.setText("Total del pedido: Q " + totalPedido);
        
        Producto pizza = new Producto("Pizza", precioPizza);
        productos.add(pizza);
    
    }//GEN-LAST:event_agregarPizzaButtonActionPerformed

    private void agregarPolloButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPolloButtonActionPerformed
        contadorPollo++;
        agregarPolloButton.setText("Agregar (" + contadorPollo + ")");
        double precioPollo = 130;
        Object[] fila = {"Pollo Frito", precioPollo};
        modelo.addRow(fila);
        totalPedido += precioPollo;
        System.out.println("Se suma el precio del pollo");
        totalOrdenLabel.setText("Total del pedido: Q " + totalPedido);
        
        Producto polloFrito = new Producto("Pollo Frito", precioPollo);
        productos.add(polloFrito);
    }//GEN-LAST:event_agregarPolloButtonActionPerformed

    private void agregarPapasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPapasButtonActionPerformed
        contadorPapas++;
        agregarPapasButton.setText("Agregar (" + contadorPapas + ")");
        double precioPapas = 15;
        Object[] fila = {"Papas Fritas", precioPapas};
        modelo.addRow(fila);
        totalPedido += precioPapas;
        System.out.println("Se suma el precio de las papas");
        totalOrdenLabel.setText("Total del pedido: Q " + totalPedido);
        
        Producto papas = new Producto("Papas Fritas", precioPapas);
        productos.add(papas);
    }//GEN-LAST:event_agregarPapasButtonActionPerformed

    private void agregarGaseosaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarGaseosaButtonActionPerformed
        contadorGaseosa++;
        agregarGaseosaButton.setText("Agregar (" + contadorGaseosa + ")");
        double precioGaseosa = 12;
        Object[] fila = {"Gaseosa", precioGaseosa};
        modelo.addRow(fila);
        totalPedido += precioGaseosa;
        System.out.println("Se suma el precio de la gaseosa");
        totalOrdenLabel.setText("Total del pedido: Q " + totalPedido);
        
        Producto gaseosa = new Producto("Gaseosa", precioGaseosa);
        productos.add(gaseosa);
    }//GEN-LAST:event_agregarGaseosaButtonActionPerformed

    private void confirmarPedidoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarPedidoButtonActionPerformed
        String distanciaTexto = distanciaTF.getText();
    String motociclistaSeleccionado = (String) vehiculoComboBox.getSelectedItem();

    if (productos.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No hay productos en la orden.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        int distancia = Integer.parseInt(distanciaTexto);
        if (distancia > 10) {
            JOptionPane.showMessageDialog(this, "La distancia no puede ser mayor a 10 km", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea confirmar la orden?", "Confirmar Orden", JOptionPane.YES_NO_OPTION);
        System.out.println("Se hace la confirmación");
        if (confirmacion == JOptionPane.YES_OPTION) {
            // La orden se confirmó
            Orden nuevaOrden = new Orden();
            nuevaOrden.setDistancia(distancia);
            nuevaOrden.setMotociclistaSeleccionado(motociclistaSeleccionado);

            Motociclista motociclistaDisponible = encontrarMotociclistaDisponible(motociclistaSeleccionado);

            if (motociclistaDisponible != null) {
                nuevaOrden.setMotociclistaAsignado(motociclistaDisponible);
                motociclistaDisponible.setDisponible(false);
                setDistanciaMotociclista(motociclistaSeleccionado, String.valueOf(distancia));

                // Agrega los productos a la orden
                for (Producto producto : productos) {
                    nuevaOrden.agregarProducto(producto);
                }

                nuevaOrden.calcularMontoTotal(); // Calcula el monto total después de agregar los productos
                System.out.println("Se llama a calcular el monto");
                Date fechaCreacion = new Date();
                nuevaOrden.setFechaCreacion(fechaCreacion);

                   // Agrega 1 hora a la fecha de creación para la fecha de entrega
                   Calendar calendar = Calendar.getInstance();
                   calendar.setTime(fechaCreacion);
                   calendar.add(Calendar.MINUTE, 27); // Agregar 1 hora a la fecha de creación
                   Date fechaEntrega = calendar.getTime();
                   nuevaOrden.setFechaEntrega(fechaEntrega);

                   historialPedidos.add(nuevaOrden);
                   actualizarTablaHistorial();
                   System.out.println("Motociclista seleccionado: " + motociclistaSeleccionado);
                   
               } else {
                   JOptionPane.showMessageDialog(this, "El motociclista seleccionado no está disponible o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }

       } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingresa una distancia válida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_confirmarPedidoButtonActionPerformed

    private void agregarProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoButtonActionPerformed
        int filaSeleccionada = productosJTable.getSelectedRow();
    
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla para agregarlo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombreProducto = (String) productosJTable.getValueAt(filaSeleccionada, 0); // Obtiene el nombre del producto
        double precioProducto = (double) productosJTable.getValueAt(filaSeleccionada, 1); // Obtiene el precio del producto

        DefaultTableModel modeloProductosAgregados = (DefaultTableModel) productosAgregadosJTable.getModel();
        Object[] fila = {nombreProducto, precioProducto};
        modeloProductosAgregados.addRow(fila);
        
        totalPedido += precioProducto;
        System.out.println("Se suma el precio del producto de la tabla");
        totalOrdenLabel.setText("Total del pedido: Q " + totalPedido);
    }//GEN-LAST:event_agregarProductoButtonActionPerformed

    private void vehiculoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehiculoComboBoxActionPerformed
        String motociclistaSeleccionado = (String) vehiculoComboBox.getSelectedItem();

        String distanciaTexto = distanciaTF.getText(); // Obtener la distancia ingresada

        // Verifica si el motociclista ya ha sido asignado a otra orden
        if (motociclistasAsignados.contains(motociclistaSeleccionado)) {
            JOptionPane.showMessageDialog(this, "Este motociclista ya ha sido asignado a otra orden.", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Lista de motociclistas: " + motociclistas);

            vehiculoComboBox.setSelectedIndex(0); // Reinicia la selección
        } else {
            System.out.println("Se selecciona moto");
            
        }
    }//GEN-LAST:event_vehiculoComboBoxActionPerformed

    private void nuevoProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoProductoButtonActionPerformed
        NuevoProductoJFrame nuevoProductoFrame = new NuevoProductoJFrame(this);
        nuevoProductoFrame.setVisible(true);
    }//GEN-LAST:event_nuevoProductoButtonActionPerformed

    private void enviarMotociclista1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarMotociclista1ButtonActionPerformed
        if (enviarMotociclista1Habilitado) {
            movimientoImagen1.iniciarMovimiento();
            enviarMotociclista1Habilitado = false;
            enviarMotociclista2Habilitado = true;
            enviarMotociclista3Habilitado = true;
        }
    }//GEN-LAST:event_enviarMotociclista1ButtonActionPerformed

    private void enviarTodosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarTodosButtonActionPerformed
        movimientoImagen1.iniciarMovimiento();
        movimientoImagen2.iniciarMovimiento();
        movimientoImagen3.iniciarMovimiento();

        // Los botones individuales
        enviarMotociclista1Habilitado = true;
        enviarMotociclista2Habilitado = true;
        enviarMotociclista3Habilitado = true;
    }//GEN-LAST:event_enviarTodosButtonActionPerformed

    private void enviarMotociclista2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarMotociclista2ButtonActionPerformed
        if (enviarMotociclista2Habilitado) {
            movimientoImagen2.iniciarMovimiento();
            enviarMotociclista1Habilitado = true;
            enviarMotociclista2Habilitado = false;
            enviarMotociclista3Habilitado = true;
        }
    }//GEN-LAST:event_enviarMotociclista2ButtonActionPerformed

    private void enviarMotociclista3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarMotociclista3ButtonActionPerformed
        if (enviarMotociclista3Habilitado) {
            movimientoImagen3.iniciarMovimiento();
            enviarMotociclista1Habilitado = true;
            enviarMotociclista2Habilitado = true;
            enviarMotociclista3Habilitado = false;
        }
    }//GEN-LAST:event_enviarMotociclista3ButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RestauranteMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RestauranteMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RestauranteMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RestauranteMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestauranteMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarGaseosaButton;
    private javax.swing.JButton agregarPapasButton;
    private javax.swing.JButton agregarPizzaButton;
    private javax.swing.JButton agregarPolloButton;
    private javax.swing.JButton agregarProductoButton;
    private javax.swing.JButton confirmarPedidoButton;
    private javax.swing.JLabel distanciaM1;
    private javax.swing.JLabel distanciaM2;
    private javax.swing.JLabel distanciaM3;
    private javax.swing.JTextField distanciaTF;
    private javax.swing.JButton enviarMotociclista1Button;
    private javax.swing.JButton enviarMotociclista2Button;
    private javax.swing.JButton enviarMotociclista3Button;
    private javax.swing.JButton enviarTodosButton;
    private gui.GaseosaPanel gaseosaPanel1;
    private javax.swing.JTable historialJTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private gui.MovimientoImagen movimientoImagen1;
    private gui.MovimientoImagen movimientoImagen2;
    private gui.MovimientoImagen movimientoImagen3;
    private javax.swing.JButton nuevaOrdenButton;
    private javax.swing.JButton nuevoProductoButton;
    private gui.PapasPanel papasPanel1;
    private gui.PizzaPanel pizzaPanel1;
    private gui.PolloPanel polloPanel1;
    private javax.swing.JTable productosAgregadosJTable;
    private javax.swing.JTable productosJTable;
    private javax.swing.JLabel totalOrdenLabel;
    private javax.swing.JLabel totalPedidoLabel;
    private javax.swing.JComboBox<String> vehiculoComboBox;
    // End of variables declaration//GEN-END:variables
}
