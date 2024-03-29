package com.projekt.CursedMemories.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.projekt.CursedMemories.entities.Ammo;
import com.projekt.CursedMemories.entities.Boss;
import com.projekt.CursedMemories.entities.Boss01;
import com.projekt.CursedMemories.entities.Boss2;
import com.projekt.CursedMemories.entities.Boss3;
import com.projekt.CursedMemories.entities.Bullet;
import com.projekt.CursedMemories.entities.Construction;
import com.projekt.CursedMemories.entities.Darker;
import com.projekt.CursedMemories.entities.Enemy;
import com.projekt.CursedMemories.entities.Entity;
import com.projekt.CursedMemories.entities.ExplosionParticle;
import com.projekt.CursedMemories.entities.LifePack;
import com.projekt.CursedMemories.entities.Merchant;
import com.projekt.CursedMemories.entities.Player;
import com.projekt.CursedMemories.entities.Portal;
import com.projekt.CursedMemories.entities.SaveBeam;
import com.projekt.CursedMemories.entities.Weapon;
import com.projekt.CursedMemories.graficos.Spritesheet;
import com.projekt.CursedMemories.graficos.UI;
import com.projekt.CursedMemories.main.Game;

public class World {
	
	public static Tile[] tiles;
	
	public static Integer[] coordsToRemove = new Integer[2];
	
	public static int HEIGHT;
	public static int WIDTH;
	public static final int TILE_SIZE = 32;
	public static final int TILE_SIZE_BOSS = 128;
	public static int px;

	public static int py;
	
	public static int MAX_MAP_Y;
	public static int MAX_MAP_X;
	
	public static void setLevel(String level, boolean showBoss)
	{
		Merchant m = null;
		
		for(Entity e : Game.entities) {
			if (e instanceof Merchant) {
				m = (Merchant) e;
			}
		}
		
		Player player = Game.player;
		player.setLife(Game.player.getLife());
		List<Weapon> ws = Game.weapon;
		Game.entities = new ArrayList < Entity > ();
		Game.entities.add(m);
		Game.GAME_STATE = 0;
		Game.nextLevel = false;
		Game.CHANGE_LEVEL = false;
    	Game.enemies = new ArrayList< Enemy >();
    	Game.bullets = new ArrayList< Bullet >();
    	Game.bulletsEn = new ArrayList< Bullet >();
    	Game.bosses = new ArrayList< Boss >();
    	Game.spritesheet = new Spritesheet("/spr.png");
    	Game.ui = new UI("/HUD_HEALTH_PLAYER.png");
    	Game.player = player;
    	Game.weapon = ws;
    	Game.entities.add(Game.player);
    	Game.world = new World(level, showBoss);
		return;
	}
	
	public static void restartGame(String level, boolean showBoss)
	{
		Merchant m = null;
		
		for(Entity e : Game.entities) {
			if (e instanceof Merchant)
				m = (Merchant) e;
		}
		Game.entities = new ArrayList < Entity > ();
		Game.entities.add(m);
		Game.GAME_STATE = 0;
		Game.nextLevel = false;
		Game.CHANGE_LEVEL = false;
    	Game.enemies = new ArrayList< Enemy >();
    	Game.bullets = new ArrayList< Bullet >();
    	Game.bulletsEn = new ArrayList< Bullet >();
    	Game.weapon = new ArrayList<Weapon>();
    	Game.bosses = new ArrayList< Boss >();
    	Game.spritesheet = new Spritesheet("/spr.png");
    	Game.ui = new UI("/HUD_HEALTH_PLAYER.png");
    	Game.player = new Player(0, 0, 32, 32, Game.spritesheet.getSprite(64, 0, 32, 32));
    	Game.player.setLife(Game.player.maxLife);
    	Game.entities.add(Game.player);
    	Game.world = new World(level, showBoss);
    	return;
	}
	
