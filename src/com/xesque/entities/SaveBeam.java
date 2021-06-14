package com.xesque.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.main.Menu;
import com.xesque.world.Camera;
import com.xesque.world.World;

public class SaveBeam extends Entity 
{
	public static boolean isSaved = false;
	
	public SaveBeam(int x, int y, int w, int h, BufferedImage sprite) 
	{
		super(x, y, w, h, sprite);
		this.x = x;
		this.y = y;
	}
	
	public void tick()
	{
		if(isCollidingPlayer())
		{
			if(isSaved)
			{
				isSaved = false;
    			if(Game.bosses.size() <= 0 && Game.enemies.size() <= 0)
    			{
    				String[] opt1 = {"mapa",
									"PlayerX",
									"PlayerY",
									"vida",
									"municao"
								};
					int[] opt2 = {Game.CUR_LEVEL, 
									World.px,
									World.py,
									Game.player.getLife(),
									(Game.player.getAmmo() == 0) ? 0 : Game.player.getAmmo()
								};
					Menu.saveGame(opt1, opt2, 1);
					System.out.println("Saved");
    			}
    			else
    			{
    				System.out.println("O mapa não cumpre os requesitos para salvar o jogo!\n(Existe um Boss ou Inimigos vivos)");
    			}
			}
		}
	}
	
	public void render(Graphics gfx)
	{
		gfx.drawImage(Entity.SAVE_BEAM, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
	public void renderSaveMSG(Graphics gfx)
	{
		gfx.setColor(new Color(0, 0, 0,100));
		gfx.fillRect(this.getX() - Camera.x - 98, this.getY() - Camera.y + 70, 218, 30);
		gfx.setFont(new Font("arial", Font.ITALIC, 11));
		gfx.setColor(new Color(255, 255, 255,150));
		gfx.drawString("Aperte \"ENTER\" para salvar seu progresso", this.getX() - Camera.x - 95, this.getY() - Camera.y + 90);
	}
	
	public boolean isCollidingPlayer()
	{
		Rectangle current_enemy = new Rectangle(this.getX(), this.getY(), 32, 64);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), World.TILE_SIZE, World.TILE_SIZE);
		
		return current_enemy.intersects(player);
	}
	
}
