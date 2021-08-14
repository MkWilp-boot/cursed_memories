package com.projekt.CursedMemories.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Portal extends Entity
{
	public static int x;
	public static int y;
	
	public Portal(int x, int y, int w, int h, BufferedImage sprite) 
	{
		super(x, y, w, h, sprite);
		Portal.x = x;
		Portal.y = y;
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
		gfx.drawImage(this.getSprite(), this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
	public boolean isCollidingPlayer()
	{
		Rectangle current_enemy = new Rectangle(this.getX(), this.getY(), w, h);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), World.TILE_SIZE, World.TILE_SIZE);
		
		return current_enemy.intersects(player);
	}
}
