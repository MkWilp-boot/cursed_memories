package com.xesque.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.main.Sound;
import com.xesque.world.Camera;
import com.xesque.world.World;

public class Enemy extends Entity 
{
	
	private double speed = 0.0;
	private int frames, index = 0, maxFrames = 20, maxIndex = 1, maxTime = 200, curTime = 0, life = 1;
	private boolean moved = true;
	
	private BufferedImage[] spritesEn = new BufferedImage[2];
	
	public Enemy(int x, int y, int w, int h, BufferedImage sprite) 
	{
		
		super(x, y, w, h, null);
		spritesEn[0] = Game.spritesheet.getSprite(128, 32, World.TILE_SIZE, World.TILE_SIZE);
		spritesEn[1] = Game.spritesheet.getSprite(96, 32, World.TILE_SIZE, World.TILE_SIZE);
	}
	
	public boolean isCollidingPlayer()
	{
		Rectangle current_enemy = new Rectangle(this.getX(), this.getY(), World.TILE_SIZE, World.TILE_SIZE);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), World.TILE_SIZE, World.TILE_SIZE);
		
		return current_enemy.intersects(player);
	}
	
	public boolean isColliding(int xNext, int yNext)
	{
		Rectangle current_enemy = new Rectangle(xNext, yNext, World.TILE_SIZE, World.TILE_SIZE);
		
		for(int i = 0; i < Game.enemies.size(); i++)
		{
			if(Game.enemies.get(i) == this)
				continue;
			Rectangle target_enemy = new Rectangle(Game.enemies.get(i).getX(),
												   Game.enemies.get(i).getY(),
												   World.TILE_SIZE,
												   World.TILE_SIZE);
			if(current_enemy.intersects(target_enemy))
				return true;
		}
		return false;
	}
	
	public void tick()
	{
		curTime++;
    	if(curTime >= maxTime)
    	{
    		for(int i = 0; i < 4; i++)
    		{
    			Bullet bullet;
    			if(i == 0)
    			{
    				bullet = new Bullet(this.getX() + 8, this.getY() + 16, 8, 8, null, 1, 0, Color.LIGHT_GRAY);
    			}
    			else if(i == 1)
    			{
    				bullet = new Bullet(this.getX() + 8, this.getY() + 16, 8, 8, null, 0, 1, Color.LIGHT_GRAY);
    			}
    			else if(i == 2)
    			{
    				bullet = new Bullet(this.getX() + 8, this.getY() + 16, 8, 8, null, -1, 0, Color.LIGHT_GRAY);
    			}
    			else
    			{
    				bullet = new Bullet(this.getX() + 8, this.getY() + 16, 8, 8, null, 0, -1, Color.LIGHT_GRAY);
    			}
    	    	Game.bulletsEn.add(bullet);
    		}
    		curTime = 0;
    	}
		
    	
    	
		if(!this.isCollidingPlayer())
		{
			if(x < Game.player.getX() && 
					World.isFree(x + (int)speed, this.getY()) && 
					!isColliding(x + (int)speed, this.getY()))
			{
				x += speed;
			}
			else if(x > Game.player.getX() &&
					World.isFree(x - (int)speed, this.getY()) &&
					!isColliding(x - (int)speed, this.getY()))
			{
				x -= speed;
			}
			
			if(y < Game.player.getY() && 
					World.isFree(x, this.getY() + (int)speed) &&
					!isColliding(x, this.getY() + (int)speed))
			{
				y += speed;
			}
			else if(y > Game.player.getY() && 
					World.isFree(x, this.getY() - (int)speed) &&
					!isColliding(x, this.getY() -(int) speed))
			{
				y -= speed;
			}
		}
		else
		{
			if(Game.rand.nextInt(100) < 5)
			{
				Game.player.isDameged = true;
				Game.player.life--;
				Sound.playerHurt.play();
			}
		}
		

		if (moved) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if(index > maxIndex) {
                	index = 0;
                }
            }
        }
		this.checkCollisionBullet();
		if(life <= 0)
		{
			destroy();
			return;
		}
	}
	
	public void destroy()
	{
		Game.entities.remove(this);
		Game.enemies.remove(this);
	}
	
	public void checkCollisionBullet()
	{
		for(int i = 0; i < Game.bullets.size(); i++)
		{
			Entity e = Game.bullets.get(i);
			if(Entity.isColliding(e, this))
			{
				life--;
				Game.bullets.remove(i);
				return;
			}
		}
	}
	
	public void render(Graphics gfx)
	{
		gfx.drawImage(spritesEn[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
