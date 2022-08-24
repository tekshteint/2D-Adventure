package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	/*
	 * this will find the x and y coordinates 
	 * used for detecting collisions
	 */
	public void checkTile(Entity e) {
		int entLeftWorldX = e.worldX + e.solidArea.x;
		int entRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
		int entTopWorldY = e.worldY + e.solidArea.y;
		int entBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;
		
		int entLeftCol = entLeftWorldX / gp.tileSize;
		int entRightCol = entRightWorldX / gp.tileSize;
		int entTopRow = entTopWorldY / gp.tileSize;
		int entBottomRow = entBottomWorldY / gp.tileSize;
		
		int collision1, collision2; //only need to check 2 tiles per direction
		
		switch(e.direction) {
		case ("up"):
			entTopRow = (entTopWorldY - e.speed) / gp.tileSize;
			collision1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
			collision2 = gp.tileM.mapTileNum[entRightCol][entTopRow];
			if (gp.tileM.tile[collision1].collision == true || gp.tileM.tile[collision2].collision == true) {
				e.collisionOn = true;
			}
			break;
		case("down"):
			entBottomRow = (entBottomWorldY + e.speed) / gp.tileSize;
		collision1 = gp.tileM.mapTileNum[entLeftCol][entBottomRow];
		collision2 = gp.tileM.mapTileNum[entRightCol][entBottomRow];
		if (gp.tileM.tile[collision1].collision == true || gp.tileM.tile[collision2].collision == true) {
			e.collisionOn = true;
		}
			break;
		case("left"):
			entLeftCol = (entLeftWorldX - e.speed) / gp.tileSize;
		collision1 = gp.tileM.mapTileNum[entLeftCol][entTopRow];
		collision2 = gp.tileM.mapTileNum[entLeftCol][entBottomRow];
		if (gp.tileM.tile[collision1].collision == true || gp.tileM.tile[collision2].collision == true) {
			e.collisionOn = true;
		}
			break;
		case("right"):
			entRightCol = (entRightWorldX + e.speed) / gp.tileSize;
		collision1 = gp.tileM.mapTileNum[entRightCol][entTopRow];
		collision2 = gp.tileM.mapTileNum[entRightCol][entBottomRow];
		if (gp.tileM.tile[collision1].collision == true || gp.tileM.tile[collision2].collision == true) {
			e.collisionOn = true;
		}
			break;
		}
		
		
	}
}
