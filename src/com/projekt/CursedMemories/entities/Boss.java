package com.projekt.CursedMemories.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Boss extends Entity
{
	protected double speed;
	protected int x, y;
	protected int life, damageFrame = 0, damageMaxFrame = 8;
	protected boolean dameged = false;
	protected int maxLife;
	
	public Boss(int x, int y, int w, int h, double speed,int vida,BufferedImage sprite)
	{
		super(x, y, w, h, sprite);
		this.x = x;
		this.y = y;
		this.life = vida;
		this.maxLife = vida;
		this.speed = speed;
	}
	
	public int getX() {
		return x - 32;
	}

	public int getY() {
		return y - 32;
	}


	public boolean isCollidingPlayer()
	{
		Rectangle current_enemy = new Rectangle(this.getX(), this.getY(), 128, 128);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), World.TILE_SIZE, World.TILE_SIZE);
		
		return current_enemy.intersects(player);
	}
	
	
	public void tick()
	{
		if(!this.isCollidingPlayer())
		{
			
			if(Game.rand.nextInt(100) < 80)
			{
				if(x < Game.player.getX() && 
						World.isFreeDynamic(this.getX() + (int)speed, this.getY(), this.getW(), this.getH()))
				{
					x += speed;
				}
				else if(x > Game.player.getX() &&
						World.isFreeDynamic(this.getX() - (int)speed, this.getY(), this.getW(), this.getH()))
				{
					x -= speed;
				}
				
				if(y < Game.player.getY() && 
						World.isFreeDynamic(this.getX(), this.getY() + (int)speed, this.getW(), this.getH()))
				{
					y += speed;
				}
				else if(y > Game.player.getY() && 
						World.isFreeDynamic(this.getX(), this.getY() - (int)speed, this.getW(), this.getH()))
				{
					y -= speed;
				}
			}
			
		}
		else
		{
			if(Game.rand.nextInt(100) < 5)
			{
				if(!Game.player.invulnerable) {
					Game.player.isDameged = true;
					Game.player.setLife(Game.player.getLife() - 1);
					Game.player.invulnerable = true;
				}
			}
		}
		
		this.checkCollisionBullet();
		
		if(this.life <= 0)
		{
			destroyBoss();
		}
		if(dameged)
		{
			damageFrame++;
			if(damageFrame >= damageMaxFrame)
			{
				dameged = false;
				damageFrame = 0;
			}
		}
	}
	
	protected void destroyBoss()
	{
		Game.entities.remove(this);
		Game.bosses.remove(this);
		int bosses = Game.bosses.size();
		if(bosses-- <= 0)
		{
			Game.CHANGE_LEVEL = true;
		}
		else
		{
			Game.CHANGE_LEVEL = false;
		}
	}
	
	public void checkCollisionBullet()
	{
		for(int i = 0; i < Game.bullets.size(); i++)
		{
			Entity e = Game.bullets.get(i);
			if(Entity.isCollidingBoss(e, this))
			{
				life--;
				dameged = true;
				Game.bullets.remove(i);
				return;
			}
		}
	}
	
	public int getMaxLife() {
		return this.maxLife;
	}
	
	public int getLife() {
		return this.life;
	}

	public void render(Graphics gfx)
	{

	}
}
