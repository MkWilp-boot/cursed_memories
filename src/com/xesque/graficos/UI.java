package com.xesque.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;

public class UI
{
	private BufferedImage heart = Game.spritesheet.getSprite(256, 96, 32, 32);
	private BufferedImage ammo = Game.spritesheet.getSprite(288, 96, 32, 32);
	
	public void render(Graphics gfx)
	{
		gfx.setColor(new Color(103, 64, 5));
		
		
		gfx.fillRect(2, 0, 166 / Game.SCALE + 30, 76 / Game.SCALE + 25);
		gfx.setColor(new Color(174, 130, 64));
		gfx.fillRect(7, 6, 166 / Game.SCALE + 21, 76 / Game.SCALE + 14);
		gfx.setColor(Color.black);
		gfx.drawRect(2, 0, 166 / Game.SCALE + 30, 76 / Game.SCALE + 25);
		
		
		gfx.drawRect(59 / Game.SCALE, 25  / Game.SCALE,  Game.player.maxLife / Game.SCALE + 1, 13 );
		if(Game.player.getLife() <= 100 && Game.player.getLife() >= 60)
		{
			gfx.setColor(Color.green);
			gfx.fillRect(60 / Game.SCALE, 26 / Game.SCALE,  Game.player.maxLife / Game.SCALE, 12);
		}
		else if(Game.player.getLife() < 60 && Game.player.getLife() >= 30)
		{
			gfx.setColor(Color.YELLOW);
			gfx.fillRect(60 / Game.SCALE, 26 / Game.SCALE,  Game.player.maxLife / Game.SCALE, 12);
		}
		else if(Game.player.getLife() < 30)
		{
			gfx.setColor(Color.RED);
			gfx.fillRect(60 / Game.SCALE, 26 / Game.SCALE,  Game.player.maxLife / Game.SCALE, 12);
		}
		gfx.setColor(Color.black);
		gfx.drawImage(heart, 0, 3, null);
		gfx.drawImage(ammo, 0, 20, null);
		gfx.drawRect(59 / Game.SCALE, 58 / Game.SCALE,  Game.player.maxLife / Game.SCALE + 23, 13);
		gfx.setColor(Color.yellow);
		gfx.fillRect(60 / Game.SCALE, 60 / Game.SCALE,  100 / Game.SCALE + 22, 12);
		gfx.setColor(Color.BLACK);
		gfx.setFont(new Font("arial", Font.ITALIC, 10));
		gfx.drawString(Game.player.getAmmo() + "/" + Game.player.maxAmmo + "/" + Game.player.getReserveAmmo(), 70 / Game.SCALE, 79 / Game.SCALE);
		
		if(Game.player.getLife() < 30)
		{
			gfx.setColor(Color.WHITE);
		}
		
		gfx.drawString(Game.player.getLife() + "/" + Game.player.maxLife, 70 / Game.SCALE, 44 / Game.SCALE);
	}
}
