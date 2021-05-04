package com.xesque.graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;

public class UI
{
	private BufferedImage heart = Game.spritesheet.getSprite(192, 32, 32, 32);
	
	public void render(Graphics gfx)
	{
		gfx.setColor(Color.LIGHT_GRAY);
		
		gfx.fillRect(2, 0, 126 / Game.SCALE + 30, 76 / Game.SCALE + 30);
		gfx.setColor(Color.black);
		gfx.drawRect(2, 0, 126 / Game.SCALE + 30, 76 / Game.SCALE + 30);
		
		// corações habilitados, comentar daqui (1)
		gfx.drawRect(13 / Game.SCALE, 25  / Game.SCALE,  Game.player.maxLife / Game.SCALE + 1, 13 );
		
		
		if(Game.player.life <= 100 && Game.player.life >= 60)
		{
			gfx.setColor(Color.green);
			gfx.fillRect(14 / Game.SCALE, 26 / Game.SCALE,  Game.player.maxLife / Game.SCALE, 12);
		}
		else if(Game.player.life < 60 && Game.player.life >= 30)
		{
			gfx.setColor(Color.YELLOW);
			gfx.fillRect(14 / Game.SCALE, 26 / Game.SCALE,  Game.player.maxLife / Game.SCALE, 12);
		}
		else if(Game.player.life < 30)
		{
			gfx.setColor(Color.RED);
			gfx.fillRect(14 / Game.SCALE, 26 / Game.SCALE,  Game.player.maxLife / Game.SCALE, 12);
		}
		// até aqui (1)
		
		// Corações (3)
		/*
		switch(Game.player.life)
		{
		case 3: // 192, 32
			gfx.drawImage(heart, 10, 10, null);
			gfx.drawImage(heart, 48, 10, null);
			gfx.drawImage(heart, 86, 10, null);
		break;
		case 2:
			gfx.drawImage(heart, 10, 10, null);
			gfx.drawImage(heart, 48, 10, null);
		break;
		case 1:
			gfx.drawImage(heart, 10, 10, null);
		break;
		default:
			gfx.drawImage(heart, 10, 10, null);
			gfx.drawImage(heart, 48, 10, null);
			gfx.drawImage(heart, 86, 10, null);
		}
		*/
		gfx.setColor(Color.BLACK);
		gfx.drawRect(13 / Game.SCALE, 55 / Game.SCALE,  Game.player.maxLife / Game.SCALE + 11, 13);
		gfx.setColor(Color.YELLOW);
		gfx.fillRect(14 / Game.SCALE, 56 / Game.SCALE,  Game.player.maxLife / Game.SCALE + 10, 12);
		gfx.setColor(Color.BLACK);
		gfx.drawString(Game.player.ammo + " balas", 15 / Game.SCALE, 76 / Game.SCALE);
		// Comentar linha para habilitar corações
		gfx.drawString(Game.player.life + " HP", 15 / Game.SCALE, 46 / Game.SCALE);
		
		// Teste list de armas
		/*
		gfx.setColor(Color.LIGHT_GRAY);
		gfx.fillRect(2, 80, 200, 76);
		gfx.setColor(Color.black);
		gfx.drawRect(2, 80, 200, 76);
		
		for(int i = 0; i < Game.weapon.size(); i++)
		{
			Weapon w = Game.weapon.get(i);
			gfx.drawImage(w.getSprite(), 2, 80, null);
		}
		*/
	}
}
