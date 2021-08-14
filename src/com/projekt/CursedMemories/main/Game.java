package com.projekt.CursedMemories.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.projekt.CursedMemories.entities.Boss;
import com.projekt.CursedMemories.entities.Bullet;
import com.projekt.CursedMemories.entities.Enemy;
import com.projekt.CursedMemories.entities.Entity;
import com.projekt.CursedMemories.entities.Merchant;
import com.projekt.CursedMemories.entities.Player;
import com.projekt.CursedMemories.entities.Portal;
import com.projekt.CursedMemories.entities.SaveBeam;
import com.projekt.CursedMemories.entities.Weapon;
import com.projekt.CursedMemories.graficos.Pixel;
import com.projekt.CursedMemories.graficos.Spritesheet;
import com.projekt.CursedMemories.graficos.UI;
import com.projekt.CursedMemories.world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private static final long serialVersionUID = 1L;

    public static Spritesheet spritesheet;
    public static Spritesheet spr_map0;
    public static SpriteSheet spr_b001;
    public static Spritesheet spr_bDarker;
    
    private Thread thread;

    private boolean bIsRunning;
    public static boolean isInScene;

    public static final int WIDTH = 540; //540
    public static final int HEIGHT = 330; // 330
    
    private int framdesRestart = 0, maxFramesRestart = 200;
    public final static int SCALE = 2;
    
    //public static int WIDTH_SCALE = Toolkit.getDefaultToolkit().getScreenSize().width;
    //public static int HEIGHT_SCALE = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public static final int WIDTH_SCALE = WIDTH * SCALE;
    public static final int HEIGHT_SCALE = HEIGHT * SCALE;
    
    public static int GAME_STATE = 2;
    public static int CUR_LEVEL = 0, MAX_LEVEL = 6;
    public static String mapName = "/map_"+ CUR_LEVEL +".png";

    private BufferedImage image;

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
    
    // Cutscene
    public static int entrada = 1;
    public static int comecar = 2;
    public static int jogando = 3;
    public static int estado_cena = entrada;

    public static UI ui;

    public Menu menu;
    
    public static Merchant merchant;

    public boolean saveGame = false;
    public static double angle;

    public InputStream stream_main_font = ClassLoader.getSystemClassLoader().getResourceAsStream("main_font.ttf");
    public static Font main_font;

    public int[] pixels;
    public BufferedImage lightmap;
    public int[] lightmapPixels;
    
    private boolean isPreparedDash = false;
    
    private int dashSize = 96;
    
    public static int dashCooldown = 400;
    public static int currentCooldownStep = 0;
    
    public List<UIMessages> falas = new ArrayList<>();
    
    // Dialogos
    public static int currentDialogue;

    public Game() {
    	
        try {
            main_font = Font.createFont(Font.TRUETYPE_FONT, stream_main_font).deriveFont(48f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
        rand = new Random();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        //this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        initFrame();
        entities = new ArrayList < Entity > ();
        enemies = new ArrayList < Enemy > ();
        bullets = new ArrayList < Bullet > ();
        bulletsEn = new ArrayList < Bullet > ();
        bosses = new ArrayList < Boss > ();
        weapon = new ArrayList < Weapon > ();

        spritesheet = new Spritesheet("/spr.png");
        spr_map0 = new Spritesheet("/spr_map_0.png");
        spr_b001 = new SpriteSheet("/b001_spr.png");
        spr_bDarker = new Spritesheet("/spr_boss_darker.png");
        
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        try {
            lightmap = ImageIO.read(getClass().getResource("/light.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        lightmapPixels = new int[lightmap.getWidth() * lightmap.getHeight()];
        lightmap.getRGB(0, 0, lightmap.getWidth(), lightmap.getHeight(), lightmapPixels, 0, lightmap.getWidth());
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        player = new Player(0, 0, 32, 32, spritesheet.getSprite(64, 0, 32, 32));
        entities.add(player);
        
        merchant = new Merchant(0, 0, 32, 32, Entity.MerchantIdle);

        entities.add(merchant);
        
        world = new World(mapName, true);
        ui = new UI();
        menu = new Menu("/logo.png", "/BG_0.png");
        currentDialogue = 0;
        isInScene = true;
        
        String[] M_0_INTROCUCAO_HALL_MSG = {". . .",". . .",
					"Acorde.",
					"Você esteve fora por tanto tempo que. . . Bem. . . você verá.",
					"O seu tempo não chegou ainda.",
					"Ainda há o que ser feito.",
					"Então acorde, meu filho, acorde e....",
					"Cumpra seu dever"};
        UIMessages M_0_INTROCUCAO_HALL = new TextMessage(M_0_INTROCUCAO_HALL_MSG, "???");
        
        
        String[] M_0_FALAPLAYER_00_MSG = {"????",
        							  "Onde estou?"};
        UIMessages M_0_FALAPLAYER_00 = new TextMessage(M_0_FALAPLAYER_00_MSG, "Jogador");
        
        UIMessages HINT_BASICS = new HintMessage(new String[] {"Mova-se pelo mapa usando W A S D"}, "");
        
        new Thread(() -> {
        	falas.add(M_0_INTROCUCAO_HALL);	
        	falas.add(M_0_FALAPLAYER_00);
        	falas.add(HINT_BASICS);
        }).run();
    }

    private void dashAble(String dir) {
    	if(currentCooldownStep == 0) {
    		currentCooldownStep = 400;
        	if(dir == "CIMA") {
        		int prev = player.getY() - this.getDashSize();
        		if(World.isFree(player.getX(), player.getY() - this.getDashSize()) && prev > 32) {
        			player.setY(player.getY() - this.getDashSize());
        		}
        	}
        	else if(dir == "BAIXO") {
        		int prev = player.getY() + this.getDashSize();
        		if(World.isFree(player.getX(), player.getY() + this.getDashSize())  && prev < World.MAX_MAP_Y) {
        			player.setY(player.getY() + this.getDashSize());
        		}
        	}
        	else if(dir == "ESQUERDA") {
        		int prev = player.getX() - this.getDashSize();
        		if(World.isFree(player.getX() - this.getDashSize(), player.getY()) && prev > 32) {
        			player.setX(player.getX() - this.getDashSize());
        		}
        	}
        	else if(dir == "DIREITA") {
        		int prev = player.getX() + this.getDashSize();
        		if(World.isFree(player.getX() + this.getDashSize(), player.getY()) && prev < World.MAX_MAP_X) {
        			player.setX(player.getX() + this.getDashSize());
        		}
        	}
    	}
    }

    public void initFrame() {
        frame = new JFrame("Cursed Memories");
        frame.add(this);
        frame.setResizable(false);
        //frame.setUndecorated(true);
        frame.pack();
        Image icon = null;

        try {
            //icon = ImageIO.read(getClass().getResource("/icon.png"));
        	icon = ImageIO.read(new FileInputStream("res/icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cross = toolkit.getImage(getClass().getResource("/cross.png"));
        Cursor cursor = toolkit.createCustomCursor(cross, new Point(0, 0), "img");

        frame.setCursor(cursor);
        frame.setIconImage(icon);
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
    	if(currentCooldownStep > 0) {
    		currentCooldownStep--;
    	}
        if (resetAble) {
            resetAble = false;
            World.restartGame(mapName, true);
        }
        // Normal gameplay
        if (GAME_STATE == 0) {
        	
        	Sound.musicGB.stop();
        	if(CUR_LEVEL == 0 && Game.estado_cena == Game.jogando) {
            	Sound.mp_bg_0.loop(0.5f);
            }
            else {
            	Sound.mp_bg_0.stop();
            }
        	
            if(CUR_LEVEL == 1) {
            	Sound.mp_bg_1.loop(0.5f);
            }
            else {
            	Sound.mp_bg_1.stop();
            }
            
            if (bosses.size() == 0 && CHANGE_LEVEL && CUR_LEVEL <= MAX_LEVEL) {
                showPortal = true;
                if (nextLevel) {
                	System.out.println("proximo nivel");
                    showPortal = false;
                    nextLevel = false;
                    CUR_LEVEL++;
                    mapName = "/map_" + CUR_LEVEL + ".png";
                    CHANGE_LEVEL = false;
                    World.restartGame(mapName, true);
                    System.out.println(mapName);
                }
            }

            if(Game.estado_cena == Game.entrada) {
            	if(!isInScene) {
            		Game.estado_cena = Game.comecar;
            	}
            	if(currentDialogue == 1 || currentDialogue == 2) {
            		isInScene = true;
            	}
            }
            else if(Game.estado_cena == Game.jogando) {
            	for (int i = 0; i < entities.size(); i++) {
                    Entity e = entities.get(i);
                    if (e instanceof Portal) {
                        if (showPortal) 
                            e.tick();
                    }
                    else {
                    	e.tick();
                    }
                }
                for (int i = 0; i < bullets.size(); i++) {
                    bullets.get(i).tick();
                }
                for (int i = 0; i < bulletsEn.size(); i++) {
                    bulletsEn.get(i).tick();
                }
                if(currentDialogue == 1) {
                	Game.estado_cena = Game.entrada;
                }
            }
            else if(Game.estado_cena == Game.comecar) {
            	Game.estado_cena = Game.jogando;
            }
        }
        // Game Over
        else if (GAME_STATE == 1) {
            for (int i = 0; i < 5; i++) {
                framdesRestart++;
                if (showRestart) {
                    if (framdesRestart >= maxFramesRestart) {
                        showRestart = false;
                        framdesRestart = 0;
                    }
                } else {
                    if (framdesRestart >= maxFramesRestart) {
                        showRestart = true;
                        framdesRestart = 0;
                    }
                }
            }
        }
        // Menu
        else if (GAME_STATE == 2) {
            menu.tick();
            Sound.musicGB.loop(0.5f);
            if(CUR_LEVEL == 1) {
            	Sound.mp_bg_1.stop();
            }
        }
        else if(GAME_STATE == 3) {
        	for(int i = 0; i < entities.size(); i++) {
        		Entity en = entities.get(i);
        		if(en instanceof Player || en instanceof Merchant) {
        			en.tick();
        		}
        	}
        }
        angle = Math.toDegrees(Math.atan2(my, mx));
    }

    public void applyLight() {
        for (int x = 0; x < lightmap.getWidth(); x++) {
            for (int y = 0; y < lightmap.getHeight(); y++) {
                if (lightmapPixels[x + (y * lightmap.getWidth())] == 0xffffffff) {
                    int pixel = Pixel.getLightBlend(pixels[x + (y * lightmap.getWidth())], 0x000000, 0);
                    pixels[x + (y * lightmap.getWidth())] = pixel;
                }
            }
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        //Graphics gfx = bs.getDrawGraphics();

        Graphics gfx = image.getGraphics();
        // B
        
        switch (CUR_LEVEL) {
        case 0:
            gfx.setColor(new Color(0, 0, 0));
        break;
        case 1:
            gfx.setColor(new Color(69, 80, 82));
        break;
        default:
            gfx.setColor(new Color(0, 0, 0));
        }
        
        gfx.fillRect(0, 0, 
        		WIDTH_SCALE, 
        		HEIGHT_SCALE);
        world.render(gfx);

        // Entities
        if(GAME_STATE != 3) {
	        for (int i = 0; i < entities.size(); i++) {
	            Entity e = entities.get(i);
	            
	            if (e instanceof Portal || e instanceof SaveBeam) {
	                if (showPortal) {
	                    e.render(gfx);
	                }
	            }
	            else {
	            	e.render(gfx);
	            }
	
	            if (e instanceof SaveBeam) {
	                if (showPortal) {
	                    if (((SaveBeam) e).isCollidingPlayer()) {
	                        ((SaveBeam) e).renderSaveMSG(gfx);
	                        saveGame = true;
	                    } else {
	                        saveGame = false;
	                    }
	                }
	            }
	        }
        }
        else {
        	for(int i = 0; i < entities.size(); i++) {
        		Entity en = entities.get(i);
        		if(en instanceof Player || en instanceof Merchant) {
        			en.render(gfx);
        		}
            }
        }

        if (GAME_STATE != 2) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).render(gfx);
            }

            for (int i = 0; i < bulletsEn.size(); i++) {
                bulletsEn.get(i).render(gfx);
            }
        }
        applyLight();
        if(GAME_STATE != 3){
        	ui.render(gfx);
        }
        
        // Dialogos;
        if(isInScene) {
    		falas.get(currentDialogue).render(gfx);
        }
        //
        
        gfx.dispose();
        gfx = bs.getDrawGraphics();
        gfx.drawImage(image,
            0,
            0,
            WIDTH_SCALE,
    		HEIGHT_SCALE,
            null
        );

        if (GAME_STATE == 1) {
            Graphics2D gfx2 = (Graphics2D) gfx;
            gfx2.setColor(new Color(0, 0, 0, 130));
            gfx2.fillRect(0, 0, WIDTH_SCALE, HEIGHT_SCALE);
            gfx2.setColor(new Color(255,255,255,230));
            gfx2.setFont(main_font.deriveFont(90f));
            gfx2.drawString("Game Over", WIDTH_SCALE / 2 - 160, HEIGHT_SCALE / 2);
            gfx2.setFont(main_font.deriveFont(40f));
            if (showRestart) {
                gfx2.drawString(">> Pressione \"ENTER\" para reiniciar <<", WIDTH_SCALE / 2 - 300, HEIGHT_SCALE / 2 + 60);
            }
        } else if (GAME_STATE == 2) {
            menu.render(gfx);
        }

        // Showing
        //gfx.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long lLastTime = System.nanoTime();
        double dAmountOfTicks = 60.0;
        double dNs = 1000000000 / dAmountOfTicks;
        //int nFrames = 0;
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
                
                //nFrames++;
                dDelta--;
            }
            if (System.currentTimeMillis() - dTimer >= 1000) {
                //System.out.println("FPS: " + nFrames);
                //nFrames = 0;
                dTimer += 1000;
            }
        }
        stop();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    	
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	if(isInScene) {
    		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
    			if(falas.get(currentDialogue) instanceof HintMessage) {
    				HintMessage mm = (HintMessage) falas.get(currentDialogue);
        			mm.setNextPhrase(true);
    			}
    			else if(falas.get(currentDialogue) instanceof TextMessage) {
    				TextMessage mm = (TextMessage) falas.get(currentDialogue);
        			mm.setNextPhrase(true);
    			}
    		}
    	}
    	
    	if(GAME_STATE == 3) {
    		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    			merchant.baixo = true;
    		}
    		else if(e.getKeyCode() == KeyEvent.VK_UP) {
    			merchant.cima = true;
    		}
    		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
    			merchant.hitEnter = true;
    		}
    		
    		if(merchant.levelOfDeep == 2) {
    			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    				merchant.right = true;
    			}
    			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    				merchant.left = true;
    			}
    		}
    	}
    	
    	if(e.getKeyCode() == KeyEvent.VK_ALT) {
    		isPreparedDash = true;
    	}
    	
    	if(isPreparedDash) {
    		if(e.getKeyCode() == KeyEvent.VK_W) {
        		dashAble("CIMA");
        	}
    		else if(e.getKeyCode() == KeyEvent.VK_S) {
        		dashAble("BAIXO");
        	}
    		else if(e.getKeyCode() == KeyEvent.VK_D) {
        		dashAble("DIREITA");
        	}
    		else if(e.getKeyCode() == KeyEvent.VK_A) {
        		dashAble("ESQUERDA");
        	}
    	}
    	
    	if(e.getKeyCode() == KeyEvent.VK_R) {
    		if(Game.player.isReload()) {
    			Game.player.setReload(true);
    		}
    	}

        if (GAME_STATE == 1) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                resetAble = true;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_R) {
        	player.setReload(true);
        }
        // Direita - Esquerda
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        }

        // Cima - baixo
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = true;
        }

        if (GAME_STATE == 2) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                menu.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                menu.down = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	
    	if(GAME_STATE == 3) {
    		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
    			merchant.baixo = false;
    		}
    		else if(e.getKeyCode() == KeyEvent.VK_UP) {
    			merchant.cima = false;
    		}
    	}

    	if(e.getKeyCode() == KeyEvent.VK_ALT) {
    		isPreparedDash = false;
    	}
    	
        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        }

        // Cima - baixo
        if (e.getKeyCode() == KeyEvent.VK_S) {
            player.down = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            player.up = false;
        }

        // Menu
        if (GAME_STATE == 2) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                menu.up = false;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                menu.down = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                Menu.context = true;
            }
        } else if (GAME_STATE == 0) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Menu.paused = true;
                GAME_STATE = 2;
            }
            if (saveGame) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SaveBeam.isSaved = true;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
// 256 128
    @Override
    public void mousePressed(MouseEvent e) {
    	
        if (e.getButton() == 1) {
            player.shoot = true;
            player.mx = e.getX() / SCALE;
            player.my = e.getY() / SCALE;
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
        mx = e.getX() / SCALE;
        my = e.getY() / SCALE;

    }

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		Game.player.setChangeWeapon(true);
	}

	public int getDashSize() {
		return dashSize;
	}

	public void setDashSize(int dashSize) {
		this.dashSize = dashSize;
	}
}