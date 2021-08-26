package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;

public class Merchant extends Entity{

	private String[] options = {"Comprar", "Sair"};
	private String[] optionsDeep1 = {"Armas", "Powerups"};

	public HashMap<Integer, BufferedImage> mapperGuns = new HashMap<Integer, BufferedImage>();
	public static HashMap<Integer, Boolean> mapperSold = new HashMap<Integer, Boolean>();

	private int cur_option = 0;
	private int cur_option_deep1 = 0;
	private int cur_option_deep2GUN = 0;
	private int cur_option_deep2POW = 0;
	
	public int levelOfDeep = 0;
	
	public boolean cima = false;
	public boolean baixo = false;
	public boolean left = false;
	public boolean right = false;
	public boolean hitEnter = false;
	public boolean bought = false;
	
	
	public Merchant(int x, int y, int w, int h, BufferedImage sprite) {
		super(x, y, w, h, sprite);
        mapperGuns.put(0, Entity.WEAPON_ENT_RIFLE_NON_AUTO);
		mapperGuns.put(1, Entity.WEAPON_ENT_SHOTGUN);
	}
	
	public void tick() {
		if(Game.GAME_STATE == 3) {
			if(hitEnter) {
				hitEnter = false;
				if(options[cur_option] == "Sair") {
					Game.GAME_STATE = 0;
					Game.player.setX(Game.player.getX() + 32);
				}
				else {
					if(levelOfDeep < 2) {
						levelOfDeep++;
					}
					else if(cur_option_deep2GUN >= 0) {
						if(!mapperSold.containsKey(cur_option_deep2GUN)) {
							bought = true;
						}
					}
				}
				
			}
			if(levelOfDeep == 2) {
				if(left) {
					left = false;
					cur_option_deep2GUN--;
					if(cur_option_deep2GUN < 0) {
						cur_option_deep2GUN = 0;
					}
				}
				else if(right) {
					right = false;
					cur_option_deep2GUN++;
					if(cur_option_deep2GUN >= mapperGuns.size()) {
						cur_option_deep2GUN = mapperGuns.size() - 1;
					}
				}
			}
		}
		
		if(baixo) {
			if(levelOfDeep == 0) {
				cur_option++;
				if(cur_option >= options.length) {
					cur_option = 0;
				}
			}
			
			else if(cur_option_deep1 == 1) {
				cur_option_deep1++;
				if(cur_option_deep1 >= optionsDeep1.length) {
					cur_option_deep1 = 0;
				}
			}
			baixo = false;
		}
		
		else if(cima) {
			if(levelOfDeep == 0) {
				cur_option--;
				if(cur_option < 0) {
					cur_option = options.length - 1;
				}
			}
			
			else if(levelOfDeep == 1) {
				cur_option_deep1--;
				if(cur_option_deep1 < 0) {
					cur_option_deep1 = optionsDeep1.length - 1;
				}
			}
			cima = false;
		}
		
		if(Entity.isColliding(this, Game.player)) {
			Game.GAME_STATE = 3;
		}
		else {
			if(Game.GAME_STATE == 3) {
				Game.GAME_STATE = 0;
				// reset state
				cur_option = 0;
				cur_option_deep1 = 0;
				cur_option_deep2GUN = 0;
				cur_option_deep2POW = 0;
				
				levelOfDeep = 0;
				
				cima = false;
				baixo = false;
				left = false;
				right = false;
				hitEnter = false;
				bought = false;
			}
		}
	}
	
