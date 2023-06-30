import javax.swing.*;

public class FlameFall {
	static int w = 1000;
	static int h = 800;
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Flame Fall");
		window.add(new Window());
		window.setSize(w, h);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
	}
}
