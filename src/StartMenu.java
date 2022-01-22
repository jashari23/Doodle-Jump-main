import java.awt.Graphics;
import java.awt.event.*;


import javax.swing.*;

public class StartMenu extends JFrame implements KeyListener{
	
	private final int D_W = 900;
	private final int D_H = 1000;
	private int px = 380;
	private int py = 610;
	private int lx = 380;
	private int ly = 710;
	private int ox = 20;
	private int oy = 20;


	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;

	private JButton b;
	private JButton c;
	private JButton d;


	public StartMenu() {

		panel1 = new JPanel();

		b = new JButton(new ImageIcon("images/play.png"));
		b.setBorder(null);
		b.setLayout(null);

		panel1.add(b);
		panel1.setBounds(px, py, 185, 75);


		panel2 = new JPanel();
		c = new JButton(new ImageIcon("images/login.png"));
		c.setBorder(null);
		c.setLayout(null);

		panel2.add(c);
		panel2.setBounds(lx, ly, 185, 75);

		panel3 = new JPanel();
		d = new JButton(new ImageIcon("images/options.png"));
		d.setBorder(null);
		d.setLayout(null);

		panel3.add(d);
		panel3.setBounds(ox, oy, 185, 75);

		add(panel1);
		add(panel2);
		add(panel3);

		setTitle("Main Menu");
		setSize(D_W, D_H);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
		addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Calls the DoodleJump class when clicked to the button
		b.addActionListener((ActionEvent e) -> {
			new DoodleJump();
			setVisible(false);
		});

		// Calls the Login class when clicked to the button
		c.addActionListener((ActionEvent e) -> {
			new Login();
			setVisible(false);
		});
		// Calls the Login class when clicked to the button
		d.addActionListener((ActionEvent e) -> {
			new Options();
			setVisible(false);
		});
	}
	
	public void paint(Graphics g) {
		g.drawImage(Assets.background, 0, 0, this);
		g.drawImage(Assets.doodleForMenu, 435, 540, this);
	}
	
	public static void main(String[] args) {
		new StartMenu();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
