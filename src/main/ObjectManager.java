package main;

import object.*;

public class ObjectManager {

	GamePanel gp;
	
	public ObjectManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_token();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 3 * gp.tileSize;
		
		gp.obj[1] = new OBJ_key();
		gp.obj[1].worldX = 1 * gp.tileSize;
		gp.obj[1].worldY = 48 * gp.tileSize;
		
		gp.obj[2] = new OBJ_door();
		gp.obj[2].worldX = 32 * gp.tileSize;
		gp.obj[2].worldY = 12 * gp.tileSize;
		
		gp.obj[3] = new OBJ_chest();
		gp.obj[3].worldX = 23 * gp.tileSize;
		gp.obj[3].worldY = 40 * gp.tileSize;
		
		
	}
}
