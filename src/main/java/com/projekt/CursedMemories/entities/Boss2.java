package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Boss2 extends Boss 
{
	private int maxTime = 100, curTime = 0, animation_max_fr = 120, cur_animation_fr = 0;
	private int frames, index = 0, maxFrames = 15, maxIndex = 5;
	private int framesAtt, indexAtt = 0, maxFramesAtt = 10, maxIndexAtt = 2;
	private double speed;
	private boolean attack_animation = false, movement = false, moveRight = false, moveLeft = false;
	private BufferedImage[] sprites;
	private BufferedImage[] attackSprites;
	
	public Boss2(int x, int y, int w, int h, double speed, int vida, BufferedImage sprite) 
	{
		super(x, y, w, h, speed, vida, sprite);
		this.speed = speed;
		this.sprites = new BufferedImage[6];
		sprites[0] = Game.spr_b_caveira.getSprite(0, 0, 64, 96); 
		sprites[1] = Game.spr_b_caveira.getSprite(64, 0, 64, 96);
		sprites[2] = Game.spr_b_caveira.getSprite(128, 0, 64, 96);
		sprites[3] = Game.spr_b_caveira.getSprite(192, 0, 64, 96);
		sprites[4] = Game.spr_b_caveira.getSprite(256, 0, 64, 96);
		sprites[5] = Game.spr_b_caveira.getSprite(320, 0, 64, 96);
		
		attackSprites = new BufferedImage[3];
		attackSprites[0] = Game.spr_b_caveira.getSprite(0, 192, 64, 96);
		attackSprites[1] = Game.spr_b_caveira.getSprite(107, 192, 106, 93);
		attackSprites[2] = Game.spr_b_caveira.getSprite(260, 192, 58, 96);
	}
	
	
	public void attack_spear_up()
	{
		// -1 0 down
		// 0 -1 up
		Spear bullet = null;
		for(int i = 0; i < 2; i++)
		{
			switch(i)
			{
			// inicio padrao inteiro
			case 0:
				bullet = new Spear(this.getX(), this.getY(), 0, 0, null, 0, -1, Color.RED, 4.0, null, false);
				break;
			case 1:
				bullet = new Spear(this.getX() + 32, this.getY(), 0, 0, null, 0, -1, Color.RED, 4.0, null, false);
				break;
			// Fim padrao inteiro
			}
			Game.bulletsEn.add(bullet);
		}
	}
	
	protected void animateDeath() {
		this.destrocable = false;
		this.dameged = true;
		this.speed = 0.0;
		World.generateParticles(100, this.getX(), this.getY());
		World.generateParticles(100, this.getX() + 32, this.getY() + 32);
		World.generateParticles(100, this.getX() + 64, this.getY() + 64);
		
		Game.boss_fire_kill = true;
		
		Game.entities.remove(this);
		Game.bosses.remove(this);
		
		int bosses = Game.bosses.size();
		if(bosses <= 0)
			Game.CHANGE_LEVEL = true;
	}
	
	public void render(Graphics gfx) {
		if(!dameged) {
			if(attack_animation) {
				gfx.drawImage(attackSprites[indexAtt], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			else {
				gfx.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		else {
			gfx.drawImage(Entity.BOSS_CAV_DAM, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}
	
	public void createEnemy() {
		Enemy en = new Enemy(this.getX(), this.getY(), 32, 32, Entity.ENEMY_ENT);
		Game.entities.add(en);
		Game.enemies.add(en);
	}
	
	public void tick()
	{
		this.checkCollisionBullet();
		
			curTime++;
			if(curTime >= maxTime) {
				attack_animation = true;
				curTime = 0;
				attack_spear_up();
			}
			
			if(attack_animation) {
				framesAtt++;
				if (framesAtt >= maxFramesAtt) {
	                framesAtt = 0;
	                indexAtt++;
	                if(indexAtt > maxIndexAtt) {
	                	indexAtt = 0;
	                	attack_animation = false;
	                }
	            }
			}
			
			if(!this.isCollidingPlayer()) {
				movement = false;
				moveLeft = false;
				moveRight = false;
				
				if(x < Game.player.getX() && 
						World.isFreeDynamic(this.getX() + (int)speed, this.getY(), this.getW(), this.getH()))
				{
					x += speed;
					movement = true;
					moveLeft = false;
					moveRight = true;
				}
				else if(x > Game.player.getX() &&
						World.isFreeDynamic(this.getX() - (int)speed, this.getY(), this.getW(), this.getH()))
				{
					x -= speed;
					movement = true;
					moveRight = false;
					moveLeft = true;
				}
				
				if(y < Game.player.getY() && 
						World.isFreeDynamic(this.getX(), this.getY() + (int)speed, this.getW(), this.getH()))
				{
					y += speed;
					movement = true;
					
					if(x < Game.player.getX())
						moveRight = true;
					else 
						moveLeft = false;
				}
				else if(y > Game.player.getY() && 
						World.isFreeDynamic(this.getX(), this.getY() - (int)speed, this.getW(), this.getH()))
				{
					y -= speed;
					movement = true;
					
					if(x < Game.player.getX())
						moveRight = true;
					else 
						moveLeft = false;
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
			if(this.life <= 0)
			{
				if(destrocable)
					destroyBoss();
			}
			
			if(dameged)
			{
				attack_animation = false;
				if(destrocable)
				{
					damageFrame++;
					if(damageFrame >= damageMaxFrame)
					{
						dameged = false;
						damageFrame = 0;
					}
				}
			}
			
			if (movement) {
	            frames++;
	            if (frames >= maxFrames) {
	                frames = 0;
	                index++;
	                if(index > maxIndex) {
	                	index = 0;
	                }
	            }
	        }
	}
}
