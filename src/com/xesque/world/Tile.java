package com.xesque.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;

public class Tile {
	// Provavel mapa, citado em World.java
	/*
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
	*/
	// MAP_1
	// FLOOR
	public static BufferedImage TILE_FLOOR_M_1_0 = Game.spritesheet.getSprite(0, 256, 32, 32);
	public static BufferedImage TILE_FLOOR_M_1_1 = Game.spritesheet.getSprite(32, 256, 32, 32);
	
	// MAP_1
	// WALL
	public static BufferedImage TILE_WALL_M_1_0 = Game.spritesheet.getSprite(0, 288, 32, 32);
	public static BufferedImage TILE_WALL_M_1_1 = Game.spritesheet.getSprite(0, 320, 32, 32);
	
	// Complemento esquerda do mapa
	public static BufferedImage TILE_WALL_M_1_2 = Game.spritesheet.getSprite(32, 288, 32, 32);
	
	// Complemento direita do mapa
	public static BufferedImage TILE_WALL_M_1_12 = Game.spritesheet.getSprite(64, 288, 32, 32);
	// :(
	public static BufferedImage TILE_WALL_M_1_13 = Game.spritesheet.getSprite(32, 512, 32, 32);
	// :(
	public static BufferedImage TILE_WALL_M_1_14 = Game.spritesheet.getSprite(64, 512, 32, 32);
	
	// Pilares virados para a direita
	// Cima
	public static BufferedImage TILE_WALL_M_1_3 = Game.spritesheet.getSprite(0, 352, 32, 32);
	// Inferior
	public static BufferedImage TILE_WALL_M_1_4 = Game.spritesheet.getSprite(0, 384, 32, 32);
	// Entre os pilares
	public static BufferedImage TILE_WALL_M_1_5 = Game.spritesheet.getSprite(0, 416, 32, 32);
	
	// Pilares parte superior
	// Cima
	public static BufferedImage TILE_WALL_M_1_6 = Game.spritesheet.getSprite(64, 352, 32, 32);
	// Inferior
	public static BufferedImage TILE_WALL_M_1_7 = Game.spritesheet.getSprite(32, 352, 32, 32);
	// Entre
	public static BufferedImage TILE_WALL_M_1_8 = Game.spritesheet.getSprite(96, 352, 32, 32);
	
	// Pilares virados para a esquerda
	// Cima
	public static BufferedImage TILE_WALL_M_1_9 = Game.spritesheet.getSprite(0, 448, 32, 32);
	// Inferior
	public static BufferedImage TILE_WALL_M_1_10 = Game.spritesheet.getSprite(0, 480, 32, 32);
	// Entre os pilares
	public static BufferedImage TILE_WALL_M_1_11 = Game.spritesheet.getSprite(0, 512, 32, 32);
	
	// Parte inferior do mapa
	public static BufferedImage TILE_WALL_M_1_15 = Game.spritesheet.getSprite(64, 320, 32, 32);
	public static BufferedImage TILE_WALL_M_1_16 = Game.spritesheet.getSprite(96, 320, 32, 32);
	// :(
	public static BufferedImage TILE_WALL_M_1_17 = Game.spritesheet.getSprite(128, 320, 32, 32);
	
	
	// Fim MAP_1
	
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
