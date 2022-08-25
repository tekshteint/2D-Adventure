package object;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import main.GamePanel;

import java.awt.Graphics2D;

public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collisionOn = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX; //where to be drawn on the screen
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		//chunking
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
				g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
}
