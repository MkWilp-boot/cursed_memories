package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.projekt.CursedMemories.graficos.DynamicShadow;
import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.main.Sound;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Boss01 extends Boss 
{
	private Random rand;
	private int maxTime = 150, curTime = 0;
	private double speed = 0.0;
	//private BufferedImage shadow;
	
	public Boss01(int x, int y, int w, int h, double speed, int vida, BufferedImage sprite) 
	{
		super(x, y, w, h, speed, vida, sprite);
		rand = new Random();
		this.speed = speed;
		//shadow = DynamicShadow.internalProcessor("Boss01", Entity.METEOR_SPR);
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
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_DIR, 1, 1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 1:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_ESQ, -1, -1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 2:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_ESQ, -1, 1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 3:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_DIR, 1, -1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 4:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_BAIXO, 0, 1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 5:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_RIGHT, 1.1, 0, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 6:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_LEFT, -1, 0, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 7:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_CIMA, 0, -1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			// Fim padrao inteiro
			// inicio padrao real
			case 8:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_DIR, 0.5, 1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 9:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_DIR, 1, 0.5, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 10:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_ESQ, -1, 0.5, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 11:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_DIR, 0.5, -1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 12:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_DIR, 1, -0.5, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 13:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_INFERIOR_ESQ, -0.5, 1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 14:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_ESQ, -0.5, -1, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
				break;
			case 15:
				bullet = new Meteor(this.getX() + 32  + 16, this.getY() + 32  + 24, 28, 20, Entity.FIRE_BALL_SUPERIOR_ESQ, -1, -0.5, Color.RED, 2.0, new Color(137,137,137, 100), false, "big");
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
					2.0, 
					new Color(137,137,137, 100),
					false,
					"small",
					"vertical"
				);
			Game.bulletsEn.add(bullet);
		}
	}
	
	public void render(Graphics gfx)
	{
		//gfx.drawImage(shadow, 100, 100, null);
		
		if(!dameged)
		{
			gfx.drawImage(Entity.BOSS_01, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		else
		{
			gfx.drawImage(Entity.BOSS_01_DAM, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}
	
	public void createEnemy() {
		Enemy en = new Enemy(Game.player.getX() - 96, Game.player.getY(), 32, 32, Entity.ENEMY_ENT);
		Game.entities.add(en);
		Game.enemies.add(en);
	}
	
	public void tick()
	{
		curTime++;
		if(curTime >= maxTime)
		{
			curTime = 0;
			int chance = this.rand.nextInt(100);
			if(this.rand.nextInt(100) > 50) {
				Sound.mp_bs_1.play();
			}
			else {
				Sound.mp_bs_2.play();
			}
			if(chance < 50) {
				//attack_circ();
			}
			else if(chance >= 50) {
				//createEnemy();
			}
			//rainning();
			
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
