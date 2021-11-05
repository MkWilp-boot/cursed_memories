package com.projekt.CursedMemories.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projekt.CursedMemories.main.Game;
import com.projekt.CursedMemories.main.ImageUtils;
import com.projekt.CursedMemories.main.Sound;
import com.projekt.CursedMemories.world.Camera;
import com.projekt.CursedMemories.world.World;

public class Player extends Entity {
	// 256, 192
    public boolean right, left, up, down;
    public double speed = 3.0;
    public int cur_dir = 0;
    private boolean keepShooting = false;
    private int goldAmount = 100; 
    private int life = 10;
	public int maxLife = 10;
	//varial de controle, para checar se o player pode tomar dano ou não, false = pode toma dano, true = não pode
	public boolean invulnerable = false;
	
    private int frames, index = 0, maxFrames = 5, maxIndex = 4, damageFrames = 0;
    private int idleFrames, idleIndex = 0;
    public static boolean moved = false;
    public boolean hasWeapon = false, shoot = false, isDameged = false;
    private BufferedImage[] rightPlayer, leftPlayer, idlePlayerR, idlePlayerL;
    private BufferedImage defPlayerR, defPlayerL, damegedPlayer, GUN_LEFT, GUN_RIGHT;
    private int ammo = 0;
	public int maxAmmo = 50;
	public int mx;
	public int my;
	public int cur_weapon = 0;
	public int max_weapon = 0;
	private int reserveAmmo = 0;
	private boolean reload;
	
	private boolean changeWeapon = false;
	private Integer removePlayerLife;
	private boolean IsWeaponCooldown = false;
	private int currentWeaponCooldown = 0;
	private int maxWeaponCooldown = 100;

    public Player(int x, int y, int w, int h, BufferedImage sprite) {
        super(x, y, w, h, sprite);
        this.setReload(false);
        
        defPlayerR = Game.spritesheet.getSprite(65, 0, 32, 32); // 64, 0, 32, 32
        defPlayerL = Game.spritesheet.getSprite(224, 96, 32, 32); // 64, 0, 32, 32
        damegedPlayer = Game.spritesheet.getSprite(256, 0, 32, 32);
        
     
        rightPlayer = new BufferedImage[4];
        leftPlayer = new BufferedImage[4];
        idlePlayerR = new BufferedImage[4];
        idlePlayerL = new BufferedImage[4];
        
        idlePlayerR[0] = Game.spritesheet.getSprite(256, 192, 32, 32);
        idlePlayerR[1] = Game.spritesheet.getSprite(288, 192, 32, 32);
        idlePlayerR[2] = Game.spritesheet.getSprite(320, 192, 32, 32);
        idlePlayerR[3] = Game.spritesheet.getSprite(352, 192, 32, 32);
        
        idlePlayerL[0] = Game.spritesheet.getSprite(256, 224, 32, 32);
        idlePlayerL[1] = Game.spritesheet.getSprite(288, 224, 32, 32);
        idlePlayerL[2] = Game.spritesheet.getSprite(320, 224, 32, 32);
        idlePlayerL[3] = Game.spritesheet.getSprite(352, 224, 32, 32);

        rightPlayer[0] = Game.spritesheet.getSprite(65, 0, 32, 32); 
        rightPlayer[1] = Game.spritesheet.getSprite(96, 0, 32, 32);
        rightPlayer[2] = Game.spritesheet.getSprite(128, 0, 32, 32);
        rightPlayer[3] = Game.spritesheet.getSprite(160, 0, 32, 32);
        

        leftPlayer[3] = Game.spritesheet.getSprite(160, 96, 32, 32);
        leftPlayer[2] = Game.spritesheet.getSprite(160, 96, 32, 32);
        leftPlayer[1] = Game.spritesheet.getSprite(128, 96, 32, 32);
        leftPlayer[0] = Game.spritesheet.getSprite(65, 96, 32, 32);
        
        if (Game.difficult == "Dificil")
			this.removePlayerLife = 3;
		else if(Game.difficult == "Normal")
			this.removePlayerLife = 2;
		else
			this.removePlayerLife = 1;
        
        if(!Game.died0) {
        	this.removePlayerLife = 300;
        }
        this.removePlayerLife = 0;
    }

