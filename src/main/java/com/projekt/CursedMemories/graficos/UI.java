package com.projekt.CursedMemories.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.projekt.CursedMemories.entities.Boss;
import com.projekt.CursedMemories.entities.Entity;
import com.projekt.CursedMemories.entities.Weapon;
import com.projekt.CursedMemories.main.Game;

public class UI
{
	private Spritesheet HUD;
	private BufferedImage H_FULL_HEALTH;
	private BufferedImage H_9_HEALTH;
	private BufferedImage H_8_HEALTH;
	private BufferedImage H_7_HEALTH;
	private BufferedImage H_6_HEALTH;
	private BufferedImage H_5_HEALTH;
	private BufferedImage H_4_HEALTH;
	private BufferedImage H_3_HEALTH;
	private BufferedImage H_2_HEALTH;
	private BufferedImage H_1_HEALTH;
	private BufferedImage H_0_HEALTH;
	private BufferedImage W_HUD_VIEW;
	
	public UI(String path) {
		this.HUD = new Spritesheet(path);
		this.H_FULL_HEALTH = HUD.getSprite(24, 5, 178, 18);
		this.H_9_HEALTH = HUD.getSprite(24, 37, 178, 18);
		this.H_8_HEALTH = HUD.getSprite(24, 69, 178, 18);
		this.H_7_HEALTH = HUD.getSprite(24, 101, 178, 18);
		this.H_6_HEALTH = HUD.getSprite(24, 133, 178, 18);
		this.H_5_HEALTH = HUD.getSprite(24, 165, 178, 18);
		this.H_4_HEALTH = HUD.getSprite(24, 197, 178, 18);
		this.H_3_HEALTH = HUD.getSprite(24, 229, 178, 18);
		this.H_2_HEALTH = HUD.getSprite(24, 261, 178, 18);
		this.H_1_HEALTH = HUD.getSprite(24, 293, 178, 18);
		this.H_0_HEALTH = HUD.getSprite(24, 325, 178, 18);
		
		try {
			this.W_HUD_VIEW = ImageIO.read(new File(Game.ROOT_DIR+"\\res\\HUD_WEAPOM_VIEW.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);;
		}
	}
	
	public void render(Graphics gfx)
	{
		if(Game.player.getLife() == Game.player.getMaxLife()) {
			gfx.drawImage(H_FULL_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 9) {
			gfx.drawImage(H_9_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 8) {
			gfx.drawImage(H_8_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 7) {
			gfx.drawImage(H_7_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 6) {
			gfx.drawImage(H_6_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 5) {
			gfx.drawImage(H_5_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 4) {
			gfx.drawImage(H_4_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 3) {
			gfx.drawImage(H_3_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 2) {
			gfx.drawImage(H_2_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 1) {
			gfx.drawImage(H_1_HEALTH, 10, 10, null);
		}
		else if(Game.player.getLife() == 0) {
			gfx.drawImage(H_0_HEALTH, 10, 10, null);
		}
		
		gfx.setColor(new Color(255,255,255,170));
		
		gfx.setFont(Game.main_font.deriveFont(20f));
		gfx.drawString(""+Game.player.getAmmo() + " | " + Game.player.getReserveAmmo(), 20, 55);
		
		if(Game.bosses.size() > 0) {
			for(Boss b : Game.bosses) {
				
				gfx.drawRect(20, Game.HEIGHT - 45, b.getMaxLife() + 1, 20);
				gfx.fillRect(21, Game.HEIGHT - 44, b.getLife(), 19);
			}
		}
		if(Game.weapon.size() > 0) {
			int xPos = Game.WIDTH - 70;
			for(Weapon w : Game.weapon) {
				gfx.setColor(new Color(100, 100, 100, 100));
				gfx.fillRect(xPos, Game.HEIGHT - 50, 32, 32);
				gfx.drawImage(this.W_HUD_VIEW, xPos, Game.HEIGHT - 50, null);
				gfx.setColor(new Color(255, 255, 255, 255));
				
				if(Game.player.getGunLeft() == Entity.GUN_LEFT) {
					if(w.getSprite() == Entity.WEAPON_ENT_RIFLE_NON_AUTO) {
						gfx.drawRect(xPos - 1, Game.HEIGHT - 51, 33, 33);
					}
				}
				else if(Game.player.getGunLeft() == Entity.GUN_SHOTGUN_LEFT) { 
					if(w.getSprite() == Entity.WEAPON_ENT_SHOTGUN) {
						gfx.drawRect(xPos - 1, Game.HEIGHT - 51, 33, 33);
					}
				}
				
				gfx.drawImage(w.getSprite(), xPos, Game.HEIGHT - 50, null);
				xPos-=35;
			}
		}
	}
}
