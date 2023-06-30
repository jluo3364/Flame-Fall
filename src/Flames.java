import java.awt.*;
import java.util.ArrayList;

public class Flames {
	private ArrayList<Flame> flames;
	
	public Flames() {
		flames = new ArrayList<Flame>();
		flames.add(new Flame(speed(false)));
	}
	
	public boolean empty() {
		return flames.size()==0;
	}
	
	public double speed(boolean hard) {
		double low = 3.5;
		double range = 1.5;
		if(hard) {
			low = 4;
			range = 2;
		}
		return Math.random()*range+low;
	}
	
	public int add(boolean hard) {
		int more = 3;
		int min = 2;
		if(hard) { 
			min = 3;
		}
		int toAdd = (int)(Math.random()*more+min);
		for(int i = 0; i<toAdd; i++) {
			flames.add(new Flame(speed(hard)));
		}
		System.out.println(toAdd);
		return toAdd;
	}
	
	public void moveUpdate() {
		for(int i = flames.size()-1; i>=0; i--) {
			flames.get(i).move();
			if(flames.get(i).hitGround()) {
				flames.remove(i);
			}
		}		
	}
	
	public boolean hitPlayer(Player p) {
		for(Flame f: flames) {
			if(f.hitPlayer(p))
				return true;
		}
		return false;
	}
	
	public void paint(Graphics g) {
		for(Flame f: flames) {
			f.paint(g);
		}
	}
}