    public void render(Graphics gfx) 
    {
    	if(!isDameged)
    	{
            this.drawPlayer(gfx);
    	}
    	else 
    	{
    		if((damageFrames >= 20 && damageFrames <= 30) ||
    		   (damageFrames >= 40 && damageFrames <= 50) ||
    		   (damageFrames >= 60 && damageFrames <= 70) ||
    		   (damageFrames >= 80 && damageFrames <= 90))
    		{
    			gfx.drawImage(damegedPlayer, (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
    		}
    		else
    		{
    			 this.drawPlayer(gfx);
    		}
    	}
    }

    public void drawPlayer(Graphics gfx) 
    {
    	gfx.setColor(Color.CYAN);
    	gfx.fillRect(this.getX() - Camera.x - 10, this.getY() - Camera.y + 34, (int)Game.currentCooldownStep / 8, 2);
    	
    	if(Game.GAME_STATE != 1) {
	    	if(moved) {
	    		if (right || left || up || down)  {
		        	if(Game.mx > (Game.WIDTH / 2))
		        		gfx.drawImage(rightPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		        	else
		        		gfx.drawImage(leftPlayer[index], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		        }
		        else {
		        	if(Game.mx > (Game.WIDTH / 2))
		        		gfx.drawImage(defPlayerR, (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		        	else
		        		gfx.drawImage(defPlayerL, (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
		        }
	    	}
	    	else {
	    		if(Game.mx > (Game.WIDTH / 2))
	        		gfx.drawImage(idlePlayerR[idleIndex], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
	        	else
	        		gfx.drawImage(idlePlayerL[idleIndex], (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
	    	}
	    	if(hasWeapon) {
	    	   double dangle = Math.atan2(Game.my - (this.getY() - Camera.y), Game.mx - (this.getX() - Camera.x));
	    	   
	    	   if(Game.mx > 270) {
	    		   gfx.drawImage(ImageUtils.rotate(this.GUN_RIGHT, Math.toDegrees(dangle)), this.getX() - Camera.x, this.getY() - Camera.y + 4, null);
	    	   }
	    	   else {
	    		   gfx.drawImage(ImageUtils.rotate(this.GUN_LEFT, Math.toDegrees(dangle)), this.getX() - Camera.x, this.getY() - Camera.y + 8, null);
	    	   }
	       }
    	}
    	else {
    		gfx.drawImage(damegedPlayer, (int)this.getX() - Camera.x, (int)this.getY() - Camera.y, null);
    	}
    }
    

    public void tick() {
    	
    	// cooldown weapon
    	if (this.isWeaponCooldown()) {
    		for (int i = 0; i < maxWeaponCooldown; i++) {
    			currentWeaponCooldown++;
    			if (currentWeaponCooldown >= maxWeaponCooldown) {
    				currentWeaponCooldown = 0;
    				this.setIsWeaponCooldown(false);
    			}
    		}
    	}
    	
    	// reload
    	if(this.isReload()) {
    		if(this.getAmmo() < maxAmmo) {
    			if(this.getReserveAmmo() > 0) {
    				this.setReserveAmmo(this.getReserveAmmo() - (maxAmmo - this.getAmmo()));
        			this.setAmmo((maxAmmo - this.getAmmo()) + this.getAmmo());
    			}
    			else {
    				while(this.getReserveAmmo() > 0) {
    					this.setReserveAmmo(this.getReserveAmmo() - 1);
    					this.setAmmo(this.getAmmo() + 1);
    				}
    			}
    		}
    		this.setReload(false);
    	}
    	
    	// trocar de arma
    	if(this.isChangeWeapon()) {
    		if(Game.weapon.size() > 0) {
    			if(cur_weapon >= max_weapon) {
        			cur_weapon = 0;
        		}
        		else {
        			Weapon w = Game.weapon.get(cur_weapon);
        			
        			this.setWeapon(w);
        			
        			cur_weapon++;
        		}
        		setChangeWeapon(false);
    		}
    	}
    	
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
            if (frames == 8) {
                frames = 0;
                index++;
                if(index > 3) {
                	index = 0;
                }
            }
        }
        else {
        	idleFrames++;
            if (idleFrames == 8) {
            	idleFrames = 0;
                idleIndex++;
                if(idleIndex > 3) {
                	idleIndex = 0;
                }
            }
        }
        
        this.checkCollisionLifePack();
        this.checkCollisionAmmoPack();
        this.checkCollisionGun();
        this.checkCollisionBullet();
        
        if(shoot && hasWeapon && ammo > 0)
        {
        	double angle = Math.atan2(my - (this.getY() - Camera.y), mx - (this.getX() - Camera.x));
        	
        	double dx = Math.cos(angle), dy = Math.sin(angle);
        	
        	if(this.GUN_LEFT.equals(Entity.GUN_LEFT)) {
        		Bullet bullet = new Bullet(
        				this.getX() + 16,
        				this.getY() + 16, 
        				4, 4, null, 
        				dx, dy, 
        				new Color(124, 62, 62), 
        				8.0, 
        				new Color(137,137,137, 100),
        				true
        			);
            	Game.bullets.add(bullet);
        	}
        	else if(this.GUN_LEFT.equals(Entity.GUN_SHOTGUN_LEFT)) {
        		
        		Bullet b1 = new Bullet(
        				this.getX() + 16, 
        				this.getY() + 16, 
        				4, 4, null, 
        				dx, dy, 
        				new Color(255, 255, 255),
        				8.0,
        				new Color(137,137,137, 100),
        				true
        			);
        		Bullet b2 = new Bullet(
        				this.getX() + 16, 
        				this.getY() + 16, 
        				4, 4, null, 
        				dx - 0.25, dy - 0.25, 
        				new Color(255, 255, 255),
        				8.0,
        				new Color(137,137,137, 100),
        				true
        			);
        		
        		Bullet b3 = new Bullet(
        				this.getX() + 16, 
        				this.getY() + 16, 
        				4, 4, null, 
        				dx + 0.25, dy + 0.25, 
        				new Color(255, 255, 255),
        				8.0,
        				new Color(137,137,137, 100),
        				true
        			);
        		Game.bullets.add(b1);
        		Game.bullets.add(b2);
        		Game.bullets.add(b3);
        		this.setIsWeaponCooldown(true);
        	}
        	this.setAmmo(this.getAmmo() - 1);
        	shoot = false;
        }
        else
        {
        	shoot = false;
        }
        
        if(isDameged)
        {
        	this.damageFrames++;
        	if(this.damageFrames == 120)
        	{
        		this.damageFrames = 0;
        		isDameged = false;
        		invulnerable = false;
        	}
        }
        
        if(life <= 0) {
        	Game.GAME_STATE = 1; //Game Over
        	if(Game.mapName.contains("1"))
        		Game.diedLevel0 = true;
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
				if(!Game.player.invulnerable) {
					Game.player.isDameged = true;
					Sound.playerHurt.play(0.7f);
					Game.bulletsEn.remove(i);
					Game.player.setLife(Game.player.getLife() - this.removePlayerLife);
					Game.player.invulnerable = true;
					return;
				}
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
    					life+=1;
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
    				max_weapon++;
    				if(this.GUN_LEFT == null) {
    					this.setWeapon(w);
    				}
    				return;
    			}
    		}
    	}
    }
    
    public void setWeapon(Weapon w) {
    	if(w.getSprite() == Entity.WEAPON_ENT_RIFLE_NON_AUTO) {
			this.GUN_LEFT = Entity.GUN_LEFT;
			this.GUN_RIGHT = Entity.GUN_RIGHT;
		}
		else if(w.getSprite() == Entity.WEAPON_ENT_SHOTGUN) {
			this.GUN_LEFT = Entity.GUN_SHOTGUN_LEFT;
			this.GUN_RIGHT = Entity.GUN_SHOTGUN_RIGHT;
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
    				if(this.getAmmo() != maxAmmo)
    				{
    					this.setAmmo(this.getAmmo() + 25);
        				if(this.getAmmo() >= maxAmmo) {
        					this.setAmmo(maxAmmo);
        				}
        				Game.entities.remove(i);
    				}
    				else
        			{
    					if(this.getReserveAmmo() != 100)
    					{
    						this.setReserveAmmo(maxAmmo);
    						Game.entities.remove(i);
    					}
        			}
    				return;
    			}
    		}
    	}
    }

    public BufferedImage getGunLeft() {
    	return this.GUN_LEFT;
    }
    
    public void setGUN_LEFT(BufferedImage img) {
    	this.GUN_LEFT = img;
    }
    
    public void setGUN_RIGHT(BufferedImage img) {
    	this.GUN_RIGHT = img;
    }
    
	public boolean isKeepShooting() {
		return keepShooting;
	}

	public void setKeepShooting(boolean keepShooting) {
		this.keepShooting = keepShooting;
	}

	public boolean isChangeWeapon() {
		return changeWeapon;
	}

	public void setChangeWeapon(boolean changeWeapon) {
		this.changeWeapon = changeWeapon;
	}

	public boolean isReload() {
		return reload;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}

	public int getReserveAmmo() {
		return reserveAmmo;
	}

	public void setReserveAmmo(int reserveAmmo) {
		this.reserveAmmo = reserveAmmo;
	}

	public int getLife() {
		return this.life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public int getGoldAmount() {
		return goldAmount;
	}

	public void setGoldAmount(int goldAmount) {
		this.goldAmount = goldAmount;
	}

	public boolean isWeaponCooldown() {
		return IsWeaponCooldown;
	}

	public void setIsWeaponCooldown(boolean isWeaponCooldown) {
		IsWeaponCooldown = isWeaponCooldown;
	}
	
}