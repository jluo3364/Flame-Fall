import java.awt.*;
import java.io.File;


import javax.imageio.*;
public class Flame {
	//flame images: pch.vector / Freepik
	private Image flame;
	private double x, y, w, h;
	private double startX;
	private double direction;	//top angle of triangle formed from x to targetX
	private int targetX;	//where flame lands
	private double speed;	//change in y of every move
	
	public Flame(double speed) {
		startX = (int)(Math.random()*FlameFall.w);
		x= startX;
		this.speed = speed;
		Integer flameVer = (int)(Math.random()*5)+1;
		File flameFile = new File("src/img/flame"+flameVer.toString()+".png");
		try {
			flame = ImageIO.read(flameFile);
			w = flame.getWidth(null);
			h = flame.getHeight(null);
			flame = flame.getScaledInstance((int)(w/4.8), (int)(h/6), Image.SCALE_DEFAULT);
			w = flame.getWidth(null);
			h = flame.getHeight(null);
			y= -1*h-Math.random()*500;
			setTarget((int)(Math.random()*FlameFall.w));
			direction = Math.atan(xDistance()/Window.GROUNDY);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("file error");
		}
		
	}
	
	public void setTarget(int targetX) {
		if(targetX + w>FlameFall.w)
			this.targetX = (int) (targetX - w);
		else
			this.targetX = targetX;
	}

	private boolean hitTop(Player p) {
		boolean yRange = y+h < p.bottomY() && y+h>Window.GROUNDY-p.h()*4/5;
		boolean xRange = false;
		for(double i = x; i<x+w; i++) {
			if(i>p.topX() && i<p.topX()+p.topW()) {
				xRange = true;
			}
		}
		return(yRange&& xRange);
	}
	
	private boolean hitBottom(Player p) {
		boolean yRange = y+h < Window.GROUNDY && y+h>p.bottomY();
		boolean leftXRange = x>p.x() && x <p.x()+p.w();
		double rightX = x+w;
		boolean rightXRange = rightX>p.x() && x<p.x()+p.w();
		boolean targetXRange = leftXRange||rightXRange;
		return(yRange&& targetXRange);
	}
	
	public boolean hitPlayer(Player p) {

		return(hitTop(p)||hitBottom(p));
	}
	
	private double xDistance() {
		return targetX-startX;
	}
	
	
	public double w() {
		return w;
	}
	
	public double h() {
		return h;
	}

	public boolean hitGround() {
		return (y+h >= Window.GROUNDY );
	}

	
	public void move() {	
		double ychange = Math.abs(speed*Math.cos(direction));
		int totalMoves = (int)(Window.GROUNDY/ychange);
		y += ychange;
		x+= xDistance()/totalMoves;
	}
	
	public void paint(Graphics g) {
		g.drawImage(flame, (int) x, (int) y, null);
	}
}
