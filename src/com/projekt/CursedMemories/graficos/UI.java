package com.projekt.CursedMemories.graficos;

import java.awt.Color;
import java.awt.Graphics;

import com.projekt.CursedMemories.entities.Boss;
import com.projekt.CursedMemories.entities.Entity;
import com.projekt.CursedMemories.entities.Weapon;
import com.projekt.CursedMemories.main.Game;

public class UI
{
	public void render(Graphics gfx)
	{
		if(Game.player.getLife() == Game.player.getMaxLife()) {
			gfx.drawImage(Entity.HEART_FULL, 12, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 44, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 76, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 108, 12, null);
		}
		else if(Game.player.getLife() == 7) {
			gfx.drawImage(Entity.HEART_FULL, 12, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 44, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 76, 12, null);
			gfx.drawImage(Entity.HEART_HALF, 108, 12, null);
		}
		else if(Game.player.getLife() == 6) {
			gfx.drawImage(Entity.HEART_FULL, 12, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 44, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 76, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 108, 12, null);
		}
		else if(Game.player.getLife() == 5) {
			gfx.drawImage(Entity.HEART_FULL, 12, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 44, 12, null);
			gfx.drawImage(Entity.HEART_HALF, 76, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 108, 12, null);
		}
		else if(Game.player.getLife() == 4) {
			gfx.drawImage(Entity.HEART_FULL, 12, 12, null);
			gfx.drawImage(Entity.HEART_FULL, 44, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 76, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 108, 12, null);
		}
		else if(Game.player.getLife() == 3) {
			gfx.drawImage(Entity.HEART_FULL, 12, 12, null);
			gfx.drawImage(Entity.HEART_HALF, 44, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 76, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 108, 12, null);
		}
		else if(Game.player.getLife() == 2) {
			gfx.drawImage(Entity.HEART_FULL, 12, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 44, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 76, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 108, 12, null);
		}
		else if(Game.player.getLife() == 1) {
			gfx.drawImage(Entity.HEART_HALF, 12, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 44, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 76, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 108, 12, null);
		}
		else if(Game.player.getLife() == 0) {
			gfx.drawImage(Entity.HEART_EMPTY, 12, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 44, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 76, 12, null);
			gfx.drawImage(Entity.HEART_EMPTY, 108, 12, null);
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
