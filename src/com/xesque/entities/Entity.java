package com.xesque.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.world.Camera;
import com.xesque.world.World;

public class Entity {
    protected int x, y, w, h;
    
    public static BufferedImage LIFE_PACK_ENT =  Game.spritesheet.getSprite(0, 32, 32, 32);
    public static BufferedImage WEAPON_ENT =  Game.spritesheet.getSprite(32, 32, 32, 32);
    public static BufferedImage AMMO_PACK_ENT =  Game.spritesheet.getSprite(64, 32, 32, 32);
    public static BufferedImage ENEMY_ENT =  Game.spritesheet.getSprite(96, 32, 32, 32);
    public static BufferedImage GUN_LEFT =  Game.spritesheet.getSprite(256, 32, 32, 32);
    public static BufferedImage GUN_RIGHT =  Game.spritesheet.getSprite(224, 32, 32, 32);
    public static BufferedImage PORTAL = Game.spritesheet.getSprite(320, 0, 64, 96);
    
    // Player face for text
    public static BufferedImage PLAYER_AVATAR =  Game.spritesheet.getSprite(0, 768, 32, 32);
    
    // STAGE 1, FIRE BOSS
    public static BufferedImage BOSS_01 = Game.spritesheet.getSprite(0, 128, 128, 128);
    public static BufferedImage BOSS_01_DAM = Game.spritesheet.getSprite(128, 128, 128, 128);
    
    
    private BufferedImage sprite;

    public Entity(int x, int y, int w, int h, BufferedImage sprite) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.sprite = sprite;
    }
    
    public static boolean isColliding(Entity e1, Entity e2)
    {
    	Rectangle rectE1 = new Rectangle(e1.getX(), e1.getY(), World.TILE_SIZE, World.TILE_SIZE);
    	Rectangle rectE2 = new Rectangle(e2.getX(), e2.getY(), World.TILE_SIZE, World.TILE_SIZE);
    	return rectE1.intersects(rectE2);
    }
    
    public static boolean isCollidingBoss(Entity e1, Entity e2)
    {
    	Rectangle rectE1 = new Rectangle(e1.getX(), e1.getY(), World.TILE_SIZE, World.TILE_SIZE);
    	Rectangle rectE2 = new Rectangle(e2.getX(), e2.getY(), World.TILE_SIZE_BOSS, World.TILE_SIZE_BOSS);
    	return rectE1.intersects(rectE2);
    }

    public void tick() {

    }

    public void render(Graphics gfx) {
        gfx.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }

    public int getH() {
        return this.h;
    }
    
    public BufferedImage getSprite()
    {
    	return this.sprite;
    }
}