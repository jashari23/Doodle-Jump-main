import javax.swing.*;
import java.awt.event.ActionEvent;

public class Options extends JFrame{

    public static JFrame frame = new JFrame("Options");

    private int bx = 80;
    private int by = 250;
    private int cx = 80;
    private int cy = 350;



    private JPanel panel1;
    private JPanel panel2;


    public Options() {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(417, 626);
        ImageIcon icon = new ImageIcon("images/hintergrund-options.jpg");
        JLabel l1 = new JLabel(icon);
        frame.add(l1); // Label hinzufügen
        frame.setVisible(true);
        frame.setLayout(null);

        panel2 = new JPanel();
        JButton c = new JButton(new ImageIcon("images/character-button.png"));
        c.setBorder(null);
        c.setLayout(null);
        frame.add(panel2);
        panel2.add(c);
        panel2.setBounds(cx, cy, 240, 65);


        panel1 = new JPanel();

        JButton b = new JButton(new ImageIcon("images/background-button.png"));
        b.setBorder(null);
        b.setLayout(null);
        frame.add(panel1);
        panel1.add(b);
        panel1.setBounds(bx, by, 240, 65);

        b.addActionListener((ActionEvent e) -> {
            new Background();
            panel1.setVisible(true);
        });


        c.addActionListener((ActionEvent e) -> {
            new Character();
            panel2.setVisible(true);
        });
    }

}

