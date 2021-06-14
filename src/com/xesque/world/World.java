package com.xesque.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.xesque.entities.Ammo;
import com.xesque.entities.Boss;
import com.xesque.entities.Boss01;
import com.xesque.entities.Bullet;
import com.xesque.entities.Enemy;
import com.xesque.entities.Entity;
import com.xesque.entities.LifePack;
import com.xesque.entities.Player;
import com.xesque.entities.Portal;
import com.xesque.entities.SaveBeam;
import com.xesque.entities.Weapon;
import com.xesque.graficos.Spritesheet;
import com.xesque.graficos.UI;
import com.xesque.main.Game;

public class World {
	
	public static Tile[] tiles;
	
	public static int HEIGHT;
	public static int WIDTH;
	public static final int TILE_SIZE = 32;
	public static final int TILE_SIZE_BOSS = 128;
	public static int px;

	public static int py;
	
	public static void restartGame(String level)
	{
		Game.entities = new ArrayList < Entity > ();
		Game.GAME_STATE = 0;
    	Game.enemies = new ArrayList< Enemy >();
    	Game.bullets = new ArrayList< Bullet >();
    	Game.bulletsEn = new ArrayList< Bullet >();
    	Game.bosses = new ArrayList< Boss >();
    	Game.spritesheet = new Spritesheet("/spr.png");
    	Game.ui = new UI();
    	Game.player = new Player(0, 0, 32, 32, Game.spritesheet.getSprite(64, 0, 32, 32));
    	Game.player.setLife(Game.player.maxLife);
    	Game.entities.add(Game.player);
    	Game.world = new World(level);
    	return;
	}
	
	public World(String path) 
	{
		try 
		{
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			HEIGHT = map.getHeight();
			WIDTH = map.getWidth();
			
			//int[] pixel = new int[WIDTH * HEIGHT];
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
						Game.showPortal = false;
						Game.entities.add(new Portal(x * 32, y * 32, 64, 64, Entity.PORTAL_01));
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
						Enemy en =new Enemy(x * 32, y * 32, 32, 32, Entity.ENEMY_ENT);
						Game.entities.add(en);
						Game.enemies.add(en);
					break;
					case "0026ff":
						// arma
						Game.entities.add(new Weapon(x * 32, y * 32, 32, 32, Entity.WEAPON_ENT_RIFLE_NON_AUTO, 2));
					break;
					case "3251ff":
						Game.entities.add(new Weapon(x * 32, y * 32, 32, 32, Entity.WEAPON_ENT_SHOTGUN, 1));
					break;
					case "ffd800":
						// munição
						Game.entities.add(new Ammo(x * 32, y * 32, 32, 32, Entity.AMMO_PACK_ENT));
					break;
					case "00ffff":
						// vida
						Game.entities.add(new LifePack(x * 32, y * 32, 32, 32, Entity.LIFE_PACK_ENT));
					break;
					case "a6ff72":
						Boss01 bs = new Boss01(x * 32, y * 32, 128, 128, 0.0, 10, Entity.BOSS_01);
						Game.entities.add(bs);
						Game.bosses.add(bs);
					break;
					
					// Inicio MAP_0
					
					//Gate, Wall TILE
					case "00ff90":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_GATE_M_0_0);
					break;
					case "00d374":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_GATE_M_0_1);
					break;
					case "00c46b":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_GATE_M_0_2);
					break;
					case "00a859":
						tiles[x + (y * WIDTH)] = new WallTile(x * 32, y * 32, Tile.TILE_GATE_M_0_3);
					break;
					
					// Gate, Bottom TILE
					case "00994c":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_4);
					break;
					case "00964b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_5);
					break;
					case "009148":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_6);
					break;
					case "008743":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_GATE_M_0_7);
					break;
					
					// FloorTiles
					case "717271":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_0);
					break;
					case "004a7f":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_1);
					break;
					case "00477a":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_2);
					break;
					case "004475":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_3);
					break;
					case "00355b":
						tiles[x + (y * WIDTH)] = new FloorTile(x * 32, y * 32, Tile.TILE_FLOOR_M_0_4);
					break;
					
					// Fim MAP_0
					// WallTiles
					case "722815":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_0);
					break;
					case "70131c":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_1);
					break;
					case "6b1d25":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_2);
					break;
					case "6d2e3b":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_3);
					break;
					case "6d1836":
						tiles[x + y * WIDTH] = new WallTile(x * 32, y * 32, Tile.TILE_WALL_M_0_4);
					break;
					// Fim WallTiles
					
					
					
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
		
		return !(tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile ||
				tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile ||
				tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile ||
				tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile);
	}
	
	public static boolean isFreeBos(int xNext, int yNext)
	{
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext + TILE_SIZE -1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE -1) / TILE_SIZE;
		
		int x4 = (xNext + TILE_SIZE -1) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE -1) / TILE_SIZE;
		
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
				
			}
		}
	}
}
