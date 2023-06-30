import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	private Image bg;
	
	public Background(String name) {
		try {
			bg = ImageIO.read(new File("src/img/"+name));
			bg = bg.getScaledInstance(FlameFall.w, FlameFall.h, Image.SCALE_AREA_AVERAGING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
	}
}
