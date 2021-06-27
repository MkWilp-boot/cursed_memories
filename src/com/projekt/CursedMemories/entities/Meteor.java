package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Meteor extends Bullet {

	private double dx, dy;
	private double spd;
	private int lifeTime = 200, cur_life = 0;
	private Color color;
	private BufferedImage spr;
	private String size;
	private BufferedImage shadow;
	private String direction = "horizontal";
	
	public Meteor(int x, int y, int w, int h, BufferedImage sprite, double dx, double dy, Color color, double speed,
			Color pColor, boolean build, String size) {
		super(x, y, w, h, sprite, dx, dy, color, speed, pColor, build);
		
		this.dx = dx;
		this.dy = dy;
		this.spr = sprite;
		this.color = color;
		this.spd = speed;
		this.size = size;
		prepareShadow();
	}
	
	public Meteor(int x, int y, int w, int h, BufferedImage sprite, double dx, double dy, Color color, double speed,
			Color pColor, boolean build, String size, String direction) {
		super(x, y, w, h, sprite, dx, dy, color, speed, pColor, build);
		
		this.dx = dx;
		this.dy = dy;
		this.spr = sprite;
		this.color = color;
		this.spd = speed;
		this.size = size;
		this.direction = direction;
		prepareShadow();
	}
	
	private void prepareShadow() {
		if(this.size == "big") {
			this.shadow = Game.spritesheet.getSprite(0, 768, 64, 32);
		}
		else if(this.size == "small") {
			this.shadow = Game.spritesheet.getSprite(64, 768, 32, 32);
		}
	}
	
	public void tick() {
		if(World.isFreeDynamic( (int)(x + (dx * spd)), (int)(y + (dy * spd)), this.w, this.h)) {
			x += dx * spd;
			y += dy * spd;
		}
		else {
			Game.bullets.remove(this);
			Game.bulletsEn.remove(this);
		}
		
		cur_life++;
		if(cur_life >= lifeTime) {
			cur_life = 0;
			Game.bullets.remove(this);
			Game.bulletsEn.remove(this);
			return;
		}
	}
	
	public void render(Graphics gfx) {
		if(direction == "horizontal") {
			gfx.drawImage(shadow, this.getX() - Camera.x - 16, this.getY() - Camera.y + 10, null);
		}
		else if(direction == "vertical") {
			gfx.drawImage(shadow, this.getX() - Camera.x + 6, this.getY() - Camera.y + 16, null);
		}
		
		if(this.spr == null) {
			gfx.setColor(this.color);
			gfx.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, w, h);
			return;
		}
		
		gfx.drawImage(spr, this.getX() - Camera.x, this.getY() - Camera.y, w, h, null);
	}
}
