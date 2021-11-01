package com.projekt.CursedMemories.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class ImageUtils {

	public static BufferedImage rotate(BufferedImage bimg, double angle) {
    	
    	if(bimg != null ) {
    		int w = bimg.getWidth();    
            int h = bimg.getHeight();

            BufferedImage rotated = new BufferedImage(w, h, bimg.getType());  
            Graphics2D graphic = rotated.createGraphics();
            graphic.rotate(Math.toRadians(angle), w/2, h/2);
            graphic.drawImage(bimg, null, 0, 0);
            graphic.dispose();
            return rotated;
    	}
    	else {
    		return null;
    	}
    }
}
