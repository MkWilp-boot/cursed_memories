package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.projekt.CursedMemories.main.Encryptor;
import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.main.Menu;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class SaveBeam extends Entity 
{
	public static boolean isSaved = false;
	public static boolean bShowSavedMessage = false;
	private int nFrames;
	private int nMaxFrames = 4000;
	
	public SaveBeam(int x, int y, int w, int h, BufferedImage sprite) 
	{
		super(x, y, w, h, sprite);
		this.x = x;
		this.y = y;
	}
	
	public void tick()
	{
		if(SaveBeam.bShowSavedMessage)
		{
			nFrames++;
			if(nFrames >= nMaxFrames)
			{
				SaveBeam.bShowSavedMessage = false;
				nFrames = 0;
			}
		}
		if(isCollidingPlayer())
		{
			if(isSaved)
			{
				isSaved = false;
    			if(Game.bosses.size() <= 0 && Game.enemies.size() <= 0)
    			{
    				String armas = "";
    				for(Weapon w : Game.weapon) {
    					if(w.getSprite() == Entity.WEAPON_ENT_RIFLE_NON_AUTO) {
    						armas += "R,";
    					}
    					
    					if(w.getSprite() == Entity.WEAPON_ENT_SHOTGUN) {
    						armas += "S,";
    					}
    				}
    				System.out.println(armas);
    				try {
						Encryptor enc = new Encryptor();
						var hm = new HashMap<String, String>();
						
						// definindo valores a serem salvos
						hm.put("mapa"	, enc.encrypt(Integer.toString(Game.CUR_LEVEL)));
						hm.put("PlayerX", enc.encrypt(Integer.toString(World.px)));
						hm.put("PlayerY", enc.encrypt(Integer.toString(World.py)));
						hm.put("vida"	, enc.encrypt(Integer.toString(Game.player.getLife())));
						hm.put("municao", enc.encrypt(Integer.toString(Game.player.getAmmo())));
						hm.put("reserva", enc.encrypt(Integer.toString(Game.player.getReserveAmmo())));
						hm.put("armas"	, enc.encrypt(armas));
						hm.put("fire"	, enc.encrypt(Boolean.toString(Game.boss_fire_kill)));
						hm.put("gold"	, enc.encrypt(Integer.toString(Game.player.getGoldAmount())));
						hm.put("dialogos", enc.encrypt(Integer.toString(Game.currentDialogue)));
						Menu.saveGameHM(hm);
					}
    				catch (Exception e) {
						e.printStackTrace();
					}
    				/*
    				String[] opt1 = {"mapa",
									"PlayerX",
									"PlayerY",
									"vida",
									"municao",
									"reserva",
									"armas",
									"fire",
									"gold"
								};
					String[] opt2 = {Integer.toString(Game.CUR_LEVEL), 
									Integer.toString(World.px),
									Integer.toString(World.py),
									Integer.toString(Game.player.getLife()),
									Integer.toString((Game.player.getAmmo() == 0) ? 0 : Game.player.getAmmo()),
									Integer.toString((Game.player.getReserveAmmo() == 0) ? 0 : Game.player.getReserveAmmo()),
									armas,
									Boolean.toString(Game.boss_fire_kill),
									Integer.toString(Game.player.getGoldAmount())
								};

					for(int i = 0; i < opt2.length; i++) {
						System.out.println(opt1[i] + ": " + opt2[i]);
					}
					Menu.saveGame(opt1, opt2, 5);
					*/
					System.out.println("Saved");
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
		if(bShowSavedMessage)
		{
			gfx.setColor(new Color(0, 0, 0,100));
			gfx.fillRect(this.getX() - Camera.x - 42, this.getY() - Camera.y + 102, 100, 30);
			gfx.setColor(new Color(255, 255, 255,150));
			gfx.drawString("Progresso salvo!", this.getX() - Camera.x - 35, this.getY() - Camera.y + 122);
		}
	}
	
	public boolean isCollidingPlayer()
	{
		Rectangle current_enemy = new Rectangle(this.getX(), this.getY(), 32, 64);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), World.TILE_SIZE, World.TILE_SIZE);
		
		return current_enemy.intersects(player);
	}
	
}
