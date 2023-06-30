import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Player {
	private int w, h, x, y;
	private Image obj, burned, topbox, bottom;
	private double factor = 6.5;
	private boolean hit = false;
	
	public Player() {
		File normal = new File("src/img/robot.png");
		File red = new File("src/img/burned.png");
		File box = new File("src/img/box.png");
		File box2 = new File("src/img/bottom.png");
		
		try {
			obj = ImageIO.read(normal);
			w = obj.getWidth(null);
			h = obj.getHeight(null);
			x = FlameFall.w/2;
			obj = obj.getScaledInstance((int)(w/factor), (int)(h/factor), Image.SCALE_DEFAULT);
			w = obj.getWidth(null);
			h = obj.getHeight(null);
			y = Window.GROUNDY-h;
			burned = ImageIO.read(red);
			burned = burned.getScaledInstance(w, h, Image.SCALE_DEFAULT);
			topbox = ImageIO.read(box);
			topbox = topbox.getScaledInstance((int)(topbox.getWidth(null)/factor), (int)(topbox.getHeight(null)/factor), Image.SCALE_DEFAULT);
			bottom = ImageIO.read(box2);
			bottom = bottom.getScaledInstance((int)(bottom.getWidth(null)/factor), (int)(bottom.getHeight(null)/factor), Image.SCALE_DEFAULT);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int x() {
		return x;
	}
	
	public int h() {
		return h;
	}
	
	public int topW() {
		return topbox.getWidth(null);
	}
	
	public int topX() {
		int dif = w-topW();
		return x+dif/2;
	}
	
	public int bottomY() {
		return y+topbox.getHeight(null);
	}
	
	public int w() {
		return w;
	}
	
	public void moveL() {
		if(x>0)
			x-=15;
	}
	public void moveR() {
		if(x+w<FlameFall.w)
			x+=15;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public boolean hit() {
		return hit;
	}
	
	public void paint(Graphics g) {
		if(!hit)
			g.drawImage(obj, x, y, null);
		else
			g.drawImage(burned, x,  y, null);
	}
}
