package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Boss3 extends Boss 
{
	private int maxTime = 100, curTime = 0, animation_max_fr = 120, cur_animation_fr = 0;
	private int frames, index = 0, maxFrames = 15, maxIndex = 7;
	private int framesAtt, indexAtt = 0, maxFramesAtt = 10, maxIndexAtt = 7;
	private double speed;
	private boolean attack_animation = false, movement = false, moveRight = false, moveLeft = false;
	private BufferedImage[] sprites;
	private BufferedImage[] attackSprites;
	
	public Boss3(int x, int y, int w, int h, double speed, int vida, BufferedImage sprite) 
	{
		super(x, y, w, h, speed, vida, sprite);
		this.speed = speed;
		this.sprites = new BufferedImage[8];
		sprites[0] = Game.spr_b_clock.getSprite(0,   128, 128, 128); 
		sprites[1] = Game.spr_b_clock.getSprite(128, 128, 128, 128);
		sprites[2] = Game.spr_b_clock.getSprite(256, 128, 128, 128);
		sprites[3] = Game.spr_b_clock.getSprite(384, 128, 128, 128);
		sprites[4] = Game.spr_b_clock.getSprite(512, 128, 128, 128);
		sprites[5] = Game.spr_b_clock.getSprite(640, 128, 128, 128);
		sprites[6] = Game.spr_b_clock.getSprite(768, 128, 128, 128);
		sprites[7] = Game.spr_b_clock.getSprite(896, 128, 128, 128);
		
		attackSprites = new BufferedImage[8];
		attackSprites[0] = Game.spr_b_clock.getSprite(0,   0, 128, 128); 
		attackSprites[1] = Game.spr_b_clock.getSprite(128, 0, 128, 128);
		attackSprites[2] = Game.spr_b_clock.getSprite(256, 0, 128, 128);
		attackSprites[3] = Game.spr_b_clock.getSprite(384, 0, 128, 128);
		attackSprites[4] = Game.spr_b_clock.getSprite(512, 0, 128, 128);
		attackSprites[5] = Game.spr_b_clock.getSprite(640, 0, 128, 128);
		attackSprites[6] = Game.spr_b_clock.getSprite(768, 0, 128, 128);
		attackSprites[7] = Game.spr_b_clock.getSprite(896, 0, 128, 128);
	}
	
	
	public void attack_spear_up() {
		Spear bullet = null;
		for(int i = 0; i < 2; i++) {
			switch(i) {
			// inicio padrao inteiro
			case 0:
				bullet = new Spear(this.getX(), this.getY(), 0, 0, null, 0, -1, null, 4.0, null, false);
				break;
			case 1:
				bullet = new Spear(this.getX() + 32, this.getY(), 0, 0, null, 0, -1, null, 4.0, null, false);
				break;
			// Fim padrao inteiro
			}
			Game.bulletsEn.add(bullet);
		}
	}
	
	public void attack_spear_left_right_player() {
		Spear bullet = null;
		for(int i = 0; i < 2; i++) {
			switch(i) {
			// inicio padrao inteiro
			case 0:
				bullet = new Spear(Game.player.getX() + 150, Game.player.getY(), 0, 0, null, -1, 0, null, 4.0, null, false);
				break;
			case 1:
				bullet = new Spear(Game.player.getX() - 150, Game.player.getY(), 0, 0, null, 1, 0, null, 4.0, null, false);
				break;
			// Fim padrao inteiro
			}
			Game.bulletsEn.add(bullet);
		}
	}
	
	public void attack_spear_up_down_player() {
		Spear bullet = null;
		for(int i = 0; i < 2; i++) {
			switch(i) {
			// inicio padrao inteiro
			case 0:
				bullet = new Spear(Game.player.getX(), Game.player.getY() + 150, 0, 0, null, 0, -1, null, 4.0, null, false);
				break;
			case 1:
				bullet = new Spear(Game.player.getX(), Game.player.getY() - 150, 0, 0, null, 0, 1, null, 4.0, null, false);
				break;
			// Fim padrao inteiro
			}
			Game.bulletsEn.add(bullet);
		}
	}
	
	public void attack_circ()
	{
		Meteor bullet = null;
		for(int i = 0; i < 16; i++)
		{
			switch(i)
			{
			// inicio padrao inteiro
			case 0:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_DIR, 1, 1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 1:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_ESQ, -1, -1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 2:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_ESQ, -1, 1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 3:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_DIR, 1, -1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 4:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_BAIXO, 0, 1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 5:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_RIGHT, 1.1, 0, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 6:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_LEFT, -1, 0, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 7:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_CIMA, 0, -1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			// Fim padrao inteiro
			// inicio padrao real
			case 8:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_DIR, 0.5, 1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 9:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_DIR, 1, 0.5, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 10:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_ESQ, -1, 0.5, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 11:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_DIR, 0.5, -1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 12:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_DIR, 1, -0.5, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 13:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_ESQ, -0.5, 1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 14:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_ESQ, -0.5, -1, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			case 15:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_ESQ, -1, -0.5, Color.RED, 3.0, new Color(137,137,137, 100), false, "big");
				break;
			}
			// fim padrao real
			Game.bulletsEn.add(bullet);
		}
	}
	
	private void rainning() {
		int coords = 0;
		for(int i = 0; i < 3; i++) {
			if(i == 0) {
				coords = Game.player.getX() + 6;
			}
			else if(i == 1) {
				coords = Game.player.getX() + 38;
			}
			else {
				coords = Game.player.getX() - 28;
			}
			Meteor bullet = new Meteor(
					coords,
					Game.player.getY() - (Game.HEIGHT / 2), 
					16, 16, 
					Entity.FIRE_BALL_BAIXO,
					0, 1,
					new Color(244,36,36, 255), 
					3.0, 
					new Color(137,137,137, 100),
					false,
					"small",
					"vertical"
				);
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
		
		Game.boss_clock_kill = true;
		
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
				int chanc = Game.rand.nextInt(100);
				if(chanc < 25) {
					attack_circ();
				}
				else if(chanc >= 25 && chanc < 50) {
					attack_spear_left_right_player();
				}
				else if(chanc >= 50 && chanc <= 75) {
					attack_spear_up_down_player();
				}
				else {
					attack_circ();
				}
				rainning();
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
