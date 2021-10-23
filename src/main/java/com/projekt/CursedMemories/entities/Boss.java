package com.projekt.CursedMemories.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.World;

public class Boss extends Entity
{
	protected Random rand = new Random();
	protected double speed;
	protected int x, y;
	protected int life, damageFrame = 0, damageMaxFrame = 8;
	protected boolean dameged = false;
	protected int maxLife;
	protected boolean destrocable = true;
	protected Integer removePlayerLife;
	
	public Boss(int x, int y, int w, int h, double speed,int vida,BufferedImage sprite)
	{
		super(x, y, w, h, sprite);
		this.x = x;
		this.y = y;
		this.life = vida;
		this.maxLife = vida;
		this.speed = speed;
		
		if (Game.difficult == "Dificil")
			this.removePlayerLife = 3;
		else if(Game.difficult == "Dificil")
			this.removePlayerLife = 2;
		else
			this.removePlayerLife = 1;
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
					Game.player.setLife(Game.player.getLife() - this.removePlayerLife);
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
	
	protected void animateDeath() {
		destrocable = false;
		dameged = true;
		speed = 0.0;
	}

	protected void destroyBoss()
	{
		Integer gold = (int)(rand.nextDouble() * (140 - 70) + 70);
		Game.player.setGoldAmount(Game.player.getGoldAmount() + gold);
		Game.bossDestroyed = true;
		Game.enemies.clear();
		this.animateDeath();
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