	public void render(Graphics gfx) {
		gfx.drawImage(Entity.MerchantIdle,
				      this.getX() - Camera.x,
				      this.getY() - Camera.y,
				      null);
		
		// Options
		if(Game.GAME_STATE == 3) {
			gfx.setColor(new Color(124, 63, 12));
			gfx.fillRect(0, 0, Game.WIDTH, Game.HEIGHT_SCALE);
			
			gfx.setColor(new Color(0,0,0,100));
			gfx.setFont(Game.main_font.deriveFont(40f));
			gfx.drawString("Loja", Game.WIDTH / 2 - 30, 70);
			gfx.drawRect(Game.WIDTH / 2 - 60, 80, 125, 2);
			
			gfx.setColor(Color.WHITE);
			gfx.setFont(Game.main_font.deriveFont(38f));
			gfx.drawString("Loja", Game.WIDTH / 2 - 28, 70);
			gfx.setColor(new Color(0,0,0,100));
			
			gfx.setFont(Game.main_font.deriveFont(20f));
			gfx.drawString("Comprar", 100, 150);
			gfx.drawString("Sair", 100, 200);
			gfx.drawRect(80, 160, 110, 2);
			gfx.drawRect(80, 210, 75, 2);
			
			gfx.setColor(new Color(0,0,0,100));
			gfx.drawString("Seu ouro: " + Game.player.getGoldAmount(), 215, 300);
			gfx.setColor(Color.WHITE);
			gfx.setFont(Game.main_font.deriveFont(19f));
			gfx.drawString("Seu ouro: " + Game.player.getGoldAmount(), 216, 300);
			
			gfx.setColor(Color.WHITE);
			gfx.setFont(Game.main_font.deriveFont(19f));
			gfx.drawString("Comprar", 100, 150);
			
			gfx.setFont(Game.main_font.deriveFont(18f));
			gfx.drawString("Sair", 100, 200);
			gfx.setColor(Color.BLACK);
			gfx.setFont(Game.main_font.deriveFont(20f));
			
			gfx.setColor(Color.LIGHT_GRAY);
			if(options[cur_option] == "Comprar") {
				gfx.drawString(">", 85, 150);
				gfx.drawString("<", 170, 150);
			}
			else if(options[cur_option] == "Sair") {
				gfx.drawString(">", 85, 200);
				gfx.drawString("<", 135, 200);
			}
			gfx.setColor(new Color(0,0,0,100));
			if(levelOfDeep >= 1) {
				
				gfx.drawString("Armas", 230, 150);
				gfx.drawString("Powerups", 220, 200);
				gfx.drawRect(200, 160, 110, 2);
				gfx.drawRect(200, 210, 110, 2);
				
				gfx.setColor(Color.WHITE);
				gfx.setFont(Game.main_font.deriveFont(19f));
				gfx.drawString("Armas", 230, 150);
				gfx.drawString("Powerups", 220, 200);
				gfx.setColor(Color.BLACK);
				
				gfx.setColor(Color.LIGHT_GRAY);
				if(optionsDeep1[cur_option_deep1] == "Armas") {
					gfx.drawString(">", 215, 150);
					gfx.drawString("<", 280, 150);
				}
				else if(optionsDeep1[cur_option_deep1] == "Powerups") {
					gfx.drawString(">", 205, 200);
					gfx.drawString("<", 295, 200);
				}
			}
			if(levelOfDeep == 2) {
				gfx.setColor(Color.LIGHT_GRAY);
				gfx.setFont(Game.main_font.deriveFont(25f));
				if(cur_option_deep2GUN == 0 || cur_option_deep2GUN < mapperGuns.size() - 1) {
					gfx.drawString("->", 485, 180);
				}
				if(cur_option_deep2GUN == mapperGuns.size() || cur_option_deep2GUN >= 1) {
					gfx.drawString("<-", 335, 180);
				}
				
				gfx.setFont(Game.main_font.deriveFont(15f));
				gfx.setColor(Color.BLACK);
				gfx.drawRect(365, 120, 115, 100);
				
				gfx.drawImage(mapperGuns.get(cur_option_deep2GUN), 410, 120, null);
				
				if(cur_option_deep2GUN >= 0) {
					gfx.drawRect(365,230,60,20);
					if(mapperSold.containsKey(cur_option_deep2GUN)) {
						gfx.drawRect(420,230,60,20);
						gfx.drawString("Vendido", 430, 244);
						gfx.setColor(new Color(0, 0, 0, 100));
					}
					gfx.drawString("Comprar", 370, 244);
					if(bought) {
						tellSoldItem();
					}
					gfx.setColor(Color.BLACK);
				}
				
				if(cur_option_deep2GUN == 0) {
					// Price
					gfx.fillOval(350, 105, 30, 30);
					gfx.setColor(Color.WHITE);
					gfx.drawString("$50", 355, 125);
					gfx.setColor(Color.BLACK);
					
					// Title
					gfx.drawString("Pistola 9mm", 390, 165);
					gfx.setFont(Game.main_font.deriveFont(13f));
					
					//
					gfx.drawRect(375, 177, 45, 7);
					gfx.fillRect(375, 178, 10, 7);
					gfx.drawString("Dano", 425, 185);
					
					//
					gfx.fillRect(376, 198, 15, 6);
					gfx.drawRect(375, 197, 45, 7);
					gfx.drawString("Cadencia", 425, 205);
				}
				else if(cur_option_deep2GUN == 1) {
					// Price
					gfx.fillOval(350, 105, 35, 30);
					gfx.setColor(Color.WHITE);
					gfx.drawString("$150", 355, 125);
					gfx.setColor(Color.BLACK);
					
					gfx.drawString("Shotgun", 400, 165);
					gfx.setFont(Game.main_font.deriveFont(13f));
					
					//
					gfx.drawRect(375, 177, 45, 7);
					gfx.fillRect(376, 178, 20, 6);
					gfx.drawString("Dano", 425, 185);
					
					//
					gfx.fillRect(376, 198, 10, 6);
					gfx.drawRect(375, 197, 45, 7);
					gfx.drawString("Cadencia", 425, 205);
				}
			}
		}
	}
	private void tellSoldItem() {
		//
		bought = false;
		Integer total = 99999999; // :)
		if (cur_option_deep2GUN == 1) {
			total = 150;
			
			Integer subtotal = Game.player.getGoldAmount() - total;
			System.out.println("total: " + total);
			System.out.println("getAmmo: " + Game.player.getGoldAmount());
			System.out.println("Sobrou: " + subtotal);
			if (subtotal >= 0) {
				mapperSold.put(cur_option_deep2GUN, true);
				Game.player.setGoldAmount(subtotal);
				Weapon w = new Weapon(0, 0, 32, 32, mapperGuns.get(cur_option_deep2GUN), cur_option_deep2GUN);
				Game.weapon.add(w);
				Game.player.hasWeapon = true;
				Game.player.max_weapon++;
				if(Game.player.getGunLeft() == null) {
					Game.player.setWeapon(w);
				}
			}
		}
	}
}
