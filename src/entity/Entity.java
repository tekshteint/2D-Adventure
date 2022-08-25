package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
public class Entity { //parent class for all entities

	public int worldX, worldY;
	public int speed;
	public boolean idle;
	
	//BufferedImage describes an image with an accessible buffer of image data
	//(store the image files)
	public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	public boolean collisionOn = false;
	
}
