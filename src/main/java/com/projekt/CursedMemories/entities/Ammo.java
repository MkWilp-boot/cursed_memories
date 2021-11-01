package com.projekt.CursedMemories.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;

public class Ammo extends Entity 
{

	private Integer step = 0;
	private Integer max = 4;
	private Integer reachedSprite = 0;
	private Integer animationCoolDown = 7;
	
	private BufferedImage[] sprites = {
			Game.spritesheet.getSprite(448, 192, 32, 32),
			Game.spritesheet.getSprite(480, 192, 32, 32),
			Game.spritesheet.getSprite(512, 192, 32, 32),
			Game.spritesheet.getSprite(544, 192, 32, 32),
			Game.spritesheet.getSprite(576, 192, 32, 32),
	};
	
	public Ammo(int x, int y, int w, int h, BufferedImage sprite) {
		super(x, y, w, h, sprite);
	}

	public void tick() {
		reachedSprite++;
		if(reachedSprite >= animationCoolDown) {
			reachedSprite = 0;
			step++;
			if(step > max) {
				step = 0;
			}
		}
	}
	
	public void render(Graphics gfx) {
		gfx.drawImage(sprites[step], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

}
