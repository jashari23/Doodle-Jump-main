import javax.swing.*;

public class Login extends JFrame {

    public static JFrame frame = new JFrame("Username");
    public static String userName;

    public Login() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userName = JOptionPane.showInputDialog(frame, "Valid usernames may contain characters only!",
                "ENTER A VALID USERNAME", JOptionPane.PLAIN_MESSAGE);
        frame.pack();
        new DoodleJump();
        frame.setVisible(true);

    }


}
