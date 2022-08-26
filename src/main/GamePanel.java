package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 pixel size for any tile in the game
	final int scale = 3; //scales the panel due to monitor size
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //48x16 = 768 pixels
	public final int screenHeight = tileSize * maxScreenRow;//48x12 = 576 pixels
	int FPS = 60;
	
	//WORLD MAP SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	
	KeyHandler keyH = new KeyHandler();
	public Sound sound = new Sound();
	Thread gameThread;
	public Player player = new Player(this,keyH);
	TileManager tileM = new TileManager(this);
	public CollisionChecker collisionCheck = new CollisionChecker(this);
	public ObjectManager objM = new ObjectManager(this); 
	public SuperObject obj[] = new SuperObject[10]; //10 object slots. Display 10 objects at the same time but not limited to 10 total
	
		
	GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set the size of this class
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //if set to true all the drawing from this component will be done in an offscreen painting buffer
		this.addKeyListener(keyH);
		this.setFocusable(true); //this gamepanel can be "focused" to recieve key input
		
		
	}

	public void setupGame() {
		objM.setObject();
		playMusic(0);
	}
	
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		//game loop
		while (gameThread != null) {
			
			//first part: update information
			update();
			
			//second part: draw the screen with updated information
			repaint(); //this calls the paintComponent method
		
			//third part: restrict to 60 fps
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		player.update();	
	}
	public void paintComponent(Graphics g) { //Graphics is a class that has many functions to draw objects on the screen
		super.paintComponent(g); //THIS MUST BE USED ON ANY JPANEL
		
		Graphics2D g2 = (Graphics2D)g;
		/*
		 * Graphics2D extends the graphics class to provide more sophisticated
		 * control over geometry, coordinate transformations, color management,
		 * and text layout
		 */
		
		
		tileM.draw(g2); //draw tiles first then player so they can be the background
		
		
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		
		player.draw(g2);
		
		
		g2.dispose(); //disposes of this graphics context and release any system resources it uses
		
		
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSoundEffect(int i) {
		sound.setFile(i);
		sound.play();
	}
}
