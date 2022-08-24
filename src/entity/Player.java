package entity;

import java.awt.Color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;


import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		solidArea = new Rectangle(8, 8, 20, 20); //player hitbox should be smaller than player size itself
								//x, y, width, height
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23; //center of a 50x50 map
		worldY = gp.tileSize * 21; //center of a 50x50 map
		speed = 4;
		direction = "down";
		idle = true;
	}
	
	public void getPlayerImage() {
		
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/player/player_back_idle.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_back_run1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_back_run2.png"));
			
			down = ImageIO.read(getClass().getResourceAsStream("/player/player_front_idle.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_front_run1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_front_run2.png"));
			
			left = ImageIO.read(getClass().getResourceAsStream("/player/player_left_idle.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_run1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_run2.png"));
			
			right = ImageIO.read(getClass().getResourceAsStream("/player/player_right_idle.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_run1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_run2.png"));
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		//upper left corner is 0,0
		
		if (keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
				idle = false;
			} else if (keyH.downPressed == true) {
				direction = "down";
				idle = false;
			} else if (keyH.leftPressed == true) {
				direction = "left";
				idle = false;
			} else if (keyH.rightPressed == true) {
				direction = "right";
				idle = false;
			} 
			
			collisionOn = false;
			gp.collisionCheck.checkTile(this);
			
			//Collision checking
			if (collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			/*
			 * the update method gets called 60 times per second
			 * since it is inside the game loop
			 * each frame will increase the counter by 1, and when
			 * the counter hits 12, it will change the sprite
			 * 
			 * tldr: changes the sprite every 12 frames
			 */
			spriteCounter++;
			if(spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		} else {
			idle = true;
		}
	}
	
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if (idle == true) {
				image = up;
			}
			else if (spriteNum == 1) {
				image = up1;
			} 
			else if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (idle == true) {
				image = down;
			}
			else if (spriteNum == 1) {
				image = down1;
			} 
			else if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (idle == true) {
				image = left;
			}
			else if (spriteNum == 1) {
				image = left1;
			} 
			else if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (idle == true) {
				image = right;
			}
			else if (spriteNum == 1) {
				image = right1;
			} 
			else if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		//draws an image on the screen
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		//null is image observer
		
		
	}
}
