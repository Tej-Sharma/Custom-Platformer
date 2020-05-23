package com.Tejas.platformer.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Helper class to load in images from a string path
 * Used to load in images that represent levels
 * @author tejas
 *
 */
public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path)
	{
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	

}
