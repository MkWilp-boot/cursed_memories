package com.xesque.main;
// FF0000

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.xesque.entities.Boss;
import com.xesque.entities.Bullet;
import com.xesque.entities.Enemy;
import com.xesque.entities.Entity;
import com.xesque.entities.Player;
import com.xesque.entities.Portal;
import com.xesque.entities.Weapon;
import com.xesque.graficos.Spritesheet;
import com.xesque.graficos.UI;
import com.xesque.world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    public static Spritesheet spritesheet;

    private Thread thread;

    private boolean bIsRunning;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    private int framdesRestart = 0, maxFramesRestart = 200;
    public final static int SCALE = 1;
    public static int GAME_STATE = 2;
    
    public static String mapName = "/map_1.png";
    
    private BufferedImage image;
    
    public static int CUR_LEVEL = 1, MAX_LEVEL = 6;

	static int CUR_SCENE = 0;
    public static boolean CHANGE_LEVEL = false;
    public static boolean inScene = false;
    public static boolean nextLevel = false;
    public static boolean showPortal = false;
    public boolean resetAble = false, showRestart = false;

    private JFrame frame;

    public static List < Entity > entities;
    public static List < Enemy > enemies;
    public static List < Boss > bosses;
    public static List < Bullet > bullets;
    public static List < Bullet > bulletsEn;
    public static List < Weapon > weapon;
    public static Player player;
    
    public static World world;
    public static Random rand;
    
    public static int mx;

	public static int my;

    public static UI ui;
    
    public Menu menu;

    public boolean saveGame = false;
    public static double angle;
    
    public InputStream stream_main_font = ClassLoader.getSystemClassLoader().getResourceAsStream("main_font.ttf");
    public static Font main_font;
    
    public Game() 
    {
    	//Sound.musicGB.loop();
    	try 
    	{
			main_font = Font.createFont(Font.TRUETYPE_FONT, stream_main_font).deriveFont(48f);
		} 
    	catch (FontFormatException e) 
    	{
			e.printStackTrace();
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    	
    	rand = new Random();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
        entities = new ArrayList < Entity > ();
        enemies = new ArrayList< Enemy >();
        bullets = new ArrayList< Bullet >();
        bulletsEn = new ArrayList< Bullet >();
        bosses = new ArrayList< Boss >();
        weapon = new ArrayList< Weapon >();
        
        spritesheet = new Spritesheet("/spr.png");
        ui = new UI();
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        
        player = new Player(0, 0, 32, 32, spritesheet.getSprite(64, 0, 32, 32));
        entities.add(player);
        world = new World(mapName);
        menu = new Menu("/logo.png", "/BG.jpg");
    }

    public void initFrame() {
        frame = new JFrame("Cursed Memories");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start() {
        thread = new Thread(this);
        bIsRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        bIsRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	
        Game game = new Game();
        //Game.inScene = true;
        game.start();
    }

    public void tick() {

    	if(resetAble)
    	{
    		resetAble = false;
    		World.restartGame(mapName);
    	}
    	// Normal gameplay
    	if(GAME_STATE == 0)
        {
    		if(saveGame)
    		{
    			saveGame = false;
    			if(bosses.size() <= 0 && enemies.size() <= 0)
    			{
    				String[] opt1 = {"mapa",
									"PlayerX",
									"PlayerY",
									"vida",
									"municao"
								};
					int[] opt2 = {CUR_LEVEL, 
									player.getX(),
									player.getY(),
									player.life,
									player.ammo
								};
					Menu.saveGame(opt1, opt2, 10);
					System.out.println("Saved");
    			}
    			else
    			{
    				System.out.println("O mapa não cumpre os requesitos para salvar o jogo!\n(Existe um Boss ou Inimigos vivos)");
    			}
    		}
    		
    		if(bosses.size() == 0 && CHANGE_LEVEL && CUR_LEVEL <= MAX_LEVEL)
            {
            	showPortal = true;
            	if(nextLevel)
            	{
            		showPortal = false;
            		nextLevel = false;
            		CUR_LEVEL++;
                    mapName = "/map_" + CUR_LEVEL + ".png";
                    CHANGE_LEVEL = false;
                    World.restartGame(mapName);
                    System.out.println(mapName);
            	}
            }
    		
    		for (int i = 0; i < entities.size(); i++) {
                Entity e = entities.get(i);
                if(e instanceof Portal)
                {
                	if(showPortal)
                	{
                		e.tick();
                	}
                }
                else
                {
                	e.tick();
                }
            }
    	}
    	// Game Over
    	else if(GAME_STATE == 1)
    	{
    		for(int i = 0; i < 5; i++)
    		{
    			framdesRestart++;
    			if(showRestart)
    			{
    				if(framdesRestart >= maxFramesRestart)
        			{
        				showRestart = false;
        				framdesRestart = 0;
        			}
    			}
    			else
    			{
    				if(framdesRestart >= maxFramesRestart)
        			{
        				showRestart = true;
        				framdesRestart = 0;
        			}
    			}
    		}
    	}
    	// Menu
    	else if(GAME_STATE == 2)
    	{
    		menu.tick();
    	}
    	angle = Math.toDegrees(Math.atan2(my, mx));
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics gfx = bs.getDrawGraphics();
        
        //Graphics gfx = image.getGraphics();
        // BG
        
        gfx.setColor(new Color(0, 0, 0));
        gfx.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        world.render(gfx);
        
        // Entities
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if(e instanceof Portal)
            {
            	if(showPortal)
            	{
            		e.render(gfx);
            	}
            }
            else
            {
            	 e.render(gfx);
            }
        }
        if(GAME_STATE != 2)
        {
        	 for (int i = 0; i < bullets.size(); i++) {
                 bullets.get(i).render(gfx);
                 bullets.get(i).tick();
             }
             
             for (int i = 0; i < bulletsEn.size(); i++) {
             	bulletsEn.get(i).render(gfx);
             	bulletsEn.get(i).tick();
             }
        }
       
        ui.render(gfx);
        //gfx.dispose();
        //gfx = bs.getDrawGraphics();
		//gfx.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
     
        if(GAME_STATE == 1)
        {
        	Graphics2D gfx2 = (Graphics2D) gfx;
        	gfx2.setColor(new Color(0,0,0,100));
        	gfx2.fillRect(0, 0, WIDTH, HEIGHT);
        	gfx2.setColor(Color.white);
        	gfx2.setFont(new Font("Arial", Font.PLAIN, 40));
        	gfx2.drawString("Game Over", WIDTH / 2 - 80, HEIGHT / 2);
        	gfx2.setFont(new Font("Arial", Font.PLAIN, 25));
        	if(showRestart)
        	{
        		gfx2.drawString(">> Pressione \"ENTER\" para reiniciar <<", (WIDTH * SCALE) / 2 - 205, (HEIGHT * SCALE) / 2 + 40);
        	}
        }
        else if(GAME_STATE == 2)
        {
        	menu.render(gfx);
        }
       
        // Showing
        gfx.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long lLastTime = System.nanoTime();
        double dAmountOfTicks = 60.0;
        double dNs = 1000000000 / dAmountOfTicks;
        int nFrames = 0;
        double dTimer = System.currentTimeMillis();
        double dDelta = 0;
        requestFocus();
        while (bIsRunning) {
            long lNow = System.nanoTime();
            dDelta += (lNow - lLastTime) / dNs;
            lLastTime = lNow;

            if (dDelta >= 1) {
                tick();
                render();
                nFrames++;
                dDelta--;
            }
            if (System.currentTimeMillis() - dTimer >= 1000) {
                System.out.println("FPS: " + nFrames);
                nFrames = 0;
                dTimer += 1000;
            }
        }
        stop();
    }
   
    @Override
    public void keyTyped(KeyEvent e) {
    	
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {	
    	if(GAME_STATE == 1)
    	{
    		if(e.getKeyCode() == KeyEvent.VK_ENTER)
    		{
    			resetAble = true;
    		}
    	}
    	
        // Direita - Esquerda
        if (e.getKeyCode() == KeyEvent.VK_D) 
        {
            player.right = true;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_A) 
        {
            player.left = true;
        }

        // Cima - baixo
        if (e.getKeyCode() == KeyEvent.VK_S)  
        {
            player.down = true;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_W)  
        {
            player.up = true;
        }

        if(GAME_STATE == 2)
        {
        	if(e.getKeyCode() == KeyEvent.VK_UP)
            {
            	menu.up = true;
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
            	menu.down = true;
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
   
        if (e.getKeyCode() == KeyEvent.VK_D) 
        {
            player.right = false;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_A) 
        {
            player.left = false;
        }

        // Cima - baixo
        if (e.getKeyCode() == KeyEvent.VK_S)  
        {
            player.down = false;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_W)  
        {
            player.up = false;
        }

        // Menu
        if(GAME_STATE == 2)
        {
        	if(e.getKeyCode() == KeyEvent.VK_UP)
            {
            	menu.up = false;
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
            	menu.down = false;
            }
        	
        	if(e.getKeyCode() == KeyEvent.VK_ENTER)
        	{
        		Menu.context = true;
        	}
        }
        else if(GAME_STATE == 0)
        {
        	if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        	{
        		Menu.paused = true;
        		GAME_STATE = 2;
        	}
        	if(e.getKeyCode() == KeyEvent.VK_SPACE)
        	{
        		saveGame = true;
        	}
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1)
		{
			player.shoot = true;
			player.mx = e.getX();
			player.my = e.getY();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
	}
}