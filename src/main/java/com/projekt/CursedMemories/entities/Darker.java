package com.projekt.CursedMemories.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;

public class Darker extends Boss {
	
	public static final BufferedImage IDLE_IMG = Game.spr_bDarker.getSprite(0, 255, 64, 64);

	public Darker(int x, int y, int w, int h, double speed, int vida, BufferedImage sprite) {
		super(x, y, w, h, speed, vida, sprite);
	}

	public void render(Graphics gfx) {
		gfx.drawImage(IDLE_IMG, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
	public void tick() {
		
	}
}
