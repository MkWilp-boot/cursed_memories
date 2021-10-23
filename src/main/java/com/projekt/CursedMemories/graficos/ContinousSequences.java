package com.projekt.CursedMemories.graficos;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.projekt.CursedMemories.main.Game;

public class ContinousSequences {
	public Integer generator;

	public ContinousSequences() {
	}
	
	public List<BufferedImage> GetsGnerateion(Integer generationType) {
		List<BufferedImage> lst = new ArrayList<>();
		
		switch(generationType) {
			case 1:
				lst = generateGameOverSequence();
				break;
			case 2:
				lst = generateDefeatedBossSequence();
				break;
		}
		return lst;
	}
	
	private List<BufferedImage> generateDefeatedBossSequence() {
		List<BufferedImage> lst = new ArrayList<>();
		try {
			for(int i = 0; i < 300; i++) {
				BufferedImage image = ImageIO.read(new File(Game.ROOT_DIR+"\\res\\CHEFE_ELIMINADO_"+i+".png"));
				image = toBufferedImage(image.getScaledInstance(400, 80, Image.SCALE_DEFAULT));
				lst.add(image);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return lst;
	}
	
	private List<BufferedImage> generateGameOverSequence() {
		List<BufferedImage> lst = new ArrayList<>();
		try {
			for(int i = 0; i < 300; i++) {
				BufferedImage image = ImageIO.read(new File(Game.ROOT_DIR+"\\res\\Game_over_"+i+".png"));
				image = toBufferedImage(image.getScaledInstance(600, 120, Image.SCALE_DEFAULT));
				lst.add(image);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return lst;
	}
	
	public BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}
