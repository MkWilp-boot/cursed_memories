package com.xesque.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;

public class Tile {
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 32, 32);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(32, 0, 32, 32);
	public static BufferedImage TILE_CRUZ_FLOOR = Game.spritesheet.getSprite(0, 64, 32, 32);
	public static BufferedImage TILE_LEFT_WALL = Game.spritesheet.getSprite(32, 64, 32, 32);
	public static BufferedImage TILE_LEFT_CONT_WALL = Game.spritesheet.getSprite(64, 64, 32, 32);
	public static BufferedImage TILE_FLOOR_GENERIC = Game.spritesheet.getSprite(96, 64, 32, 32);
	public static BufferedImage TILE_FLOOR_TOP = Game.spritesheet.getSprite(128, 64, 32, 32);
	public static BufferedImage TILE_WALL_RIGHT = Game.spritesheet.getSprite(160, 64, 32, 32);
	public static BufferedImage TILE_WALL_TOP_RIGHT = Game.spritesheet.getSprite(192, 64, 32, 32);
	public static BufferedImage TILE_WALL_RIGHT_SHA = Game.spritesheet.getSprite(224, 64, 32, 32);
	public static BufferedImage TILE_WALL_STD_COL = Game.spritesheet.getSprite(256, 64, 32, 32);
	public static BufferedImage TILE_WALL_STD_ROW = Game.spritesheet.getSprite(288, 64, 32, 32);
	public static BufferedImage TILE_FLOOR_LGRAY = Game.spritesheet.getSprite(224, 64, 32, 32);
	
	// MAP_1
	// FLOOR
	public static BufferedImage TILE_FLOOR_M_1_0 = Game.spritesheet.getSprite(0, 256, 32, 32);
	public static BufferedImage TILE_FLOOR_M_1_1 = Game.spritesheet.getSprite(32, 256, 32, 32);
	
	// MAP_1
	// WALL
	public static BufferedImage TILE_WALL_M_1_0 = Game.spritesheet.getSprite(0, 288, 32, 32);
	public static BufferedImage TILE_WALL_M_1_1 = Game.spritesheet.getSprite(0, 320, 32, 32);
	public static BufferedImage TILE_WALL_M_1_2 = Game.spritesheet.getSprite(32, 288, 32, 32);
	// Pilares virados para a direita
	// Cima
	public static BufferedImage TILE_WALL_M_1_3 = Game.spritesheet.getSprite(0, 352, 32, 32);
	// Inferior
	public static BufferedImage TILE_WALL_M_1_4 = Game.spritesheet.getSprite(0, 384, 32, 32);
	
	private BufferedImage sprite;
	
	private int x,y;
	
	public Tile(int x, int y, BufferedImage sprite)
	{
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics gfx)
	{
		gfx.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
}
