package gui;

import com.mycompany.practica2.Motociclista;
import com.mycompany.practica2.Orden;
import com.mycompany.practica2.Producto;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    private EnvioPedidosJFrame envioPedidosFrame;
    public List<Producto> productos = new ArrayList<>();
    private NuevoProductoJFrame nuevoProductoFrame;
    private Set<String> motociclistasAsignados = new HashSet<>();
    private List<Orden> ordenes = new ArrayList<>();
    private List<Motociclista> motociclistas = new ArrayList<>();
    private Motociclista motociclistaAsignado; 
    private Motociclista motociclista1;
    private Motociclista motociclista2;
    private Motociclista motociclista3;
    private String distanciaTemporal;
    private int velocidadConstante = 2;
    

    public List<Producto> getProducto() {
        return productos;
    }
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

   
    private static RestauranteMainFrame instance = null;
    public static RestauranteMainFrame getInstance() {
        if (instance == null) {
            instance = new RestauranteMainFrame();
        }
        return instance;
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

                
        motociclista1 = new Motociclista("Motociclista 1", "1");
        motociclista2 = new Motociclista("Motociclista 2", "2");
        motociclista3 = new Motociclista("Motociclista 3", "3");

        envioPedidosFrame = new EnvioPedidosJFrame();
        envioPedidosFrame.setRestauranteMainFrame(this); // Esto es importante para establecer la referencia bidireccional
        envioPedidosFrame.setVisible(false); // Configura la visibilidad como falso al principio

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

    public void crearNuevaOrden() {
        Orden nuevaOrden = new Orden();
        nuevaOrden.setProductos(productos);
        nuevaOrden.setFechaCreacion(new Date());

        // Asigna un motociclista a la orden actual
        asignarMotociclista(nuevaOrden);

        ordenes.add(nuevaOrden);
    
    }
    private void asignarMotociclista(Orden orden) {
        String motociclistaSeleccionado = (String) vehiculoComboBox.getSelectedItem();

        // Encuentra un motociclista disponible que coincida con el seleccionado
        Motociclista motociclistaDisponible = motociclistas.stream()
                .filter(motociclista -> motociclista.estaDisponible() && motociclista.getIdentificador().equals(motociclistaSeleccionado))
                .findFirst()
                .orElse(null);

        if (motociclistaDisponible != null) {
            // Asigna el motociclista a la orden y marca como no disponible
            orden.setMotociclistaAsignado(motociclistaDisponible);
            motociclistaDisponible.setDisponible(false);
        } else {
            JOptionPane.showMessageDialog(this, "El motociclista seleccionado no está disponible o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void entregarOrden(Orden orden) {
    orden.setFechaEntrega(new Date()); // Establece la fecha y hora de entrega
    // Puedes realizar otras acciones relacionadas con la entrega de la orden
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pizzaPanel1 = new gui.PizzaPanel();
        polloPanel1 = new gui.PolloPanel();
        papasPanel1 = new gui.PapasPanel();
        gaseosaPanel1 = new gui.GaseosaPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        agregarPizzaButton = new javax.swing.JButton();
        agregarPolloButton = new javax.swing.JButton();
        agregarPapasButton = new javax.swing.JButton();
        agregarGaseosaButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        confirmarPedidoButton = new javax.swing.JButton();
        agregarProductoButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        productosAgregadosJTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        vehiculoComboBox = new javax.swing.JComboBox<>();
        distanciaTF = new javax.swing.JTextField();
        totalPedidoLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productosJTable = new javax.swing.JTable();
        nuevoProductoButton = new javax.swing.JButton();
        historialPedidosButton = new javax.swing.JButton();
        nuevaOrdenButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("Restaurante de Comida Rápida");

        javax.swing.GroupLayout pizzaPanel1Layout = new javax.swing.GroupLayout(pizzaPanel1);
        pizzaPanel1.setLayout(pizzaPanel1Layout);
        pizzaPanel1Layout.setHorizontalGroup(
            pizzaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pizzaPanel1Layout.setVerticalGroup(
            pizzaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout polloPanel1Layout = new javax.swing.GroupLayout(polloPanel1);
        polloPanel1.setLayout(polloPanel1Layout);
        polloPanel1Layout.setHorizontalGroup(
            polloPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        polloPanel1Layout.setVerticalGroup(
            polloPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout papasPanel1Layout = new javax.swing.GroupLayout(papasPanel1);
        papasPanel1.setLayout(papasPanel1Layout);
        papasPanel1Layout.setHorizontalGroup(
            papasPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
        );
        papasPanel1Layout.setVerticalGroup(
            papasPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gaseosaPanel1Layout = new javax.swing.GroupLayout(gaseosaPanel1);
        gaseosaPanel1.setLayout(gaseosaPanel1Layout);
        gaseosaPanel1Layout.setHorizontalGroup(
            gaseosaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        gaseosaPanel1Layout.setVerticalGroup(
            gaseosaPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel2.setText("Populares");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel3.setText("Otros Productos");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel4.setText("Pizza Q55.00");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel5.setText("Pollo Frito Q130.00");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel6.setText("Papas Fritas Q15.00");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel7.setText("Gaseosa Q12.00");

        agregarPizzaButton.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        agregarPizzaButton.setText("Agregar");
        agregarPizzaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPizzaButtonActionPerformed(evt);
            }
        });

        agregarPolloButton.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
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

        agregarGaseosaButton.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        agregarGaseosaButton.setText("Agregar");
        agregarGaseosaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarGaseosaButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel8.setText("Productos agregados");

        confirmarPedidoButton.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        confirmarPedidoButton.setText("Confirmar Pedido");
        confirmarPedidoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarPedidoButtonActionPerformed(evt);
            }
        });

        agregarProductoButton.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        agregarProductoButton.setText("Agregar el producto seleccionado al pedido");
        agregarProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoButtonActionPerformed(evt);
            }
        });

        productosAgregadosJTable.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
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
        jScrollPane2.setViewportView(productosAgregadosJTable);

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel10.setText("Vehículo");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel11.setText("Distancia a recorrer");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel12.setText("km");

        vehiculoComboBox.setFont(new java.awt.Font("Tw Cen MT", 0, 16)); // NOI18N
        vehiculoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Motociclista 1", "Motociclista 2", "Motociclista 3" }));
        vehiculoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehiculoComboBoxActionPerformed(evt);
            }
        });

        distanciaTF.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        distanciaTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distanciaTFActionPerformed(evt);
            }
        });

        totalPedidoLabel.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productosJTable);

        nuevoProductoButton.setText("Nuevo Producto");
        nuevoProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoProductoButtonActionPerformed(evt);
            }
        });

        historialPedidosButton.setText("Historial de Pedidos");
        historialPedidosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialPedidosButtonActionPerformed(evt);
            }
        });

        nuevaOrdenButton.setText("Nueva Orden");
        nuevaOrdenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaOrdenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(pizzaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(43, 43, 43))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(agregarPizzaButton)
                                        .addComponent(jLabel4)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(polloPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(papasPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(agregarPolloButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(agregarPapasButton)
                                .addGap(29, 29, 29))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(gaseosaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(agregarGaseosaButton)
                        .addGap(77, 77, 77))))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(agregarProductoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevaOrdenButton))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(nuevoProductoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(confirmarPedidoButton)
                                            .addComponent(historialPedidosButton, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalPedidoLabel)
                                .addGap(54, 54, 54))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(vehiculoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(distanciaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nuevaOrdenButton)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(pizzaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nuevoProductoButton)
                        .addGap(18, 18, 18)
                        .addComponent(historialPedidosButton)
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(polloPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(papasPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gaseosaPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(agregarPizzaButton)
                    .addComponent(agregarPolloButton)
                    .addComponent(agregarPapasButton)
                    .addComponent(agregarGaseosaButton))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(totalPedidoLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(agregarProductoButton)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(vehiculoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(distanciaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(confirmarPedidoButton))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarPizzaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPizzaButtonActionPerformed
        contadorPizza++;
        agregarPizzaButton.setText("Agregar (" + contadorPizza + ")");
        double precioPizza = 55;
        Object[] fila = {"Pizza", precioPizza};
        modelo.addRow(fila);
        totalPedido += precioPizza;
        totalPedidoLabel.setText("Total del pedido: Q " + totalPedido);
        
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
        totalPedidoLabel.setText("Total del pedido: Q " + totalPedido);
        
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
        totalPedidoLabel.setText("Total del pedido: Q " + totalPedido);
        
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
        totalPedidoLabel.setText("Total del pedido: Q " + totalPedido);
        
        Producto gaseosa = new Producto("Gaseosa", precioGaseosa);
        productos.add(gaseosa);
    }//GEN-LAST:event_agregarGaseosaButtonActionPerformed

    private void confirmarPedidoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarPedidoButtonActionPerformed
        String distanciaTexto = distanciaTF.getText();
        String motociclistaSeleccionado = (String) vehiculoComboBox.getSelectedItem();
        System.out.println("se lee la moto seleccionada");
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

            // Calcular la velocidad proporcional a la distancia
            int velocidad = distancia * velocidadConstante;

            // Configurar la velocidad en el objeto MovimientoImagen
            envioPedidosFrame.setVelocidadMovimiento(velocidad);

            // Mostrar la ventana de EnvioPedidosJFrame
            envioPedidosFrame.setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingresa una distancia válida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_confirmarPedidoButtonActionPerformed

    private void distanciaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distanciaTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_distanciaTFActionPerformed

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
        totalPedidoLabel.setText("Total del pedido: Q " + totalPedido);
    }//GEN-LAST:event_agregarProductoButtonActionPerformed

    private void vehiculoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehiculoComboBoxActionPerformed
        String motociclistaSeleccionado = (String) vehiculoComboBox.getSelectedItem();
        String distanciaTexto = distanciaTF.getText(); // Obtener la distancia ingresada

        // Verifica si el motociclista ya ha sido asignado a otra orden
        if (motociclistasAsignados.contains(motociclistaSeleccionado)) {
            JOptionPane.showMessageDialog(this, "Este motociclista ya ha sido asignado a otra orden.", "Error", JOptionPane.ERROR_MESSAGE);
            vehiculoComboBox.setSelectedIndex(0); // Reinicia la selección
        } else {
            System.out.println("Se selecciona moto");

            try {
                int distancia = Integer.parseInt(distanciaTexto);
                if (distancia > 10) {
                    JOptionPane.showMessageDialog(this, "La distancia no puede ser mayor a 10 km", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Configurar la distancia para el motociclista seleccionado
                envioPedidosFrame.setDistanciaMotociclista(motociclistaSeleccionado, distanciaTexto);
                System.out.println("Distancia para moto");

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Por favor, ingresa una distancia válida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_vehiculoComboBoxActionPerformed

    private void nuevoProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoProductoButtonActionPerformed
        NuevoProductoJFrame nuevoProductoFrame = new NuevoProductoJFrame(this);
        nuevoProductoFrame.setVisible(true);
    }//GEN-LAST:event_nuevoProductoButtonActionPerformed

    private void nuevaOrdenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaOrdenButtonActionPerformed
       reiniciarContadores();
    // Limpia la tabla productosAgregadosJTable si es necesario
    DefaultTableModel modeloProductosAgregados = (DefaultTableModel) productosAgregadosJTable.getModel();
    modeloProductosAgregados.setRowCount(0); // Borra todas las filas
    totalPedido = 0.0; // Reinicia el total del pedido si es necesario
    totalPedidoLabel.setText("Total del pedido: Q " + totalPedido);
    }//GEN-LAST:event_nuevaOrdenButtonActionPerformed

    private void historialPedidosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialPedidosButtonActionPerformed
        
        HistorialJFrame historialJFrame = new HistorialJFrame(this);
        historialJFrame.setVisible(true);
    }//GEN-LAST:event_historialPedidosButtonActionPerformed

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
    private javax.swing.JTextField distanciaTF;
    private gui.GaseosaPanel gaseosaPanel1;
    private javax.swing.JButton historialPedidosButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton nuevaOrdenButton;
    private javax.swing.JButton nuevoProductoButton;
    private gui.PapasPanel papasPanel1;
    private gui.PizzaPanel pizzaPanel1;
    private gui.PolloPanel polloPanel1;
    private javax.swing.JTable productosAgregadosJTable;
    private javax.swing.JTable productosJTable;
    private javax.swing.JLabel totalPedidoLabel;
    private javax.swing.JComboBox<String> vehiculoComboBox;
    // End of variables declaration//GEN-END:variables
}
