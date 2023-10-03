package gui;


import com.mycompany.practica2.Motociclista;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 *
 * @author Usuario
 */
public class MovimientoImagen2 extends JPanel {
    private Image imagenDelivery;
    private int x;
    private int direccion;
    private Timer timer;
    private double escala = 0.2;
    private EnvioPedidosJFrame envioPedidosFrame;
    private Image imagenFondo;
    private Motociclista motociclistaAsociado;
    
    public void setMotociclistaAsociado(Motociclista motociclista) {
        this.motociclistaAsociado = motociclista;
    }
    
    public MovimientoImagen2() {
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

    public void iniciarMovimiento() {
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    public void setEnvioPedidosFrame(EnvioPedidosJFrame envioPedidosFrame) {
        this.envioPedidosFrame = envioPedidosFrame;
    }
    
    private void moverImagen() {
        if (motociclistaAsociado != null) {
            x += direccion;

            if (x <= 0 || x >= getWidth() - imagenDelivery.getWidth(this) * escala) {
                direccion *= -1; // Cambiar de dirección al llegar al borde
            }

            repaint();
        }
    
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Movimiento de Imagen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(90, 45); // Establecer el tamaño del frame a 90x45

        MovimientoImagen2 movimientoImagen = new MovimientoImagen2();
        frame.add(movimientoImagen);

        frame.setVisible(true);
    }
 }
