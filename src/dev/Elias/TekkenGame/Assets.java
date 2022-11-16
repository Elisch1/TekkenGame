package dev.Elias.TekkenGame;

import java.awt.image.BufferedImage;

public class Assets {
	
	
//	horizontal = 69,00 vertical = 83,00
	private static final int width = 69, height = 83;
	
	public static BufferedImage redStandOne, redStandTwo, redStandThree, redStandFour;

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/RedFighterStandSheet.png"));
		
		redStandOne = sheet.crop(0, 0, width, height);
		redStandTwo = sheet.crop(width, 0, width, height);
		redStandThree = sheet.crop(width * 2, 0, width, height);
		redStandFour = sheet.crop(width * 3, 0, width, height);
	}
	
}
