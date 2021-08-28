package com.projekt.CursedMemories.graficos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class DynamicShadow {
	public DynamicShadow() {
		
	}
	
	public static BufferedImage internalProcessor(String invoker, BufferedImage sprite) {
		if(invoker.contentEquals("Boss01")) {
			int HEIGHT = sprite.getHeight();
			int WIDTH  = sprite.getWidth();
			
			BufferedImage nsp = new BufferedImage(sprite.getWidth(), sprite.getHeight(), sprite.getType());
			Graphics2D img = nsp.createGraphics();
			
			for(int x = 0; x < WIDTH; x++)
			{
				for(int y = 0; y < HEIGHT; y++)
				{
					int px = sprite.getRGB(x, y);
					int alpha = (px >> 24) & 0xFF;
					if(alpha != 0) {
						img.setColor(new Color(0,0,0,100));
						img.drawLine(x, y + 1, x, y + 1);
					}
				}
			}
			img.dispose();
			return nsp;
		}
		return null;
	}
}
