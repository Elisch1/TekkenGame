package dev.Elias.TekkenGame;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		//RedFighterStandSheet horizontal = 69,00 vertical = 83,00
		return sheet.getSubimage(x, y, width, height);
	}

}
