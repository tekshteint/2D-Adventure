package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public int checkObject(Entity e, boolean player) {
		int index = 999;
		
		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				//Entity's solid area position
				e.solidArea.x = e.worldX + e.solidArea.x;
				e.solidArea.y = e.worldY + e.solidArea.y;

				//Object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(e.direction) { //simulate entity movement and check where it will be after it moved
				case "up":
					e.solidArea.y -= e.speed;
					if (e.solidArea.intersects(gp.obj[i].solidArea)) { //determines whether or not this rectangle and the specified rectangle intersect. returns boolean
						if (gp.obj[i].collisionOn) {
							e.collisionOn = true;
						}
						if (player == true) {
							index = i; //will allow only player to pickup objects
						}
					}
					break;
				case "down":
					e.solidArea.y += e.speed;
					if (e.solidArea.intersects(gp.obj[i].solidArea)) { //determines whether or not this rectangle and the specified rectangle intersect. returns boolean
						if (gp.obj[i].collisionOn) {
							e.collisionOn = true;
						}
						if (player == true) {
							index = i; //will allow only player to pickup objects
						}					}
					break;
				case "left":
					e.solidArea.x -= e.speed;
					if (e.solidArea.intersects(gp.obj[i].solidArea)) { //determines whether or not this rectangle and the specified rectangle intersect. returns boolean
						if (gp.obj[i].collisionOn) {
							e.collisionOn = true;
						}
						if (player == true) {
							index = i; //will allow only player to pickup objects
						}					}
					break;
				case "right":
					e.solidArea.x += e.speed;
					if (e.solidArea.intersects(gp.obj[i].solidArea)) { //determines whether or not this rectangle and the specified rectangle intersect. returns boolean
						if (gp.obj[i].collisionOn) {
							e.collisionOn = true;
						}
						if (player == true) {
							index = i; //will allow only player to pickup objects
						}					}
					break;
				} e.solidArea.x = e.solidAreaDefaultX;
				e.solidArea.y = e.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		
		return index; //index of object if any
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
			} //else if (gp.obj[])
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
