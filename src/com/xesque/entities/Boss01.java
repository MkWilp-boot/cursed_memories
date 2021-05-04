package com.xesque.entities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.xesque.main.Game;
import com.xesque.world.World;

public class Boss01 extends Boss 
{

	private int maxTime = 30, curTime = 0;
	private int maxSizeTime = 50, curTiSizeTime = 0;
	private Random rand = new Random();
	
	public Boss01(int x, int y, int w, int h, double speed, int vida, BufferedImage sprite) 
	{
		super(x, y, w, h, speed, vida, sprite);
		
	}
	
	private void attack_8()
	{
		Bullet bullet;
		for(int i = 0; i < 8; i++)
		{
			/*if(i == 0)
			{
				bullet = new Bullet(this.getX() + 32 + 16, this.getY() + 32  + 24, 24, 16, null, 1, 0, Color.GRAY, 10.0);
			}
			else if(i == 1)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, 1, Color.GRAY, 10.0);
			}
			else if(i == 2)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0, -1, Color.GRAY, 10.0);
			}
			else if(i == 3)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, 1, Color.GRAY, 10.0);
			}
			else if(i == 4)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, 0, Color.GRAY, 10.0);
			}
			else if(i == 5)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, -1, Color.GRAY, 10.0);
			}
			else if(i == 6)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, -1, Color.GRAY, 10.0);
			}
			else
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0, 1, Color.GRAY, 10.0);
			}
			 */
			if(i == 0)
			{
				bullet = new Bullet(this.getX() + 32 + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), rand.nextDouble(), Color.GRAY, 10.0);
			}
			else if(i == 1)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), rand.nextDouble(), Color.GRAY, 10.0);
			}
			else if(i == 2)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), -rand.nextDouble(), Color.GRAY, 10.0);
			}
			else if(i == 3)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -rand.nextDouble(), rand.nextDouble(), Color.GRAY, 10.0);
			}
			else if(i == 4)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -rand.nextDouble(), rand.nextDouble(), Color.GRAY, 10.0);
			}
			else if(i == 5)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -rand.nextDouble(), -rand.nextDouble(), Color.GRAY, 10.0);
			}
			else if(i == 6)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), -rand.nextDouble(), Color.GRAY, 10.0);
			}
			else
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), rand.nextDouble(), Color.GRAY, 10.0);
			}
			Game.bulletsEn.add(bullet);
		}
	}
	
	private void attack_4()
	{
		Bullet bullet;
		for(int i = 0; i < 4; i++)
		{
			/*
			if(i == 0)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, 0, Color.RED, 10.0);
			}
			else if(i == 1)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, 0, Color.RED, 10.0);
			}
			else if(i == 2)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0, -1, Color.RED, 10.0);
			}
			else
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0, 1, Color.RED, 10.0);
			}
			*/
			if(i == 0)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), rand.nextDouble(), Color.RED, 10.0);
			}
			else if(i == 1)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -rand.nextDouble(), rand.nextDouble(), Color.RED, 10.0);
			}
			else if(i == 2)
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), -rand.nextDouble(), Color.RED, 10.0);
			}
			else
			{
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), rand.nextDouble(), Color.RED, 10.0);
			}
	    	Game.bulletsEn.add(bullet);
		}
	}
	
	public void tick()
	{
		
		curTime++;
		
		if(curTime >= 1 && curTime <= 2)
		{
			/*
			if(rand.nextInt(100) > 50)
			{
				attack_4();
			}
			else
			{
				attack_8();
			}
			*/
		}
		
		if(curTime >= maxTime)
		{
			curTime = 0;
		}
		
		if(!this.isCollidingPlayer())
		{
			if(Game.rand.nextInt(100) < 80)
			{
				if(x < Game.player.getX() && 
						World.isFreeBos(this.getX() + 96 + (int)speed, this.getY()))
				{
					x += speed;
				}
				else if(x > Game.player.getX() &&
						World.isFreeBos(this.getX() - (int)speed, this.getY()))
				{
					x -= speed;
				}
				
				if(y < Game.player.getY() && 
						World.isFreeBos(this.getX(), this.getY() + 96 + (int)speed))
				{
					y += speed;
				}
				else if(y > Game.player.getY() && 
						World.isFreeBos(this.getX(), this.getY() - 96 - (int)speed))
				{
					y -= speed;
				}
			}
			
		}
		else
		{
			if(Game.rand.nextInt(100) < 5)
			{
				Game.player.isDameged = true;
				Game.player.life-=10;
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
}
