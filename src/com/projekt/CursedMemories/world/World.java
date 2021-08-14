package com.projekt.CursedMemories.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.projekt.CursedMemories.entities.Ammo;
import com.projekt.CursedMemories.entities.Boss;
import com.projekt.CursedMemories.entities.Boss01;
import com.projekt.CursedMemories.entities.Bullet;
import com.projekt.CursedMemories.entities.Darker;
import com.projekt.CursedMemories.entities.Enemy;
import com.projekt.CursedMemories.entities.Entity;
import com.projekt.CursedMemories.entities.ExplosionParticle;
import com.projekt.CursedMemories.entities.LifePack;
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
	
	public static void restartGame(String level, boolean showBoss)
	{
		Game.entities = new ArrayList < Entity > ();
		Game.GAME_STATE = 0;
		Game.nextLevel = false;
		Game.CHANGE_LEVEL = false;
    	Game.enemies = new ArrayList< Enemy >();
    	Game.bullets = new ArrayList< Bullet >();
    	Game.bulletsEn = new ArrayList< Bullet >();
    	Game.weapon = new ArrayList< Weapon > ();
    	Game.bosses = new ArrayList< Boss >();
    	Game.spritesheet = new Spritesheet("/spr.png");
    	Game.ui = new UI();
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
					// padrão (chão)
					switch(path)
					{
					case "/map_0.png":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_1);
					break;
					case "/map_1.png":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_1_0);
					break;
					default:
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_0);
					}
					
					switch(h)
					{
					case "ffd799":
						// chão
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL);
					break;
					// PROVAVEL MAPA, DEIXAR
					/*
					case "000000":
						// chão
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_GENERIC);
					break;
					case "ff006e":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_CRUZ_FLOOR);
					break;
					case "70ff8a":
						// lava
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_RIGHT);
					break;
					case "93713e":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_STD_ROW);
					break;
					///
					case "968470":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_STD_COL);
					break;
					///
					case "b2698b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_RIGHT);
					break;
					
					case "b200ff":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_LGRAY);
					break;
					*/
					case "7f006e":
						if(showBoss) {
							Game.showPortal = false;
						}
						else {
							Game.showPortal = true;
						}
						Game.entities.add(new Portal(x * 32, y * 32, 64, 103, Entity.PORTAL_01));
					break;
					
					case "007f7f":
						Game.CHANGE_LEVEL = true;
						Game.showPortal = true;
						Game.entities.add(new Portal(x * 32, y * 32, 96, 96, Entity.PORTAL_00));
					break;
					
					case "ffbb5b":
						Game.showPortal = false;
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
						// munição
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
							Boss01 bs = new Boss01(x * 32, y * 32, 128, 128, 0.0, 1, Entity.BOSS_01_defaultR);
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
		for(var count = 0; count < amount; count++) {
			Game.entities.add(new ExplosionParticle(x, y, 5, 5, null));
		}
	}
	
	public static void generateParticles(Integer amount, Integer x, Integer y, Color color) {
		for(var count = 0; count < amount; count++) {
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
				Game.player.isDameged = true;
				Game.player.setLife(Game.player.getLife() - 3);
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
				tile.render(gfx);
				coordsToRemove[0] = x;
				coordsToRemove[1] = y;
			}
		}
	}
	public static void setTileTo(Tile tile) {
		if(!(tiles[tile.getX()  + (tile.getY() * WIDTH)] instanceof WallTile)) {
			tiles[tile.getX()  + (tile.getY() * WIDTH)].setTexture(tile.getTexture());
			System.out.println("Mudou");
		}
		else {
			System.out.println("Parede");
		}
	}
	
}
