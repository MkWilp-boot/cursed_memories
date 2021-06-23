package com.xesque.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import com.xesque.main.Game;
import com.xesque.world.Camera;
import com.xesque.world.Node;
import com.xesque.world.Vector2i;
import com.xesque.world.World;

public class Entity {
    public int x, y, w, h;
    protected List<Node> path;
    
    public static BufferedImage HEART_FULL = Game.spritesheet.getSprite(320, 128, 32, 32);
    public static BufferedImage HEART_HALF = Game.spritesheet.getSprite(352, 128, 32, 32);
    public static BufferedImage HEART_EMPTY = Game.spritesheet.getSprite(384, 128, 32, 32);
    
    public static BufferedImage LIFE_PACK_ENT =  Game.spritesheet.getSprite(0, 32, 32, 32);
    public static BufferedImage AMMO_PACK_ENT =  Game.spritesheet.getSprite(64, 32, 32, 32);
    public static BufferedImage ENEMY_ENT =  Game.spritesheet.getSprite(96, 32, 32, 32);
    
    // WEAPON declaration
    
    public static BufferedImage WEAPON_ENT_RIFLE_NON_AUTO =  Game.spritesheet.getSprite(32, 32, 32, 32);
    public static BufferedImage GUN_LEFT =  Game.spritesheet.getSprite(256, 32, 32, 32);
    public static BufferedImage GUN_RIGHT =  Game.spritesheet.getSprite(224, 32, 32, 32);
    
    public static BufferedImage WEAPON_ENT_SHOTGUN =  Game.spritesheet.getSprite(256, 128, 32, 32);
    public static BufferedImage GUN_SHOTGUN_LEFT = Game.spritesheet.getSprite(288, 128, 32, 32);
    public static BufferedImage GUN_SHOTGUN_RIGHT = Game.spritesheet.getSprite(256, 128, 32, 32);
    
    // End
    
    public static BufferedImage PORTAL = Game.spritesheet.getSprite(320, 0, 64, 64);
    
    public static BufferedImage PORTAL_01 = Game.spritesheet.getSprite(416, 0, 64, 103);
    
    // SAVE GAME
    public static BufferedImage SAVE_BEAM = Game.spritesheet.getSprite(384, 0, 32, 64);
    
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
    
    public void followPath(List<Node> path)
    {
    	if(path != null)
    	{
    		if(path.size() > 0)
    		{
    			Vector2i target = path.get(path.size() - 1).tile;
    			if(x < target.x * World.TILE_SIZE)
    			{
    				x++;
    			}
    			else if(x > target.x * World.TILE_SIZE)
    			{
    				x--;
    			}
    			
    			if(y < target.y * World.TILE_SIZE)
    			{
    				y++;
    			}
    			else if(y > target.y * World.TILE_SIZE)
    			{
    				y--;
    			}
    			
    			// caso ja tenha chegado
    			if(x == target.x * World.TILE_SIZE && y == target.y * World.TILE_SIZE)
    			{
    				path.remove(path.size() - 1);
    			}
    		}
    	}
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
    
    public double calculateDistance(int x1, int y1, int x2, int y2){
    	return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
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