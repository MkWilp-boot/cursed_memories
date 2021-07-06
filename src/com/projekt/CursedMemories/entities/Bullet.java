package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Bullet extends Entity
{
	private double dx, dy;
	private double spd;
	private int lifeTime = 200, cur_life = 0;
	private Color color;
	private BufferedImage spr;
	private Color pColor;
	private boolean build = false;
	
	
	public Bullet(int x, int y, int w, int h, BufferedImage sprite, double dx, double dy, Color color, double speed, Color pColor, boolean build) 
	{
		super(x, y, w, h, sprite);
		this.dx = dx;
		this.dy = dy;
		this.spr = sprite;
		this.color = color;
		this.spd = speed;
		this.pColor = pColor;
		this.build = build;
	}

	public void tick()
	{
		Particle p = new Particle(this.x, this.y, 4, 4, null, this.pColor);
		Particle underP = new Particle(this.x, this.y + 10, 4, 4, null, new Color(0,0,0,100));
		
		if(World.isFreeDynamic( (int)(x + (dx * spd)), (int)(y + (dy * spd)), this.w, this.h) )
		{
			if(this.build) {
				Game.entities.add(p);
				Game.entities.add(underP);
			}
			x += dx * spd;
			y += dy * spd;
		}
		else
		{
			//double angleRadian = (dy > 0) ? Math.acos(dx) : -Math.acos(dx);
			//double angleDegrees = angleRadian * 180 / Math.PI;
			
			//this.dx = Math.cos(Math.toDegrees(angleRadian));
			//this.dy = Math.sin(Math.toDegrees(angleRadian));
			
			Game.bullets.remove(this);
			Game.bulletsEn.remove(this);
			
		}
		
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
		if(this.spr == null) {
			gfx.setColor(this.color);
			gfx.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, w, h);
			return;
		}
		
		gfx.drawImage(spr, this.getX() - Camera.x, this.getY() - Camera.y, w, h, null);
	}
}
