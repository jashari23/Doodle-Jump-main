import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {
	
	public static BufferedImage doodleForMenu = loadImage("images/doodleForMenu.png");
	public static BufferedImage background = loadImage("images/background-bell.png");
	public static BufferedImage background2 = loadImage("images/background-hell.png");
	public static BufferedImage playerLeft = loadImage("images/doodleLeft.png");
	public static BufferedImage playerRight = loadImage("images/doodleRight.png");
	public static BufferedImage playerLeft2 = loadImage("images/doodleLeft2.png");
	public static BufferedImage playerRight2 = loadImage("images/doodleRight2.png");

	
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}