	public World(String path, boolean showBoss) 
	{
		try 
		{
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			HEIGHT = map.getHeight();
			WIDTH = map.getWidth();
			
			MAX_MAP_X = ((WIDTH * HEIGHT) * 2) - 128;
			MAX_MAP_Y = ((WIDTH * HEIGHT) * 2) - 128;
			
			tiles = new Tile[WIDTH * HEIGHT];
			
			for(int x = 0; x < WIDTH; x++)
			{
				for(int y = 0; y < HEIGHT; y++)
				{
					Color c = new Color(map.getRGB(x, y));
					String h = String.format("%02x%02x%02x", c.getRed(),c.getGreen(),c.getBlue());
					// padr�o (ch�o)
					switch(path)
					{
					case "/map_1.png":
					case "/map_0.png":
					case "/map_caveira.png":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C_A1);
					break;
					case "/map_2.png":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A3);
					break;
					case "/map_vulcao.png":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_DRY_LAVA);
					break;
					case "/map_clock.png":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_A1);
					break;
					default:
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_0);
					}
					
					switch(h)
					{
					case "ffd799":
						// ch�o
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL);
					break;
					
					case "7f006e":
						if(showBoss)
							Game.showPortal = false;
						else
							Game.showPortal = true;
						Game.entities.add(new Portal(x * 32, y * 32, 64, 103, Entity.PORTAL_01));
					break;
					
					case "007f7f":
						Game.CHANGE_LEVEL = true;
						Game.showPortal = true;
						Game.entities.add(new Portal(x * 32, y * 32, 96, 96, Entity.PORTAL_00));
					break;
					
					case "ffbb5b":
						px = x * 32;
						py = y * 32;
						Game.entities.add(new SaveBeam(x * 32, y * 32, 32, 64, Entity.SAVE_BEAM));
					break;
					
					case "ff00dc":
						// player
						Game.player.setX(x * 32);
						Game.player.setY(y * 32);
					break;
					case "ff0000":
						// inimigo
						if(showBoss) {
							Enemy en =new Enemy(x * 32, y * 32, 32, 32, Entity.ENEMY_ENT);
							Game.entities.add(en);
							Game.enemies.add(en);
						}
					break;
					// ARMAS
					case "0026ff":
						// rifle
						if(showBoss) {
							Game.entities.add(new Weapon(x * 32, y * 32, 32, 32, Entity.WEAPON_ENT_RIFLE_NON_AUTO, 2));
						}
					break;
					case "3251ff":
							// shotgun
						if(showBoss) {
							Game.entities.add(new Weapon(x * 32, y * 32, 32, 32, Entity.WEAPON_ENT_SHOTGUN, 1));
						}
					break;
					// FIM ARMAS
					
					case "ffd800":
						// muni��o
						if(showBoss) {
							Game.entities.add(new Ammo(x * 32, y * 32, 32, 32, Entity.AMMO_PACK_ENT));
						}
					break;
					case "00ffff":
						// vida
						if(showBoss) {
							Game.entities.add(new LifePack(x * 32, y * 32, 32, 32, Entity.LIFE_PACK_ENT));
						}
					break;
					// BOSSES 
					case "a6ff72":
						if(showBoss) {
							Boss01 bs = new Boss01(x * 32, y * 32, 128, 128, 1.0, 150, Entity.BOSS_01_defaultR);
							Game.entities.add(bs);
							Game.bosses.add(bs);
						}
					break;
					case "689647":
						if(showBoss) {
							Boss2 bs = new Boss2(x * 32, y * 32, 64, 96, 1.0, 450, null);
							Game.entities.add(bs);
							Game.bosses.add(bs);
						}
					break;
					case "ffa0b8":
						if(showBoss) {
							Boss3 bs = new Boss3(x * 32, y * 32, 128, 128, 1.0, 400, null);
							Game.entities.add(bs);
							Game.bosses.add(bs);
						}
					break;
					case "43ff00":
						Darker darker = new Darker(x * 32, y * 32, 64, 64, 0.0, 1, Darker.IDLE_IMG);
						Game.entities.add(darker);
						Game.bosses.add(darker);
						break;
					// Merchant
					case "0094ff":
						Game.merchant.setX(x * 32);
						Game.merchant.setY(y * 32);
					break;
					// Mapa Vulcao
					case "ff6a00":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_WALL_LAVA01);
					break;
					case "930238":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_FLOOR_BRICK);
					break;
					case "963103":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_DRY_LAVA);
					break;
					
					
					case "934961":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_LAVA_RED);
					break;
					case "890000":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_BRICK_RED);
					break;
					case "960000":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_BRICK_RED_TOP);
					break;
					
					// Inicio MAP_0
					
					//Gate, Wall TILE
					case "00994c":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_0);
					break;
					case "00964b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_1);
					break;
					case "009148":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_2);
					break;
					case "008743":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_3);
					break;
					case "00ff90":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_4);
					break;
					case "00d374":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_5);
					break;
					case "00c46b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_6);
					break;
					case "00a859":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_7);
					break;
					
					case "85bf94":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_LION_TOP_LEFT);
					break;
					case "7cb28a":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_LION_TOP_RIGHT);
					break;
					case "74a580":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_LION_MIDDLE_LEFT);
					break;
					case "6b9976":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_LION_MIDDLE_RIGHT);
					break;
					case "628c6c":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_LION_BOTTOM_LEFT);
					break;
					case "597f62":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_LION_BOTTOM_RIGHT);
					break;
					
					// FloorTiles
					case "004a7f":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_1);
					break;
					case "19679f":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_2);
					break;
					case "3f8bc1":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_3);
					break;
					case "00355b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_4);
					break;
					case "717271":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_0);
					break;
					
					//Wall Tiles
					case "22b2f4":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_BLOCK);
					break;
					case "722815":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_BOTTOM);
					break;
					case "70131c":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_RIGHT);
					break;
					case "6b1d25":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_TOP);
					break;
					case "6d2e3b":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_LEFT);
					break;
					
					// Boxes
					case "9707ff":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_STAIR_BOX_TOP_LEFT);
					break;
					case "8506e5":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_STAIR_BOX_TOP_CENTER);
					break;
					case "7606cc":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_STAIR_BOX_TOP_RIGHT);
					break;
					
					case "390366":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_STAIR_BOX_BOTTOM_LEFT);
					break;
					case "2b024c":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_STAIR_BOX_BOTTOM_CENTER);
					break;
					case "1c0133":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_STAIR_BOX_BOTTOM_RIGHT);
					break;
					
					case "4c4c4c":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_BLACK);
					break;

					// Main Stairs
					case "ff006e":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S0);
					break;
					case "f20068":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S1);
					break;
					case "e50063":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S2);
					break;
					
					case "d8005d":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S3);
					break;
					case "cc0058":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S4);
					break;
					case "bf0052":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S5);
					break;
					
					
					case "b2004d":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S6);
					break;
					case "a50047":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S7);
					break;
					case "990042":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S8);
					break;
					
					
					case "8c003c":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S9);
					break;
					case "7f0037":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S10);
					break;
					case "720031":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S11);
					break;
					
					
					case "ff0c75":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S12);
					break;
					case "ff197c":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S13);
					break;
					case "ff2684":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S14);
					break;
					
					case "ff328b":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S15);
					break;
					case "ff3f92":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S16);
					break;
					case "ff4c99":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S17);
					break;
					
					case "ff0098":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S18);
					break;
					case "ff0c9e":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S19);
					break;
					case "ff19a3":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S20);
					break;
					case "ff26a8":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S21);
					break;
					case "ff32ad":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S22);
					break;
					case "ff3fb2":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S23);
					break;
					
					
					
					
					case "ff0061":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S24);
					break;
					case "ff0c69":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S25);
					break;
					case "ff1971":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S26);
					break;
					case "ff2679":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S27);
					break;
					case "ff3281":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S28);
					break;
					case "ff3f89":
						tiles[x + y * WIDTH] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_STAIR_S29);
					break;
					// Fim WallTiles
					// Fim MAP_0
					
					
					
					// Inicio MAP_1 
					case "87fffd":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_0);
					break;
					
					
					case "00f6ff":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_1);
					break;
					case "3dbeff":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_19);
					break;
					case "7f96ff":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_20);
					break;
					case "ae8eff":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_21);
					break;
					case "d684ff":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_22);
					break;
					case "f67cff":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_23);
					break;
					case "ff87e5":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_24);
					break;
					case "ff7cbb":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_25);
					break;
					case "ff709d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_26);
					break;
					
					
					case "007377":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_0);
					break;
					case "677475":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_1);
					break;
					case "147a3b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_13);
					break;
					case "637751":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_14);
					break;
					
					// Complemento mapa esquerda
					case "71c8ce":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_2);
					break;
					// Complemento mapa direita
					case "664244":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_12);
					break;
					
					
					// Pilares virados para a direita
					// Cima
					case "718fce":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_3);
					break;
					// Inferior
					case "7152ce":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_4);
					break;
					// Entre os pilares
					case "2d844f":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_5);
					break;
					
					// Pilares parte de cima
					// Cima
					case "0c6166":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_6);
					break;
					// Inferior
					case "233666":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_7);
					break;
					// Entre os pilares
					case "434b66":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_8);
					break;
					
					// Pilares dereita
					// Cima
					case "fbffaf":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_9);
					break;
					// Inferior
					case "ffb28e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_10);
					break;
					// Entre os pilares
					case "93ffb0":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_11);
					break;
					
					// Parte inferior do mapa
					case "a1ff5e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_16);
					break;
					case "f9ff66":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_15);
					break;
					case "ffc587":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_17);
					break;
					case "ff804f":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_1_18);
					break;
					// Fim MAPA_1
					
					// Mapa Lobby
					case "747474":
						Game.entities.add(new Construction(x * 32, y * 32, 256, 224, Entity.VULCAO, 1));
					break;
					case "838383":
						Game.entities.add(new Construction(x * 32, y * 32, 288, 240, Entity.TEMPLO, 2));
					break;
					case "7c4f45":
						Game.entities.add(new Construction(x * 32, y * 32, 64, 103, Entity.PORTAL_01, 3));
					break;
					
					case "5fcde4":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_A1);
					break;
					
					case "895a45":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A2);
					break;
					
					
					// pontes duplas / subida
					case "915f4a":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A20);
					break;
					case "8e8230":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A21);
					break;
					case "63400f":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A23);
					break;
					case "685f23":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A22);
					break;
					case "7a492a":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A24);
					break;
					
					// horz
					case "6b6f77":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A25);
						break;
					case "770531":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A26);
						break;
					case "cc0a54":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A27);
						break;
					case "91073c":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A28);
						break;
					case "a9b0bc":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A29);
						break;
						
					case "44464c":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A30);
						break;
					case "3b0218":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A31);
						break;
					case "650429":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A32);
						break;
					case "48031d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A33);
						break;
					case "757a82":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A34);
						break;
					// fim
					case "579657":
						if (Game.boss_clock_kill) {
							System.out.println("CLOCK DEAD");
							tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_AB);
						}
						else {
							tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_AD);
						}
					break;
					
					case "427f3b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A3);
					break;
					case "428b3b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A4);
					break;
					
					
					case "31662b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_AA);
					break;
					case "d8d8d8":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_AB);
					break;
					case "9e9e9e":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_AC);
					break;
					case "a0a0a0":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_AD);
					break;
					case "444444":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_AE);
					break;
					case "707070":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_AF);
					break;
					case "999999":	
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_AG);
					break;
					case "919191":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_AH);
					break;
					
					
					case "5b7f3b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A5);
					break;
					case "80a53f":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A6);
					break;
					case "80bf3f":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A7);
					break;
					case "a7acba":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A8);
					break;
					case "b8acba":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A9);
					break;
					
					case "87616a":
						// boss_fire_kill
						if (Game.boss_fire_kill) {
							tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_LAVA_SECA);
						}
						else {
							tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_A10);
						}
					break;
					
					case "523b40":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_A10);
					break;
					
					case "cd704d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A11);
					break;
					case "bf704d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A12);
					break;
					case "df704d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A13);
					break;
					case "9195a1":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A14);
					break;
					case "a895a1":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_A15);
					break;
					case "5880cc":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_A16);
					break;
					
					
					
					case "e6deca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_B1);
					break;
					case "e4deca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_B2);
					break;
					case "e4d1ca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C1);
					break;
					case "e4caca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C2);
					break;
					case "e4c1ca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_D1);
					break;
					case "e4beca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_D2);
					break;
					
					
					case "1f5836":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_B3);
					break;
					case "1f5436":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C3);
					break;
					case "1f4f36":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_B4);
					break;
					case "1f3736":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C4);
					break;
					
					
					case "517f3b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B5);
					break;
					case "5f7f3b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B6);
					break;
					case "51ac3b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_B7);
					break;
					case "51943b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_B8);
					break;
					case "895a28":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B9);
					break;
					case "895a15":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B10);
					break;
					
					
					
					
					case "895a07":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B11);
					break;
					case "894407":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B12);
					break;
					case "974407":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B13);
					break;
					case "895a0f":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B14);
					break;
					case "894c17":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B15);
					break;
					case "895a17":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_B16);
					break;
					case "973407":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C5);
					break;
					case "5d283d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C6);
					break;
					case "7c5a45":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C7);
					break;
					case "814407":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_D3);
					break;
					case "764407":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_D4);
					break;
					case "6b4407":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_D5);
					break;
					case "634407":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_D6);
					break;
					
					
					
					
					
					
					case "bbc44e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C8);
					break;
					case "aec44e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C9);
					break;
					case "a1c44e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_D8);
					break;
					case "98c44e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_D9);
					break;
					case "98a54e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C10);
					break;
					case "afb44e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_D10);
					break;
					case "89a524":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_D7);
					break;
					case "89a53b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C11);
					break;
					case "d5deca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C12);
					break;
					case "dddeca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C13);
					break;
					case "bfdeca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_D11);
					break;
					case "61a54e":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_D12);
					break;
					case "cadeca":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_D13);
					break;
					
					
					
					
					
					
					case "e82f44":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_E1);
					break;
					case "e84444":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_E2);
					break;
					case "f92f2b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_F1);
					break;
					case "f62f2b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_F2);
					break;
					case "606060":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_E3);
					break;
					case "9a7f5b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_E4);
					break;
					case "9a8571":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_F3);
					break;
					case "9a9171":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_F4);
					break;
					
					
					
					case "f61f2b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_E5);
					break;
					case "f6132b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_E6);
					break;
					case "f6092b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_F5);
					break;
					case "f6032b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_F6);
					break;
					case "f60334":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_E7);
					break;
					case "f60341":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_E8);
					break;
					case "f60349":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_F7);
					break;
					case "f60355":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_F8);
					break;
					
					
					
					case "7b5a45":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_E9);
					break;
					case "7b4c45":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_E10);
					break;
					case "7b5836":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_E11);
					break;
					case "7b4c36":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_E12);
					break;
					case "7b5f36":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_E13);
					break;
					case "7b5f43":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_E14);
					break;
					
					
					
					case "802954":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_G1);
					break;
					case "a22954":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_G2);
					break;
					case "a23369":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_H2);
					break;
					case "912954":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_G3);
					break;
					case "a2295b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_G4);
					break;
					case "a22969":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_H4);
					break;
					case "a22269":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_G5);
					break;
					case "a20c69":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_G6);
					break;
					case "bf574d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_G7);
					break;
					
					
					case "a4704d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_G8);
					break;
					case "92704d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_G9);
					break;
					
