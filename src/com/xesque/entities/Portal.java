package com.xesque.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.world.Camera;
import com.xesque.world.World;

public class Portal extends Entity
{
	public static int x;
	public static int y;
	
	public Portal(int x, int y, int w, int h, BufferedImage sprite) 
	{
		super(x, y, w, h, sprite);
		this.x = x;
		this.y = y;
	}
	
	public void tick()
	{
		if(isCollidingPlayer())
		{
			Game.nextLevel = true; 
		}
	}
	
	public void render(Graphics gfx)
	{
		gfx.drawImage(Entity.PORTAL_01, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
	public boolean isCollidingPlayer()
	{
		Rectangle current_enemy = new Rectangle(this.getX(), this.getY(), 64, 103);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), World.TILE_SIZE, World.TILE_SIZE);
		
		return current_enemy.intersects(player);
	}
}
