package com.xesque.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.xesque.main.Game;
import com.xesque.main.Sound;
import com.xesque.world.Camera;
import com.xesque.world.World;

public class Player extends Entity {

    public boolean right, left, up, down;
    public double speed = 3.0;
    public int cur_dir = 0;
    /*
     * 0 Direita
     * 1 Cima
     * 2 Esquerda
     * 3 Baixo
     */
    public int life = 100, maxLife = 100;
    private int frames, index = 0, maxFrames = 5, maxIndex = 4, damageFrames = 0;
    public static boolean moved = false;
    public boolean hasWeapon = false, shoot = false, isDameged = false;
    private BufferedImage[] rightPlayer, leftPlayer;
    private BufferedImage defPlayerR, defPlayerL, defPlayer, damegedPlayer;
    public int ammo = 0, maxAmmo = 300, mx, my;

    public Player(int x, int y, int w, int h, BufferedImage sprite) {
        super(x, y, w, h, sprite);

        defPlayer  = Game.spritesheet.getSprite(65, 0, 32, 32); // 64, 0, 32, 32
        defPlayerR = Game.spritesheet.getSprite(65, 0, 32, 32); // 64, 0, 32, 32
        defPlayerL = Game.spritesheet.getSprite(224, 96, 32, 32); // 64, 0, 32, 32
        damegedPlayer = Game.spritesheet.getSprite(256, 0, 32, 32);

        rightPlayer = new BufferedImage[5];
        leftPlayer = new BufferedImage[5];

        rightPlayer[0] = Game.spritesheet.getSprite(65, 0, 32, 32); // 32 ,32
        rightPlayer[1] = Game.spritesheet.getSprite(96, 0, 32, 32);
        rightPlayer[2] = Game.spritesheet.getSprite(160, 0, 32, 32);
        rightPlayer[3] = Game.spritesheet.getSprite(192, 0, 32, 32);
        rightPlayer[4] = Game.spritesheet.getSprite(224, 0, 32, 32);

        leftPlayer[0] = Game.spritesheet.getSprite(65, 96, 32, 32); // 32 ,32
        leftPlayer[1] = Game.spritesheet.getSprite(96, 96, 32, 32);
        leftPlayer[2] = Game.spritesheet.getSprite(160, 96, 32, 32);
        leftPlayer[3] = Game.spritesheet.getSprite(192, 96, 32, 32);
        leftPlayer[4] = Game.spritesheet.getSprite(224, 96, 32, 32);
        
    }

