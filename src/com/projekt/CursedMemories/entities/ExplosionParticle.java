package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;

public class ExplosionParticle extends Entity {
	
	public Integer lifeTime = 40;
	public Integer curLife = 0;
	
	public Integer speed = 1;
	public double dx = 0;
	public double dy = 0;

	public Color color = Color.YELLOW;
	
	public ExplosionParticle(int x, int y, int w, int h, BufferedImage sprite) {
		super(x, y, w, h, sprite);
		
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
	}
	
	public ExplosionParticle(int x, int y, int w, int h, BufferedImage sprite, Color color) {
		super(x, y, w, h, sprite);
		
		dx = new Random().nextGaussian();
		dy = new Random().nextGaussian();
	}
	
	public void render(Graphics gfx) {
		gfx.setColor(this.color);
		gfx.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, 5, 5);
	}
	
	public void tick() {
		x += dx * speed;
		y += dy * speed;
		curLife++;
		if(lifeTime == curLife) {
			Game.entities.remove(this);
		}
	}
	
}
