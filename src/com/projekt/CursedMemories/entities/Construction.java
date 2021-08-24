package com.projekt.CursedMemories.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Construction extends Entity{

	private BufferedImage sprite;
	private int IDmap;
	
	public Construction(int x, int y, int w, int h, BufferedImage sprite, int IDmap) {
		super(x, y, w, h, sprite);
		this.sprite = sprite;
		this.IDmap = IDmap;
	}
	
	public static boolean isColliding(Entity e2, int w, int h)
    {
    	Rectangle rectE1 = new Rectangle(Game.player.getX(), Game.player.getY(), World.TILE_SIZE, World.TILE_SIZE);
    	Rectangle rectE2 = new Rectangle(e2.getX(), e2.getY() + 50, w, h);
    	return rectE1.intersects(rectE2);
    }

	public void render(Graphics gfx) {
		gfx.drawImage(this.getSprite(), this.getX() - Camera.x, this.getY() - Camera.y, null);
		//gfx.setColor(Color.RED);
		//gfx.drawRect(this.getX() - Camera.x, this.getY() + 50 - Camera.y, this.getW(), this.getH() - 50);
	}

	@SuppressWarnings("static-access")
	public void tick() {
		if(this.IDmap == 1) {
			if(this.isColliding(this, this.getW(), this.getH() - 50)) {	
				if(Game.boss_fire_kill) {
					Game.player.setY(Game.player.getY() + 10);
					return;
				}
				Game.mapName = "/map_vulcao.png";
				World.setLevel(Game.mapName, true);
			}
		} else if(this.IDmap == 2) {
			if(this.isColliding(this, this.getW(), this.getH() - 50)) {
				System.out.println("Colidindo templo");
			}
		}
	}
}