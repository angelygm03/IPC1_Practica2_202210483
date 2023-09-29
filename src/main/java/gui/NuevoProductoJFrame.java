package gui;

import com.mycompany.practica2.Practica2;
import com.mycompany.practica2.Producto;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class NuevoProductoJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NuevoProductoJFrame
     */
    public NuevoProductoJFrame() {
        initComponents();
        
        this.setVisible(true); //Hace visible la ventana
        this.setLocationRelativeTo(null); //La coloca al centro
        this.setResizable(false); //Bloquea el redimensionamiento de la ventana
        this.setTitle("Nuevo producto"); //Título a la ventana
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombreProductoTF = new javax.swing.JTextField();
        precioTF = new javax.swing.JTextField();
        agregarProductoButton = new javax.swing.JButton();
        regresarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setText("Agrega un Nuevo Producto");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel2.setText("Nombre del producto:");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel4.setText("Precio Q.");

        nombreProductoTF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nombreProductoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreProductoTFActionPerformed(evt);
            }
        });

        precioTF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        precioTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precioTFActionPerformed(evt);
            }
        });

        agregarProductoButton.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        agregarProductoButton.setText("Agregar");
        agregarProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoButtonActionPerformed(evt);
            }
        });

        regresarButton.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        regresarButton.setText("Regresar");
        regresarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(79, 79, 79)
                            .addComponent(agregarProductoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                            .addComponent(regresarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nombreProductoTF, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                                .addComponent(precioTF)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreProductoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(precioTF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarProductoButton)
                    .addComponent(regresarButton))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoButtonActionPerformed
        String nombre = nombreProductoTF.getText();
        String precioTexto = precioTF.getText();

        // Verificar si el precio es un número válido
        try {
            double precio = Double.parseDouble(precioTexto);

            // Crear un nuevo Producto
            Producto nuevoProducto = new Producto(nombre, precio);

            // Agregar el nuevo producto a la lista de productos
            Practica2.listaProductos.add(nuevoProducto);
                              
            // Limpiar los campos de texto
            nombreProductoTF.setText("");
            precioTF.setText("");

            JOptionPane.showMessageDialog(this, "Producto agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            
            JOptionPane.showMessageDialog(this, "Error: Por favor, ingresa un precio válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_agregarProductoButtonActionPerformed

    private void nombreProductoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreProductoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreProductoTFActionPerformed

    private void precioTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precioTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precioTFActionPerformed

    private void regresarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarButtonActionPerformed
        this.dispose();
        MenuPrincipalJFrame mainFrame = new MenuPrincipalJFrame();
        mainFrame.setVisible(true);
    }//GEN-LAST:event_regresarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(NuevoProductoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoProductoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoProductoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoProductoJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoProductoJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarProductoButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nombreProductoTF;
    private javax.swing.JTextField precioTF;
    private javax.swing.JButton regresarButton;
    // End of variables declaration//GEN-END:variables
}
