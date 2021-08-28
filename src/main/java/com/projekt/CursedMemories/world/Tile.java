package com.projekt.CursedMemories.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;

public class Tile {
	// Mapa Vulcao
    public static final BufferedImage TILE_FLOOR_VULCAO_DIRT = Game.spr_vulcao.getSprite(32, 0, 32, 32);
    public static final BufferedImage TILE_FLOOR_VULCAO_LAVADIRT = Game.spr_vulcao.getSprite(96, 0, 32, 32);
    public static final BufferedImage TILE_WALL_VULCAO_LAVA = Game.spr_vulcao.getSprite(0, 0, 32, 32);
    public static final BufferedImage TILE_WALL_VULCAO_LAVAROCK = Game.spr_vulcao.getSprite(64, 0, 32, 32);
	
	public static final BufferedImage TILE_WALL_RIGHT = Game.spritesheet.getSprite(160, 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_GENERIC = Game.spritesheet.getSprite(96, 64, 32, 32);
	
	// MAP_0
	public static final BufferedImage TILE_FLOOR_M_0_0 = Game.spr_map0.getSprite(0, 224, 32, 32);
	// Gate Tiles
	public static final BufferedImage TILE_GATE_M_0_0 = Game.spr_map0.getSprite(0, 0, 32, 32);
	public static final BufferedImage TILE_GATE_M_0_1 = Game.spr_map0.getSprite(32, 0, 32, 32);
	public static final BufferedImage TILE_GATE_M_0_2 = Game.spr_map0.getSprite(64, 0, 32, 32);

	public static final BufferedImage TILE_GATE_M_0_3 = Game.spr_map0.getSprite(0, 32, 32, 32);
	public static final BufferedImage TILE_GATE_M_0_4 = Game.spr_map0.getSprite(32, 32, 32, 32);
	public static final BufferedImage TILE_GATE_M_0_5 = Game.spr_map0.getSprite(64, 32, 32, 32);
	
	public static final BufferedImage TILE_GATE_M_0_6 = Game.spr_map0.getSprite(96, 0, 32, 32);
	public static final BufferedImage TILE_GATE_M_0_7 = Game.spr_map0.getSprite(128, 0, 32, 32);
		
	// Floor Tiles
	public static final BufferedImage TILE_FLOOR_M_0_1 = Game.spr_map0.getSprite(0, 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_0_2 = Game.spr_map0.getSprite(32, 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_0_3 = Game.spr_map0.getSprite(64, 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_0_4 = Game.spr_map0.getSprite(96, 64, 32, 32);
	
	// Main stairs
	public static final BufferedImage TILE_FLOOR_STAIR_S0 = Game.spr_map0.getSprite(160, 480, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S1 = Game.spr_map0.getSprite(192, 480, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S2 = Game.spr_map0.getSprite(224, 480, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_STAIR_S3 = Game.spr_map0.getSprite(160, 448, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S4 = Game.spr_map0.getSprite(192, 448, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S5 = Game.spr_map0.getSprite(224, 448, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_STAIR_S6 = Game.spr_map0.getSprite(256, 480, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S7 = Game.spr_map0.getSprite(288, 480, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S8 = Game.spr_map0.getSprite(320, 480, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_STAIR_S9 = Game.spr_map0.getSprite(256, 448, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S10 = Game.spr_map0.getSprite(288, 448, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S11 = Game.spr_map0.getSprite(320, 448, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_STAIR_S12 = Game.spr_map0.getSprite(352, 448, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S13 = Game.spr_map0.getSprite(384, 448, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S14 = Game.spr_map0.getSprite(416, 448, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_STAIR_S15 = Game.spr_map0.getSprite(352, 480, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S16 = Game.spr_map0.getSprite(384, 480, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S17 = Game.spr_map0.getSprite(416, 480, 32, 32);
	
	
	public static final BufferedImage TILE_FLOOR_STAIR_S18 = Game.spr_map0.getSprite(352, 384, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S19 = Game.spr_map0.getSprite(384, 384, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S20 = Game.spr_map0.getSprite(416, 384, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_STAIR_S21 = Game.spr_map0.getSprite(352, 416, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S22 = Game.spr_map0.getSprite(384, 416, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S23 = Game.spr_map0.getSprite(416, 416, 32, 32);
	
	
	public static final BufferedImage TILE_FLOOR_STAIR_S24 = Game.spr_map0.getSprite(256, 384, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S25 = Game.spr_map0.getSprite(288, 384, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S26 = Game.spr_map0.getSprite(320, 384, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_STAIR_S27 = Game.spr_map0.getSprite(256, 416, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S28 = Game.spr_map0.getSprite(288, 416, 32, 32);
	public static final BufferedImage TILE_FLOOR_STAIR_S29 = Game.spr_map0.getSprite(320, 416, 32, 32);
	// Fim Floor Tiles
	
	
	// Wall Tiles
	public static final BufferedImage TILE_WALL_M_0_BLOCK = Game.spr_map0.getSprite(128, 64, 32, 32);
	public static final BufferedImage TILE_WALL_M_0_BOTTOM = Game.spr_map0.getSprite(128, 32, 32, 32);
	public static final BufferedImage TILE_WALL_M_0_RIGHT = Game.spr_map0.getSprite(192, 0, 32, 32);
	public static final BufferedImage TILE_WALL_M_0_TOP = Game.spr_map0.getSprite(96, 32, 32, 32);
	public static final BufferedImage TILE_WALL_M_0_LEFT = Game.spr_map0.getSprite(160, 0, 32, 32);
	
	public static final BufferedImage TILE_WALL_M_0_BLACK = Game.spr_map0.getSprite(0, 224, 32, 32);
	
	public static final BufferedImage TILE_WALL_LION_TOP_LEFT = Game.spr_map0.getSprite(0, 416, 32, 32);
	public static final BufferedImage TILE_WALL_LION_TOP_RIGHT = Game.spr_map0.getSprite(32, 416, 32, 32);
	public static final BufferedImage TILE_WALL_LION_MIDDLE_LEFT = Game.spr_map0.getSprite(0, 448, 32, 32);
	public static final BufferedImage TILE_WALL_LION_MIDDLE_RIGHT = Game.spr_map0.getSprite(32, 448, 32, 32);
	public static final BufferedImage TILE_WALL_LION_BOTTOM_LEFT = Game.spr_map0.getSprite(0, 480, 32, 32);
	public static final BufferedImage TILE_WALL_LION_BOTTOM_RIGHT = Game.spr_map0.getSprite(32, 480, 32, 32);
	
	public static final BufferedImage TILE_WALL_STAIR_BOX_TOP_LEFT = Game.spr_map0.getSprite(64, 448, 32, 32);
	public static final BufferedImage TILE_WALL_STAIR_BOX_TOP_CENTER = Game.spr_map0.getSprite(96, 448, 32, 32);
	public static final BufferedImage TILE_WALL_STAIR_BOX_TOP_RIGHT = Game.spr_map0.getSprite(128, 448, 32, 32);
	
	public static final BufferedImage TILE_WALL_STAIR_BOX_BOTTOM_LEFT = Game.spr_map0.getSprite(64, 480, 32, 32);
	public static final BufferedImage TILE_WALL_STAIR_BOX_BOTTOM_CENTER = Game.spr_map0.getSprite(96, 480, 32, 32);
	public static final BufferedImage TILE_WALL_STAIR_BOX_BOTTOM_RIGHT = Game.spr_map0.getSprite(128, 480, 32, 32);
	
	
	// Fim Wall Tiles
	
	// Fim MAP_0
	
	
	public static final BufferedImage TILE_WALL = Game.spritesheet.getSprite(32, 0, 32, 32);
	// MAP_1
	// FLOOR
	public static final BufferedImage TILE_FLOOR_M_1_0 = Game.spritesheet.getSprite(0, 256, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_M_1_1 = Game.spritesheet.getSprite(32, 384, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_1_19 = Game.spritesheet.getSprite(64, 384, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_1_20 = Game.spritesheet.getSprite(96, 384, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_1_21 = Game.spritesheet.getSprite(32, 416, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_1_22 = Game.spritesheet.getSprite(64, 416, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_1_23 = Game.spritesheet.getSprite(96, 416, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_1_24 = Game.spritesheet.getSprite(32, 448, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_1_25 = Game.spritesheet.getSprite(64, 448, 32, 32);
	public static final BufferedImage TILE_FLOOR_M_1_26 = Game.spritesheet.getSprite(96, 448, 32, 32);
	
	// MAP_1
	// WALL
	public static final BufferedImage TILE_WALL_M_1_0 = Game.spritesheet.getSprite(0, 288, 32, 32);
	public static final BufferedImage TILE_WALL_M_1_1 = Game.spritesheet.getSprite(0, 320, 32, 32);
	
	// Complemento esquerda do mapa
	public static final BufferedImage TILE_WALL_M_1_2 = Game.spritesheet.getSprite(32, 288, 32, 32);
	
	// Complemento direita do mapa
	public static final BufferedImage TILE_WALL_M_1_12 = Game.spritesheet.getSprite(64, 288, 32, 32);
	// :(
	public static final BufferedImage TILE_WALL_M_1_13 = Game.spritesheet.getSprite(32, 512, 32, 32);
	// :(
	public static final BufferedImage TILE_WALL_M_1_14 = Game.spritesheet.getSprite(64, 512, 32, 32);
	
	// Pilares virados para a direita
	// Cima
	public static final BufferedImage TILE_WALL_M_1_3 = Game.spritesheet.getSprite(0, 352, 32, 32);
	// Inferior
	public static final BufferedImage TILE_WALL_M_1_4 = Game.spritesheet.getSprite(0, 384, 32, 32);
	// Entre os pilares
	public static final BufferedImage TILE_WALL_M_1_5 = Game.spritesheet.getSprite(0, 416, 32, 32);
	
	// Pilares parte superior
	// Cima
	public static final BufferedImage TILE_WALL_M_1_6 = Game.spritesheet.getSprite(64, 352, 32, 32);
	// Inferior
	public static final BufferedImage TILE_WALL_M_1_7 = Game.spritesheet.getSprite(32, 352, 32, 32);
	// Entre
	public static final BufferedImage TILE_WALL_M_1_8 = Game.spritesheet.getSprite(96, 352, 32, 32);
	
	// Pilares virados para a esquerda
	// Cima
	public static final BufferedImage TILE_WALL_M_1_9 = Game.spritesheet.getSprite(0, 448, 32, 32);
	// Inferior
	public static final BufferedImage TILE_WALL_M_1_10 = Game.spritesheet.getSprite(0, 480, 32, 32);
	// Entre os pilares
	public static final BufferedImage TILE_WALL_M_1_11 = Game.spritesheet.getSprite(0, 512, 32, 32);
	
	// Parte inferior do mapa
	public static final BufferedImage TILE_WALL_M_1_15 = Game.spritesheet.getSprite(64, 320, 32, 32);
	public static final BufferedImage TILE_WALL_M_1_16 = Game.spritesheet.getSprite(96, 320, 32, 32);
	// :(
	public static final BufferedImage TILE_WALL_M_1_17 = Game.spritesheet.getSprite(128, 320, 32, 32);
	public static final BufferedImage TILE_WALL_M_1_18 = Game.spritesheet.getSprite(160, 320, 32, 32);
	
	
	// Fim MAP_1
	
	
	// Mapa lobby
	public static final BufferedImage TILE_FLOOR_HUB_GRASS = Game.spr_hub.getSprite(32, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_HUB_DIRT = Game.spr_hub.getSprite(96, 64, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_WATER = Game.spr_hub.getSprite(0, 96, 32, 32);
	
	public static final BufferedImage TILE_WALL_HUB_GRASS_BOTTOM = Game.spr_hub.getSprite(32, 64, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_RIGHT = Game.spr_hub.getSprite(64, 32, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_TOP = Game.spr_hub.getSprite(32, 0, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_LEFT = Game.spr_hub.getSprite(0, 32, 32, 32);
	
	public static final BufferedImage TILE_WALL_HUB_GRASS_CORNER_RB = Game.spr_hub.getSprite(64, 64, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_CORNER_RT = Game.spr_hub.getSprite(64, 0, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_CORNER_LB = Game.spr_hub.getSprite(0, 64, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_CORNER_LT = Game.spr_hub.getSprite(0, 0, 32, 32);
	
	public static final BufferedImage TILE_WALL_HUB_GRASS_CORNER_IRB = Game.spr_hub.getSprite(128, 32, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_CORNER_IRT = Game.spr_hub.getSprite(128, 0, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_CORNER_ILB = Game.spr_hub.getSprite(96, 32, 32, 32);
	public static final BufferedImage TILE_WALL_HUB_GRASS_CORNER_ILT = Game.spr_hub.getSprite(96, 0, 32, 32);
	
	//Fim mapa lobby
	
	private BufferedImage sprite;
	
	private int x,y;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setTexture(BufferedImage img) {
		this.sprite = img;
	}
	
	public BufferedImage getTexture() {
		return this.sprite;
	}

	public Tile(int x, int y, BufferedImage sprite)
	{
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics gfx)
	{
		gfx.drawImage(this.sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
