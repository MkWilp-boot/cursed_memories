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
	
	public static final BufferedImage TILE_WALL_LAVA01 = Game.spr_vulcao.getSprite(32, 96, 32, 32);
	public static final BufferedImage TILE_FLOOR_BRICK = Game.spr_vulcao.getSprite(128, 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_DRY_LAVA = Game.spr_vulcao.getSprite(128, 0, 32, 32);
	public static final BufferedImage TILE_WALL_LAVA_RED = Game.spr_vulcao.getSprite(32, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_BRICK_RED = Game.spr_vulcao.getSprite(128, 96, 32, 32);
	public static final BufferedImage TILE_FLOOR_BRICK_RED_TOP = Game.spr_vulcao.getSprite(96, 96, 32, 32);
	
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
	
	// LOBBY / HUB
	public static final BufferedImage TILE_WALL_A1 = Game.spr_hub.getSprite(0, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A2 = Game.spr_hub.getSprite(320, 224, 32, 32);
	public static final BufferedImage TILE_FLOOR_A20 = Game.spr_hub.getSprite(288, 224, 32, 32);
	public static final BufferedImage TILE_FLOOR_A21 = Game.spr_hub.getSprite(320, 224, 32, 32);
	public static final BufferedImage TILE_FLOOR_A22 = Game.spr_hub.getSprite(352, 160, 32, 32);
	public static final BufferedImage TILE_FLOOR_A23 = Game.spr_hub.getSprite(192, 256, 32, 32);
	public static final BufferedImage TILE_FLOOR_A24 = Game.spr_hub.getSprite(256, 160, 32, 32);
	
	// ponte horizontal
	public static final BufferedImage TILE_FLOOR_A25 = Game.spr_hub.getSprite(256, 192, 32, 32);
	public static final BufferedImage TILE_FLOOR_A26 = Game.spr_hub.getSprite(224, 256, 32, 32);
	public static final BufferedImage TILE_FLOOR_A27 = Game.spr_hub.getSprite(256, 256, 32, 32);
	public static final BufferedImage TILE_FLOOR_A28 = Game.spr_hub.getSprite(288, 256, 32, 32);
	public static final BufferedImage TILE_FLOOR_A29 = Game.spr_hub.getSprite(352, 192, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_A30 = Game.spr_hub.getSprite(256, 224, 32, 32);
	public static final BufferedImage TILE_FLOOR_A31 = Game.spr_hub.getSprite(224, 288, 32, 32);
	public static final BufferedImage TILE_FLOOR_A32 = Game.spr_hub.getSprite(256, 288, 32, 32);
	public static final BufferedImage TILE_FLOOR_A33 = Game.spr_hub.getSprite(288, 288, 32, 32);
	public static final BufferedImage TILE_FLOOR_A34 = Game.spr_hub.getSprite(352, 224, 32, 32);
	
	
	public static final BufferedImage TILE_FLOOR_A3 = Game.spr_hub.getSprite(64, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A4 = Game.spr_hub.getSprite(96, 0, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_AA = Game.spr_hub.getSprite(416, 96, 32, 32);
	public static final BufferedImage TILE_FLOOR_AB = Game.spr_hub.getSprite(416, 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_AC = Game.spr_hub.getSprite(448, 64, 32, 32);
	
	public static final BufferedImage TILE_WALL_AD = Game.spr_hub.getSprite(448, 96, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_AE = Game.spr_hub.getSprite(64,  256, 32, 32);
	public static final BufferedImage TILE_FLOOR_AF = Game.spr_hub.getSprite(128, 256, 32, 32);
	public static final BufferedImage TILE_FLOOR_AG = Game.spr_hub.getSprite(96,  256, 32, 32);
	public static final BufferedImage TILE_FLOOR_AH = Game.spr_hub.getSprite(160, 256, 32, 32);
	public static final BufferedImage TILE_FLOOR_AI = Game.spr_hub.getSprite(448, 96, 32, 32);
	
	
	public static final BufferedImage TILE_FLOOR_A5 = Game.spr_hub.getSprite(128, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A6 = Game.spr_hub.getSprite(160, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A7 = Game.spr_hub.getSprite(192, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A8 = Game.spr_hub.getSprite(224, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A9 = Game.spr_hub.getSprite(256, 0, 32, 32);
	public static final BufferedImage TILE_WALL_A10 = Game.spr_hub.getSprite(288, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A11 = Game.spr_hub.getSprite(320, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A12 = Game.spr_hub.getSprite(352, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A13 = Game.spr_hub.getSprite(384, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A14 = Game.spr_hub.getSprite(416, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_A15 = Game.spr_hub.getSprite(448, 0, 32, 32);
	public static final BufferedImage TILE_WALL_A16 = Game.spr_hub.getSprite(480, 0, 32, 32);
	
	
	
	public static final BufferedImage TILE_WALL_B1 = Game.spr_hub.getSprite(0, 32, 32, 32);
	public static final BufferedImage TILE_WALL_B2 = Game.spr_hub.getSprite(32, 32, 32, 32);
	public static final BufferedImage TILE_WALL_C1 = Game.spr_hub.getSprite(0, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C2 = Game.spr_hub.getSprite(32 , 64, 32, 32);
	public static final BufferedImage TILE_WALL_D1 = Game.spr_hub.getSprite(0, 96, 32, 32);
	public static final BufferedImage TILE_WALL_D2 = Game.spr_hub.getSprite(32, 96 , 32, 32);
	
	public static final BufferedImage TILE_WALL_B3 = Game.spr_hub.getSprite(64, 32, 32, 32);
	public static final BufferedImage TILE_WALL_C3 = Game.spr_hub.getSprite(64, 64, 32, 32);
	public static final BufferedImage TILE_WALL_B4 = Game.spr_hub.getSprite(96, 32, 32, 32);
	public static final BufferedImage TILE_WALL_C4 = Game.spr_hub.getSprite(96, 64, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_B5 = Game.spr_hub.getSprite(128, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_B6 = Game.spr_hub.getSprite(160, 32, 32, 32);
	public static final BufferedImage TILE_WALL_B7 = Game.spr_hub.getSprite(192, 32, 32, 32);
	public static final BufferedImage TILE_WALL_B8 = Game.spr_hub.getSprite(224, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_B9 = Game.spr_hub.getSprite(256, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_B10  = Game.spr_hub.getSprite(288, 32, 32, 32);
	
	
	
	public static final BufferedImage TILE_FLOOR_B11 = Game.spr_hub.getSprite(320 , 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_B12 = Game.spr_hub.getSprite(352, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_B13 = Game.spr_hub.getSprite(384, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_B14 = Game.spr_hub.getSprite(416, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_B15 = Game.spr_hub.getSprite(448, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_B16 = Game.spr_hub.getSprite(480, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_C5 = Game.spr_hub.getSprite(128 , 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_C6 = Game.spr_hub.getSprite(160, 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_C7 = Game.spr_hub.getSprite(192, 64, 32, 32);
	public static final BufferedImage TILE_FLOOR_D3 = Game.spr_hub.getSprite(64, 96, 32, 32);
	public static final BufferedImage TILE_FLOOR_D4 = Game.spr_hub.getSprite(96, 96, 32, 32);
	public static final BufferedImage TILE_FLOOR_D5 = Game.spr_hub.getSprite(128, 96, 32, 32);
	public static final BufferedImage TILE_FLOOR_D6 = Game.spr_hub.getSprite(160, 96, 32, 32);
	
	
	
	
	public static final BufferedImage TILE_WALL_C8 = Game.spr_hub.getSprite(224, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C9 = Game.spr_hub.getSprite(256, 64, 32, 32);
	public static final BufferedImage TILE_WALL_D8 = Game.spr_hub.getSprite(224, 96, 32, 32);
	public static final BufferedImage TILE_WALL_D9 = Game.spr_hub.getSprite(256, 96, 32, 32);
	public static final BufferedImage TILE_WALL_C10 = Game.spr_hub.getSprite(288, 64, 32, 32);
	public static final BufferedImage TILE_WALL_D10 = Game.spr_hub.getSprite(288, 96, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_D7 = Game.spr_hub.getSprite(192, 96, 32, 32);
	public static final BufferedImage TILE_FLOOR_C11 = Game.spr_hub.getSprite(320, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C12 = Game.spr_hub.getSprite(352, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C13 = Game.spr_hub.getSprite(384, 64, 32, 32);
	public static final BufferedImage TILE_WALL_D11 = Game.spr_hub.getSprite(320, 96, 32, 32);
	public static final BufferedImage TILE_FLOOR_D12 = Game.spr_hub.getSprite(352, 96, 32, 32);
	public static final BufferedImage TILE_WALL_D13 = Game.spr_hub.getSprite(384, 96, 32, 32);
	 
	
	
	public static final BufferedImage TILE_WALL_E1 = Game.spr_hub.getSprite(0, 128, 32, 32);
	public static final BufferedImage TILE_WALL_E2 = Game.spr_hub.getSprite(32, 128 , 32, 32);
	public static final BufferedImage TILE_WALL_F1 = Game.spr_hub.getSprite(0, 160, 32, 32);
	public static final BufferedImage TILE_WALL_F2 = Game.spr_hub.getSprite(32, 160, 32, 32);
	
	public static final BufferedImage TILE_WALL_E3 = Game.spr_hub.getSprite(64, 128, 32, 32);
	public static final BufferedImage TILE_WALL_E4 = Game.spr_hub.getSprite(96 , 128, 32, 32);
	public static final BufferedImage TILE_WALL_F3 = Game.spr_hub.getSprite(64, 160, 32, 32);
	public static final BufferedImage TILE_WALL_F4 = Game.spr_hub.getSprite(96, 160, 32, 32);
	
	
	public static final BufferedImage TILE_WALL_E5 = Game.spr_hub.getSprite(128, 128, 32, 32);
	public static final BufferedImage TILE_WALL_E6 = Game.spr_hub.getSprite(160, 128 , 32, 32);
	public static final BufferedImage TILE_WALL_F5 = Game.spr_hub.getSprite(128, 160, 32, 32);
	public static final BufferedImage TILE_WALL_F6 = Game.spr_hub.getSprite(160, 160, 32, 32);
	
	public static final BufferedImage TILE_WALL_E7 = Game.spr_hub.getSprite(192, 128, 32, 32);
	public static final BufferedImage TILE_WALL_E8 = Game.spr_hub.getSprite(224, 128, 32, 32);
	public static final BufferedImage TILE_WALL_F7 = Game.spr_hub.getSprite(192, 160, 32, 32);
	public static final BufferedImage TILE_WALL_F8 = Game.spr_hub.getSprite(224, 160, 32, 32);
	
	
	public static final BufferedImage TILE_FLOOR_E9 = Game.spr_hub.getSprite(256, 128, 32, 32);
	public static final BufferedImage TILE_FLOOR_E10 = Game.spr_hub.getSprite(288, 128, 32, 32);
	public static final BufferedImage TILE_FLOOR_E11 = Game.spr_hub.getSprite(320, 128, 32, 32);
	public static final BufferedImage TILE_FLOOR_E12 = Game.spr_hub.getSprite(352, 128, 32, 32);
	public static final BufferedImage TILE_FLOOR_E13 = Game.spr_hub.getSprite(384, 128, 32, 32);
	public static final BufferedImage TILE_FLOOR_E14 = Game.spr_hub.getSprite(416, 128, 32, 32);
	
	
	public static final BufferedImage TILE_WALL_G1 = Game.spr_hub.getSprite(0, 192, 32, 32);
	public static final BufferedImage TILE_WALL_G2 = Game.spr_hub.getSprite(32, 192 , 32, 32);
	public static final BufferedImage TILE_WALL_H2 = Game.spr_hub.getSprite(32, 224, 32, 32);
	public static final BufferedImage TILE_WALL_G3 = Game.spr_hub.getSprite(64, 192, 32, 32);
	public static final BufferedImage TILE_WALL_G4 = Game.spr_hub.getSprite(96, 192, 32, 32);
	public static final BufferedImage TILE_WALL_H4 = Game.spr_hub.getSprite(96, 224, 32, 32);
	public static final BufferedImage TILE_WALL_G5 = Game.spr_hub.getSprite(128, 192, 32, 32);
	public static final BufferedImage TILE_WALL_G6 = Game.spr_hub.getSprite(160, 192, 32, 32);
	public static final BufferedImage TILE_FLOOR_G7 = Game.spr_hub.getSprite(192, 192, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_G8 = Game.spr_hub.getSprite(224, 192, 32, 32);
	public static final BufferedImage TILE_FLOOR_G9 = Game.spr_hub.getSprite(256, 192, 32, 32);
	
	public static final BufferedImage TILE_FLOOR_LAVA_SECA = Game.spr_hub.getSprite(480, 64, 32, 32);
	
	//Fim mapa lobby
	
	// MAPA CAVEIRA
	
	public static final BufferedImage TILE_FLOOR_C_A1 = Game.spr_caveira.getSprite(0, 0, 32, 32);
	public static final BufferedImage TILE_WALL_C_A2 = Game.spr_caveira.getSprite(32, 0, 32, 32);
	public static final BufferedImage TILE_WALL_C_A3 = Game.spr_caveira.getSprite(64, 0, 32, 32);
	public static final BufferedImage TILE_WALL_C_A4 = Game.spr_caveira.getSprite(96, 0, 32, 32);
	public static final BufferedImage TILE_WALL_C_A5 = Game.spr_caveira.getSprite(128, 0, 32, 32);
	
	
	public static final BufferedImage TILE_WALL_C_B1 = Game.spr_caveira.getSprite(0, 32, 32, 32);
	public static final BufferedImage TILE_WALL_C_B2 = Game.spr_caveira.getSprite(32, 32, 32, 32);
	public static final BufferedImage TILE_WALL_C_B3 = Game.spr_caveira.getSprite(64, 32, 32, 32);
	public static final BufferedImage TILE_WALL_C_C1 = Game.spr_caveira.getSprite(0, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C_C2 = Game.spr_caveira.getSprite(32, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C_C3 = Game.spr_caveira.getSprite(64, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C_D1 = Game.spr_caveira.getSprite(0, 96, 32, 32);
	public static final BufferedImage TILE_WALL_C_D2 = Game.spr_caveira.getSprite(32, 96, 32, 32);
	public static final BufferedImage TILE_WALL_C_D3 = Game.spr_caveira.getSprite(64, 96, 32, 32);
	
	
	public static final BufferedImage TILE_WALL_C_B4 = Game.spr_caveira.getSprite(96, 32, 32, 32);
	public static final BufferedImage TILE_WALL_C_B5 = Game.spr_caveira.getSprite(128, 32, 32, 32);
	public static final BufferedImage TILE_WALL_C_C4 = Game.spr_caveira.getSprite(96, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C_C5 = Game.spr_caveira.getSprite(128, 64, 32, 32);
	public static final BufferedImage TILE_WALL_C_D4 = Game.spr_caveira.getSprite(96, 96, 32, 32);
	public static final BufferedImage TILE_WALL_C_D5 = Game.spr_caveira.getSprite(128, 96, 32, 32);
	
	
	public static final BufferedImage TILE_FLOOR_C_E1 = Game.spr_caveira.getSprite(0, 128, 32, 32);
	public static final BufferedImage TILE_FLOOR_C_E2 = Game.spr_caveira.getSprite(32, 128, 32, 32);
	public static final BufferedImage TILE_FLOOR_C_E3 = Game.spr_caveira.getSprite(64, 128, 32, 32);
	public static final BufferedImage TILE_FLOOR_C_E4 = Game.spr_caveira.getSprite(96, 128, 32, 32);
	
	// FIM MAPA CAVEIRA
	
	// MAPA RELOGIO
	
	public static final BufferedImage TILE_FLOOR_R_A1 = Game.spr_relogio.getSprite(0, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_A2 = Game.spr_relogio.getSprite(32, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_A3 = Game.spr_relogio.getSprite(64, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_A4 = Game.spr_relogio.getSprite(96, 0, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_A5 = Game.spr_relogio.getSprite(128, 0, 32, 32);
	public static final BufferedImage TILE_WALL_R_A6 = Game.spr_relogio.getSprite(160, 0, 32, 32);
	
	
	public static final BufferedImage TILE_FLOOR_R_B1 = Game.spr_relogio.getSprite(0, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_B2 = Game.spr_relogio.getSprite(32, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_B3 = Game.spr_relogio.getSprite(64, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_B4 = Game.spr_relogio.getSprite(96, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_B5 = Game.spr_relogio.getSprite(128, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_B6 = Game.spr_relogio.getSprite(160, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_B7 = Game.spr_relogio.getSprite(192, 32, 32, 32);
	public static final BufferedImage TILE_FLOOR_R_B8 = Game.spr_relogio.getSprite(224, 32, 32, 32);
		
		// FIM MAPA RELOGIO
	
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
