package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.main.ImageUtils;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Spear extends Bullet 
{
	// ANIMATIONS
	private Integer step = 0;
	private Integer max = 6;
	private Integer reachedSprite = 0;
	private Integer animationCoolDown = 7;
	
	//LIFE SPAM
	private Integer life = 0;
	private Integer maxLife = 100;
	
	// DIRECTION
	private Double dx, dy;
	private Double spd;
	
	private BufferedImage[] sprites = {
			Game.spritesheet.getSprite(0, 704, 32, 32),
			Game.spritesheet.getSprite(32, 704, 32, 32),
			Game.spritesheet.getSprite(64, 704, 32, 32),
			Game.spritesheet.getSprite(96, 704, 32, 32),
			Game.spritesheet.getSprite(128, 704, 32, 32),
			Game.spritesheet.getSprite(160, 704, 32, 32)
	};
	
	
	
	public Spear(int x, int y, int w, int h, BufferedImage sprite, double dx, double dy, Color color, double speed,
			Color pColor, boolean build, boolean passWall) {
		super(x, y, w, h, sprite, dx, dy, color, speed, pColor, build, passWall);
		this.dx = dx;
		this.dy = dy;
		this.spd = speed;
	}

	public Spear(int x, int y, int w, int h, BufferedImage sprite, double dx, double dy, Color color, double speed,
			Color pColor, boolean build) {
		super(x, y, w, h, sprite, dx, dy, color, speed, pColor, build);
		
		if(dx < 0 && dy == 0) {
			for(int i = 0; i < this.sprites.length; i++)
				this.sprites[i] = ImageUtils.rotate(sprites[i], -90);
		}
		else if(dx > 0 && dy == 0) {
			for(int i = 0; i < this.sprites.length; i++)
				this.sprites[i] = ImageUtils.rotate(sprites[i], 90);
		}
		else if(dx == 0 && dy > 0) {
			for(int i = 0; i < this.sprites.length; i++)
				this.sprites[i] = ImageUtils.rotate(sprites[i], 180);
		}
		this.dx = dx;
		this.dy = dy;
		this.spd = speed;
	}

	public void tick() {
		// ANIMATIONS
		reachedSprite++;
		if(reachedSprite >= animationCoolDown) {
			reachedSprite = 0;
			step++;
			if(step >= max) {
				step = 0;
			}
		}
		
		// MOVING
		try {
			if(World.isFreeDynamic( (int)(x + (dx * spd)), (int)(y + (dy * spd)), this.w, this.h)) {
				x += dx * spd;
				y += dy * spd;
			}
			else {
				Game.bullets.remove(this);
				Game.bulletsEn.remove(this);
			}
		} catch(IndexOutOfBoundsException e) {
			life = 0;
			Game.bullets.remove(this);
			Game.bulletsEn.remove(this);
		}
		
		// LIFE SPAM
		life++;
		if(life >= maxLife) {
			life = 0;
			Game.bullets.remove(this);
			Game.bulletsEn.remove(this);
			return;
		}
	}
	
	public void render(Graphics gfx) {
		gfx.drawImage(sprites[step], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}
}