    public void render(Graphics gfx) 
    {
    	
    	if(!isDameged)
    	{
    		if (!right && !left && !up && !down) 
    		{
                gfx.drawImage(defPlayer, (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
            }
            if (right) 
            {
            	cur_dir = 0;
                gfx.drawImage(rightPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
                defPlayer = defPlayerR;
            }
            else if (left)
            {
            	cur_dir = 2;
                gfx.drawImage(leftPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
                defPlayer = defPlayerL;
            }
            
            
            if(up && left) 
            {
            	cur_dir = 1;
            	gfx.drawImage(leftPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
            	defPlayer = defPlayerL;
            }
            else if(up && right)
            {
            	cur_dir = 1;
            	gfx.drawImage(rightPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
            	defPlayer = defPlayerR;
            }
            else if(up)
            {
            	cur_dir = 1;
            	if(defPlayer == defPlayerR)
         		   gfx.drawImage(rightPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
         	   else
         		   gfx.drawImage(leftPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
            }
            
           if(down && left) 
           {
        	   	cur_dir = 3;
           	 	gfx.drawImage(leftPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
           	 	defPlayer = defPlayerL;
           }
           else if(down && right)
           {    	   
        	   gfx.drawImage(rightPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
        	   defPlayer = defPlayerR;
           }
           else if(down)
           {
        	   cur_dir = 3;
        	   if(defPlayer == defPlayerR)
        		   gfx.drawImage(rightPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
        	   else
        		   gfx.drawImage(leftPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
           }
           if(hasWeapon)
           {
        	   double dangle = Math.atan2(Game.my - (this.getY() - Camera.y), Game.mx - (this.getX() - Camera.x));
        	   if(defPlayer == defPlayerR)
        	   {
        		   	//System.out.println(Math.toDegrees(dangle));
        		   	gfx.drawImage(rotate(Entity.GUN_RIGHT, Math.toDegrees(dangle)), this.getX() - Camera.x, this.getY() - Camera.y + 4, null);
        	   }
        	   else
        	   {
        		   	gfx.drawImage(rotate(Entity.GUN_RIGHT, Math.toDegrees(dangle)), this.getX() - Camera.x, this.getY() - Camera.y + 4, null);
        	   }
           }
    	}
    	else
    	{
    		gfx.drawImage(damegedPlayer, (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
    	}
    }
    
    public static BufferedImage rotate(BufferedImage bimg, double angle) {

        int w = bimg.getWidth();    
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());  
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }

    public void tick() {
        moved = false;

        if (right && World.isFree(this.getX() + (int)speed, this.getY())) {
            moved = true;
            x += speed;
        } else if (left && World.isFree(this.getX() - (int)speed, this.getY())) {
            moved = true;
            x -= speed;
        }

        if (up && World.isFree(this.getX(), this.getY() - (int)speed)) {
            moved = true;
            y -= speed;
        } else if (down && World.isFree(this.getX(), this.getY() + (int)speed)) {
            moved = true;
            y += speed;
        }

        if (moved) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if(index > maxIndex) {
                	index = 0;
                }
            }
        }
        this.checkCollisionLifePack();
        this.checkCollisionAmmoPack();
        this.checkCollisionGun();
        this.checkCollisionBullet();
        
        if(shoot && hasWeapon && ammo > 0)
        {
        	double angle = Math.atan2(my - (this.getY() + 16 - Camera.y), mx - (this.getX() + 16 - Camera.x));
        	
        	double dx = Math.cos(angle), dy = Math.sin(angle);
        	Bullet bullet = new Bullet(this.getX() + 16, this.getY() + 16, 8, 8, null, dx, dy, Color.yellow);
        	Game.bullets.add(bullet);
        	ammo--;
        	shoot = false;
        }
        else
        {
        	shoot = false;
        }
        
        if(isDameged)
        {
        	this.damageFrames++;
        	if(this.damageFrames == 8)
        	{
        		this.damageFrames = 0;
        		isDameged = false;
        	}
        }
        
        if(life <= 0)
        {
        	Game.GAME_STATE = 1; //Game Over
        	//World.restartGame(Game.mapName);
        }
        
        Camera.x = Camera.clamp(this.getX()  - (Game.WIDTH / 2), 0, World.WIDTH * 32 - Game.WIDTH);
        Camera.y = Camera.clamp(this.getY()  - (Game.HEIGHT / 2), 0, World.HEIGHT * 32 - Game.HEIGHT);
        
    }
    
    public void checkCollisionBullet()
	{
		for(int i = 0; i < Game.bulletsEn.size(); i++)
		{
			Entity e = Game.bulletsEn.get(i);
			if(Entity.isColliding(e, Game.player))
			{
				//System.out.println("Acertou");
				//life--;
				Sound.playerHurt.play();
				Game.bulletsEn.remove(i);
				return;
			}
		}
	}
    
    public void checkCollisionLifePack()
    {
    	for(int i = 0; i < Game.entities.size(); i++)
    	{
    		Entity e = Game.entities.get(i);
    		if(e instanceof LifePack)
    		{
    			if(Entity.isColliding(this, e))
    			{
    				if(life != maxLife)
    				{
    					life+=30;
        				if(life >= maxLife)
        					life = maxLife;
        				Game.entities.remove(i);
    				}
    				return;
    			}
    		}
    	}
    }
    
    public void checkCollisionGun()
    {
    	for(int i = 0; i < Game.entities.size(); i++)
    	{
    		Entity e = Game.entities.get(i);
    		if(e instanceof Weapon)
    		{
    			if(Entity.isColliding(this, e))
    			{
    				Weapon w = (Weapon) Game.entities.get(i);
    				hasWeapon = true;
    				Game.weapon.add(w);
        			Game.entities.remove(i);
    				
    				return;
    			}
    		}
    	}
    }
    
    public void checkCollisionAmmoPack()
    {
    	for(int i = 0; i < Game.entities.size(); i++)
    	{
    		Entity e = Game.entities.get(i);
    		if(e instanceof Ammo)
    		{
    			if(Entity.isColliding(this, e))
    			{
    				if(ammo != 300)
    				{
    					ammo = maxAmmo;
        				if(ammo >= 300)
        					ammo = 300;
        				Game.entities.remove(i);
    				}
    				return;
    			}
    		}
    	}
    }
}