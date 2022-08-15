package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Reloj {

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        JFrame f = new JFrame("Digital Clock");
        ClockLabel dateLable = new ClockLabel("date");
        ClockLabel timeLable = new ClockLabel("time");
        Visibilidad boton = new Visibilidad(f);
        boton.setText("Siempre Visible?");
        boton.setHorizontalAlignment(SwingConstants.RIGHT);
        boton.setForeground(Color.WHITE);
        boton.setSize(50, 30);
        boton.setBackground(Color.BLACK);
        boton.setFocusable(false);
        
        crearFuente();

        f.setSize(300, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(3, 1));
        //f.setUndecorated(true);
        f.add(dateLable);
        f.add(timeLable);
        f.add(boton);

        f.getContentPane().setBackground(Color.black);

        f.setVisible(true);
    }

    private static void crearFuente() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\RobotCrush.ttf")));

        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}

class ClockLabel extends JLabel implements ActionListener {

    String type;
    SimpleDateFormat sdf;

    public ClockLabel(String type) {
        this.type = type;
        setForeground(Color.WHITE);

        switch (type) {
            case "date":
                sdf = new SimpleDateFormat("EEEE dd");
                setFont(new Font("Robot Crush", Font.PLAIN, 18));
                setHorizontalAlignment(SwingConstants.CENTER);
                break;
            case "time":
                sdf = new SimpleDateFormat("HH:mm:ss");
                setFont(new Font("Robot Crush", Font.PLAIN, 60));
                setHorizontalAlignment(SwingConstants.CENTER);
                break;

            default:
                sdf = new SimpleDateFormat();
                break;
        }

        Timer t = new Timer(1000, this);
        t.start();
    }

    public void actionPerformed(ActionEvent ae) {
        Date d = new Date();
        setText(sdf.format(d));

    }

}

class Visibilidad extends JCheckBox implements ChangeListener {
    JFrame f;
    public Visibilidad(JFrame f){
      this.addChangeListener(this);
      this.f=f;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if(this.isSelected()==true){
            f.setAlwaysOnTop(true);
            
        }else{
            f.setAlwaysOnTop(false);
        }
    }
    
    
    

}
