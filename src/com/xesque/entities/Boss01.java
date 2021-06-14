package com.xesque.entities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.xesque.main.Game;
import com.xesque.world.World;

public class Boss01 extends Boss 
{

	private int maxTime = 150, curTime = 0;
	//private int maxSizeTime = 50, curTiSizeTime = 0;
	private Random rand = new Random();
	
	public Boss01(int x, int y, int w, int h, double speed, int vida, BufferedImage sprite) 
	{
		super(x, y, w, h, speed, vida, sprite);
		
	}
	
	private void attack_8()
	{
		Bullet bullet;
		for(int o = 0; o < 20; o++)
		{
			for(int i = 0; i < 8; i++)
			{
				if(i == 0)
				{
					bullet = new Bullet(this.getX() + 32 + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), rand.nextDouble(), Color.RED, 4.0);
				}
				else if(i == 1)
				{
					bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), rand.nextDouble(), Color.RED, 4.0);
				}
				else if(i == 2)
				{
					bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), -rand.nextDouble(), Color.RED, 4.0);
				}
				else if(i == 3)
				{
					bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -rand.nextDouble(), rand.nextDouble(), Color.RED, 4.0);
				}
				else if(i == 4)
				{
					bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -rand.nextDouble(), rand.nextDouble(), Color.RED, 4.0);
				}
				else if(i == 5)
				{
					bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -rand.nextDouble(), -rand.nextDouble(), Color.RED, 4.0);
				}
				else if(i == 6)
				{
					bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), -rand.nextDouble(), Color.RED, 4.0);
				}
				else
				{
					bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, rand.nextDouble(), rand.nextDouble(), Color.RED, 4.0);
				}
				Game.bulletsEn.add(bullet);
			}
		}
	}
	
	public void attack_circ()
	{
		Bullet bullet = null;
		for(int i = 0; i < 30; i++)
		{
			switch(i)
			{
			case 0:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, 1.5, Color.RED, 2.0);
			break;
			case 1:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, 0.9, Color.RED, 2.0);
			break;
			case 2:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0, 1, Color.RED, 3.0);
			break;
			case 3:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, 0.5, Color.RED, 2.0);
			break;
			case 4:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0.9, 0.9, Color.RED, 2.0);
			break;
			default:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0, 0, Color.RED, 2.0);
			
			}
			Game.bulletsEn.add(bullet);
		}
	}
	
	public void tick()
	{
		
		curTime++;
		
		if(curTime >= maxTime)
		{
			curTime = 0;
			//attack_circ();
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
				Game.player.setLife(Game.player.getLife() - 10);
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