// MAPA CAVEIRA
					
					case "40444b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C_A1);
					break;
					case "8c8d93":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_A2);
					break;
					case "7b8d93":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_A3);
					break;
					case "adaeb0":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_A4);
					break;
					case "100f0f":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_A5);
					break;
					
					
					
					
					case "29261e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_B1);
					break;
					case "36261e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_B2);
					break;
					case "40261e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_B3);
					break;
					case "40311e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_C1);
					break;
					case "403a1e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_C2);
					break;
					case "40401e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_C3);
					break;
					case "404027":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_D1);
					break;
					case "40402f":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_D2);
					break;
					case "404038":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_D3);
					break;
					
					
					
					
					
					case "706b72":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_B4);
					break;
					case "786b72":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_B5);
					break;
					case "787572":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_C4);
					break;
					case "788272":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_C5);
					break;
					case "78827b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_D4);
					break;
					case "788286":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_C_D5);
					break;
					
					
					
					case "2c3038":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C_E1);
					break;
					case "393038":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C_E2);
					break;
					case "4b3038":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C_E3);
					break;
					case "433038":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_C_E4);
					break;
					
					// FIM MAPA CAVEIRA
					
					// MAPA RELOGIO
					
					
					case "466b87":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_A1);
					break;
					case "596e78":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_A2);
					break;
					case "ebf9ff":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_A3);
					break;
					case "c2be68":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_A4);
					break;
					case "abc5d1":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_A5);
					break;
					case "ffffff":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_R_A6);
					break;
					
					
					
					
					case "1d3547":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_B1);
					break;
					case "2a3547":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_B2);
					break;
					case "393547":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_B3);
					break;
					case "413547":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_B4);
					break;
					case "413f47":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_B5);
					break;
					case "414b47":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_B6);
					break;
					case "414b52":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_B7);
					break;
					case "414b5d":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_R_B8);
					break;
					
					// FIM MAPA RELOGIO
					}
				}
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void generateParticles(Integer amount, Integer x, Integer y) {
		for(int count = 0; count < amount; count++) {
			Game.entities.add(new ExplosionParticle(x, y, 5, 5, null));
		}
	}
	
	public static void generateParticles(Integer amount, Integer x, Integer y, Color color) {
		for(int count = 0; count < amount; count++) {
			Game.entities.add(new ExplosionParticle(x, y, 5, 5, null, color));
		}
	}
	
	public static boolean isFree(int xNext, int yNext)
	{
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext + TILE_SIZE -1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE -1) / TILE_SIZE;
		
		int x4 = (xNext + TILE_SIZE -1) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE -1) / TILE_SIZE;
		
		if(tiles[x1 + (y1 * World.WIDTH)].getTexture() == Tile.TILE_WALL_RIGHT ||
		   tiles[x2 + (y2 * World.WIDTH)].getTexture() == Tile.TILE_WALL_RIGHT ||
		   tiles[x3 + (y3 * World.WIDTH)].getTexture() == Tile.TILE_WALL_RIGHT ||
		   tiles[x4 + (y4 * World.WIDTH)].getTexture() == Tile.TILE_WALL_RIGHT) {
			if(!Game.player.invulnerable) {
				var vida = 0;
				if (Game.difficult == "Dificil")
					vida = 3;
				else if(Game.difficult == "Dificil")
					vida = 2;
				else
					vida = 1;
				
				Game.player.isDameged = true;
				Game.player.setLife(Game.player.getLife() - vida);
				Game.player.invulnerable = true;
			}
		}
		
		return !(tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile ||
				tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile ||
				tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile ||
				tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile);
	}
	
	public static boolean isFreeDynamic(int xNext, int yNext, int width, int height)
	{
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext + width -1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + height -1) / TILE_SIZE;
		
		int x4 = (xNext + width -1) / TILE_SIZE;
		int y4 = (yNext + height -1) / TILE_SIZE;
		
		return !(tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile ||
				tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile ||
				tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile ||
				tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile);
	}
	
	
	public void render(Graphics gfx)
	{
		int xStart = Camera.x / TILE_SIZE;
		int yStart = Camera.y / TILE_SIZE;
		int xFinal = xStart + (Game.WIDTH / TILE_SIZE);
		int yFinal = yStart + (Game.HEIGHT / TILE_SIZE);
		
		for(int x = xStart; x <= xFinal; x++)
		{
			for(int y = yStart; y <= yFinal; y++)
			{
				if(x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT)
				{
					continue;
				}
				Tile tile = tiles[x  + (y * WIDTH)];
				try {
					tile.render(gfx);
				}
				catch(NullPointerException e) {
				
				}
				coordsToRemove[0] = x;
				coordsToRemove[1] = y;
			}
		}
	}
	public static void setTileTo(Tile tile) {
		if(!(tiles[tile.getX()  + (tile.getY() * WIDTH)] instanceof WallTile)) {
			tiles[tile.getX()  + (tile.getY() * WIDTH)].setTexture(tile.getTexture());
		}
	}
}
