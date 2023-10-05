package gui;


import com.mycompany.practica2.Orden;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author Usuario
 */
public class MovimientoImagen extends JPanel {
    private Image imagenDelivery;
    private int x;
    private int direccion;
    private Timer timer;
    private double escala = 0.2;
    private EnvioPedidosJFrame envioPedidosFrame;
    private Image imagenFondo;
    private int velocidad = 1;
    private Date fechaLlegadaDerecha; 
    private boolean llegoALaDerecha = false;
    
    public MovimientoImagen() {
        try {
            imagenDelivery = ImageIO.read(new File("C:/Users/Usuario/Downloads/delivery-man.png"));
            imagenFondo = ImageIO.read(new File("C:/Users/Usuario/Downloads/road (1).png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Establecer el tamaño del panel según la escala
        setPreferredSize(new Dimension((int)(imagenDelivery.getWidth(this) * escala), (int)(imagenDelivery.getHeight(this) * escala)));

        x = 0;
        direccion = 1;

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverImagen();
            }
        });

    }
public void setVelocidad(int velocidad) {
    this.velocidad = velocidad;
}

    public void iniciarMovimiento() {
        if (!timer.isRunning()) {
            x = 0; // Restablecer la posición inicial
            direccion = 1; // Restablecer la dirección
            timer.start();
        }
    }
    public void detenerMovimiento() {
        if (timer.isRunning()) {
            timer.stop();
            x = 0; // Restablece la posición inicial
            direccion = 1; // Restablece la dirección
            repaint();
        }
    }

    public void setEnvioPedidosFrame(EnvioPedidosJFrame envioPedidosFrame) {
    this.envioPedidosFrame = envioPedidosFrame;
}
    private void moverImagen() {
        x += velocidad * direccion;

    if (x <= 0 || x >= getWidth() - imagenDelivery.getWidth(this) * escala) {
        direccion *= -1; // Cambiar de dirección al llegar al borde

        // Verificar si ha completado una ida y vuelta
        if (x <= 0) {
            timer.stop(); // Detener el temporizador después de una ida y vuelta
        } else if (x >= getWidth() - imagenDelivery.getWidth(this) * escala && !llegoALaDerecha) {
            // Cuando llega al lado derecho antes de girar y no ha llegado antes, registra la fecha y hora
            fechaLlegadaDerecha = new Date();
            llegoALaDerecha = true;
        }
    }

    repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
        if (imagenDelivery != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Dibujar la imagen con la escala
            int nuevaAnchura = (int) (imagenDelivery.getWidth(this) * escala);
            int nuevaAltura = (int) (imagenDelivery.getHeight(this) * escala);

            if (direccion == 1) {
                g.drawImage(imagenDelivery, x, 0, nuevaAnchura, nuevaAltura, this);
            } else if (direccion == -1) {
                g.drawImage(imagenDelivery, x + nuevaAnchura, 0, -nuevaAnchura, nuevaAltura, this);
            }
        }
    }

    //public static void main(String[] args) {
    //    JFrame frame = new JFrame("Movimiento de Imagen");
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(90, 45); // Establecer el tamaño del frame a 90x45

        //MovimientoImagen movimientoImagen = new MovimientoImagen();
        //frame.add(movimientoImagen);

        //frame.setVisible(true);
    //}
}
