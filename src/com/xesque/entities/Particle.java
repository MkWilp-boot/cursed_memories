package com.xesque.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.world.Camera;

public class Particle extends Entity {

	private int lifeTime = 15;
	private int curLife  = 0;
	private int spd = 2;
	private Color color;
	
	private double dx, dy;
	
	public Particle(int x, int y, int w, int h, BufferedImage sprite, Color color) {
		super(x, y, w, h, sprite);
		dx = 0.3;
		dy = 0.3;
		this.color = color;
	}

	public void tick() {
		x += dx * spd;
		y += dy * spd;
		curLife++;
		if(lifeTime == curLife) {
			Game.entities.remove(this);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, w, h);
	}
}
