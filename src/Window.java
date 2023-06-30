import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JPanel;

public class Window extends JPanel implements MouseListener, ActionListener, KeyListener{
	private Flames flames;
	static final int GROUNDH = 75;
	static final int GROUNDY = FlameFall.h-GROUNDH;
	private Timer move = new Timer(5, this);
	private Timer fallInterval = new Timer(1000, this);
	private int times;
	private int cur;
	private Player p;
	private Background game, start, end;
	private boolean startScreen, over, hard;
	
	public Window() {
		setFocusable(true);
		addMouseListener(this);
		addKeyListener(this);
		p = new Player();
		game = new Background("bg2.png");	//<a href="https://www.vecteezy.com/free-vector/relief">Relief Vectors by Vecteezy</a>
		start = new Background("start.png");
		end = new Background("bg3.png");
		startScreen = true;
		reset();
	}
	
	public void reset() {
		flames = new Flames();
		p.setHit(false);
		over = false;
		hard = false;
		times = ((int)(Math.random()*15))+30;
		cur = 1;
		//System.out.println("flames: "+times); times is total number of flames in this round
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.black);
		if(startScreen) {
			start.paint(g);
		}
		else {
			gameScreen(g, over);
		}
	}
	
	public void gameScreen(Graphics g, boolean ended) {
		if(!ended) {
			game.paint(g);
		}
		else {
			end.paint(g);
		}
		p.paint(g);
		g.setColor(new Color(15, 11, 1));
		g.fillRect(0, FlameFall.h-GROUNDH, FlameFall.w, GROUNDH);
		if(!ended)
			flames.paint(g);
		else{
			String text;
			if(p.hit())
				text = "Game Over";
			else
				text = "You Won!";
			displayText(g, text, FlameFall.h/3);
			displayText(g, "Click the screen to play again", FlameFall.h/2);
		}
		
	}
	
	public void displayText(Graphics g, String text, int y) {
		g.setFont(new Font("Sans Serif",0, 50));
		int l = g.getFontMetrics().stringWidth(text);
		g.setColor(Color.white);
		g.drawString(text, (FlameFall.w-l)/2, y);
	}
	
	public void startGame() {
		move.start();
		fallInterval.start();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== move) {
			flames.moveUpdate();
			if(flames.empty()|| flames.hitPlayer(p)) {
				if(flames.hitPlayer(p)) 
					p.setHit(true);
				move.stop();
				fallInterval.stop();
				over = true;
			}
			else
				move.restart();
		}
		if(e.getSource()==fallInterval) {
			cur+= flames.add(hard);
			if(cur == times/2) 
				hard = true;
			if(cur<times) {
				int timeRange = 15;
				int delay = (int)(Math.random()*timeRange);
				fallInterval.setDelay(delay);
				fallInterval.restart();
			}
			else
				fallInterval.stop();
		}
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(startScreen) {
			startScreen = false;
			startGame();
		}
		else if(over) {
			reset();
			startGame();
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT) 
			p.moveL();
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			p.moveR();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}