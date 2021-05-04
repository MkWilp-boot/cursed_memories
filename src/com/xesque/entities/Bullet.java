package com.xesque.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.world.Camera;
import com.xesque.world.WallTile;
import com.xesque.world.World;

public class Bullet extends Entity
{
	private double dx, dy;
	private double spd;
	private int lifeTime = 100, cur_life = 0;
	private Color color;
	
	
	public Bullet(int x, int y, int w, int h, BufferedImage sprite, double dx, double dy, Color color, double speed) 
	{
		super(x, y, w, h, sprite);
		this.dx = dx;
		this.dy = dy;
		this.color = color;
		this.spd = speed;
	}
	
	public static boolean isColliding(Entity e1, WallTile e2)
    {
    	Rectangle rectE1 = new Rectangle(e1.getX(), e1.getY(), 16, 16);
    	Rectangle rectE2 = new Rectangle(e2.getX(), e2.getY(), World.TILE_SIZE, World.TILE_SIZE);
    	return rectE1.intersects(rectE2);
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
		gfx.setColor(this.color);
		gfx.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, w, h);
	}
}
