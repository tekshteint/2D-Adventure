package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_token extends SuperObject{

	public OBJ_token() {
		name = "token";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/token.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
	
	}
}
