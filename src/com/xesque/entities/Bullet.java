package com.xesque.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.world.Camera;

public class Bullet extends Entity
{
	private double dx, dy;
	private double spd;
	private int lifeTime = 100, cur_life = 0;
	private Color color;
	private BufferedImage spr = Game.spritesheet.getSprite(288, 32, 32, 32);
	
	
	public Bullet(int x, int y, int w, int h, BufferedImage sprite, double dx, double dy, Color color, double speed) 
	{
		super(x, y, w, h, sprite);
		this.dx = dx;
		this.dy = dy;
		this.color = color;
		this.spd = speed;
	}

	public void tick()
	{
		x += dx * spd;
		y += dy * spd;
		cur_life++;
		if(cur_life >= lifeTime)
		{
			cur_life = 0;
			Game.bullets.remove(this);
			Game.bulletsEn.remove(this);
			return;
		}
	}
	
	public void render(Graphics gfx)
	{
		//Graphics2D g = (Graphics2D) gfx;
		//g.setColor(new Color(173,96,0,100));
		//g.fillOval(this.getX() - Camera.x - 2, this.getY() - Camera.y - 2, w + 5, h + 5);
		gfx.drawImage(spr, this.getX() - Camera.x, this.getY() - Camera.y, w, h, null);
		/*
		gfx.setColor(this.color);
		gfx.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, w, h);
		*/
	}
}
