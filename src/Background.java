import javax.swing.*;
import java.awt.event.ActionEvent;

public class Background extends JFrame {
    public static JFrame frame = new JFrame("Background");
    private int bx = 80;
    private int by = 250;
    private int cx = 80;
    private int cy = 350;


    private JPanel panel1;
    private JPanel panel2;


    public Background() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(417, 626);
        ImageIcon icon = new ImageIcon("C:\\Users\\jjash\\Desktop\\Doodle-Jump-main\\images\\hintergrund-options.jpg");
        JLabel l1 = new JLabel(icon);
        frame.add(l1); // Label hinzufügen
        frame.setVisible(true);
        frame.setLayout(null);

        panel2 = new JPanel();
        JButton c = new JButton(new ImageIcon("images/night.png"));
        c.setBorder(null);
        c.setLayout(null);
        frame.add(panel2);
        panel2.add(c);

        panel2.setBounds(cx, cy, 200, 50);


        panel1 = new JPanel();

        JButton b = new JButton(new ImageIcon("images/day.png"));
        b.setBorder(null);
        b.setLayout(null);
        frame.add(panel1);
        panel1.add(b);
        panel1.setBounds(bx, by, 186, 50);

        b.addActionListener((ActionEvent e) -> {
            new DoodleJump_newbackground();
            panel1.setVisible(true);
        });

        // Calls the Login class when clicked to the button
        c.addActionListener((ActionEvent e) -> {
            new DoodleJump();
            panel2.setVisible(true);
        });

    }
    }
