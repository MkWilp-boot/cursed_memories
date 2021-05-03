package com.xesque.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.world.Camera;

public class Bullet extends Entity
{
	private double dx, dy;
	private double spd = 6.0;
	private int lifeTime = 80, cur_life = 0;
	private Color color;
	
	
	public Bullet(int x, int y, int w, int h, BufferedImage sprite, double dx, double dy, Color color) 
	{
		super(x, y, w, h, sprite);
		this.dx = dx;
		this.dy = dy;
		this.color = color;
	}

	public void tick()
	{
		x += dx * spd;
		y += dy * spd;
		cur_life++;
		if(cur_life == lifeTime)
		{
			Game.bullets.remove(this);
			return;
		}
	}
	
	public void render(Graphics gfx)
	{
		gfx.setColor(this.color);
		gfx.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, w, h);
	}
}
