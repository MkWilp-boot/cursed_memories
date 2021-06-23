package com.xesque.entities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.xesque.main.Game;
import com.xesque.main.Sound;
import com.xesque.world.APointer;
import com.xesque.world.Camera;
import com.xesque.world.Vector2i;
import com.xesque.world.World;

public class Boss01 extends Boss 
{

	private int maxTime = 50, curTime = 0;
	//private int maxSizeTime = 50, curTiSizeTime = 0;
	private Random rand = new Random();
	
	public Boss01(int x, int y, int w, int h, double speed, int vida, BufferedImage sprite) 
	{
		super(x, y, w, h, speed, vida, sprite);
		
	}
	
	public void attack_circ()
	{
		Bullet bullet = null;
		for(int i = 0; i < 16; i++)
		{
			switch(i)
			{
			// inicio padrao inteiro
			case 0:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, 1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 1:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, -1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 2:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, 1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 3:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, -1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 4:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0, 1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 5:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, 0, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 6:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, 0, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 7:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0, -1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			// Fim padrao inteiro
			// inicio padrao real
			case 8:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0.5, 1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 9:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, 0.5, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 10:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, 0.5, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 11:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 0.5, -1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 12:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, 1, -0.5, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 13:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -0.5, 1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 14:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -0.5, -1, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			case 15:
				bullet = new Bullet(this.getX() + 32  + 16, this.getY() + 32  + 24, 24, 16, null, -1, -0.5, Color.RED, 2.0, new Color(137,137,137, 100), false);
				break;
			}
			// fim padrao real
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
}
