package com.projekt.CursedMemories.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.projekt.CursedMemories.entities.Boss;
import com.projekt.CursedMemories.entities.Bullet;
import com.projekt.CursedMemories.entities.Enemy;
import com.projekt.CursedMemories.entities.Entity;
import com.projekt.CursedMemories.entities.Merchant;
import com.projekt.CursedMemories.entities.Player;
import com.projekt.CursedMemories.entities.Portal;
import com.projekt.CursedMemories.entities.SaveBeam;
import com.projekt.CursedMemories.entities.Weapon;
import com.projekt.CursedMemories.graficos.ContinousSequences;
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
    public static Spritesheet spr_hub;
    public static Spritesheet spr_vulcao;
    
    private Thread thread;

    private boolean bIsRunning;
    public static boolean isInScene;

    public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2; //540
    public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 2; // 330
    
    private int framdesRestart = 0, maxFramesRestart = 200;
    public final static int SCALE = 2;
    
    //public static int WIDTH_SCALE = Toolkit.getDefaultToolkit().getScreenSize().width;
    //public static int HEIGHT_SCALE = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public static final int WIDTH_SCALE = WIDTH * SCALE;
    public static final int HEIGHT_SCALE = HEIGHT * SCALE;
    
    public static final String ROOT_DIR = System.getProperty("user.dir");
    
    public static int GAME_STATE = 2;
    public static int CUR_LEVEL = 1, MAX_LEVEL = 2;
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
    public List < BufferedImage > gameOverSequence;
    public List < BufferedImage > bossBestroyedSequence;
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
    
    public static Boolean isGameStarded = false;
    
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
    
    public static boolean rmvSetToBlack = false;
    
    public static boolean boss_fire_kill = false;
    public static boolean boss_clock_kill = false;
    public static boolean boss_final_kill = false;
    
    public Integer maxFramesGameOver = 300;
    public Integer currentFrameOfGameOver = 0;
    
    public Integer maxFramesBossDestroyed = 300;
    public Integer currentFrameOfBossDestroyed = 0;
    
    public static String difficult = null;
    public static Boolean bossDestroyed = false;
    public Integer maxBossDisp = 200;
    public Integer currentBossStepDisp = 0;
    public Integer destroyedBossAplhaChannel = 200;
    private Timer timer;

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

        //this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
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
        spr_hub = new Spritesheet("/spr_hub.png");
        spr_vulcao = new Spritesheet("/spr_vulcao.png");
        
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
        ui = new UI("/HUD_HEALTH_PLAYER.png");
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
        final UIMessages M_0_INTROCUCAO_HALL = new TextMessage(M_0_INTROCUCAO_HALL_MSG, "???:");
        
        String[] M_0_FALAPLAYER_00_MSG = {"????",
        							  "Onde estou?"};
        final UIMessages M_0_FALAPLAYER_00 = new TextMessage(M_0_FALAPLAYER_00_MSG, "Jogador:");
        
        String[] M_0_FVOZ_DESCONHECIDA = 
        	{"Não . . .",
        	"Você . . . não pode",
        	"Não está pronto . . .",
        	"Seu lugar não é aqui",
        	". . . . .",
        	"Não ainda !"
        };
        final UIMessages M_0_FVOZ_DESCONHECIDA_00 = new TextMessage(M_0_FVOZ_DESCONHECIDA, "Alguém:");
        
        final UIMessages HINT_BASICS = new HintMessage(new String[] {"Mova-se pelo mapa usando W A S D"}, "");
        
        new Thread(() -> {
        	falas.add(M_0_INTROCUCAO_HALL);	
        	falas.add(M_0_FALAPLAYER_00);
        	falas.add(HINT_BASICS);
        	falas.add(M_0_FVOZ_DESCONHECIDA_00);
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
        frame.setUndecorated(true);
        frame.pack();
        
        GraphicsEnvironment graphics =
		GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
        
        Image icon = null;

        try {
            icon = ImageIO.read(getClass().getResource("/icon.png"));
        	//icon = ImageIO.read(new FileInputStream("res/icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image cross = toolkit.getImage(getClass().getResource("/crosshair.png"));
        Cursor cursor = toolkit.createCustomCursor(cross, new Point(0, 0), "img");

        frame.setCursor(cursor);
        frame.setIconImage(icon);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        device.setFullScreenWindow(frame);
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
        var generators = new ContinousSequences();
        game.gameOverSequence = generators.GetsGnerateion(1);
        game.bossBestroyedSequence = generators.GetsGnerateion(2);
        game.start();
    }

    public void tick() {
    	/*if (bossDestroyed) {
    		if (currentBossStepDisp <= maxBossDisp) {
    			currentBossStepDisp++;
    		}
    		else if (currentBossStepDisp >= maxBossDisp) {
    			if (destroyedBossAplhaChannel > 0) {
    				destroyedBossAplhaChannel--;
    			}
    			else
    			{
    				destroyedBossAplhaChannel = 0;
    				bossDestroyed = false;
    			}
    		}
    	}*/
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
        	// MAPA 0 (hall)
        	if(mapName.contains("0") && Game.estado_cena == Game.jogando) {
        		Sound.mp_bg_0.loop(0.5f);
        	}
        	else {
        		Sound.mp_bg_0.stop();
        	}
        	// MAPA 1 (lobby)
        	if(mapName.contains("1")) {
        		if(!rmvSetToBlack) 
        			Sound.mp_bg_1.loop(0.5f);
            }
            else {
            	Sound.mp_bg_1.stop();
            }
        	// MAPA VULCAO (boss de fogo)
            if(mapName.contains("vulcao")) {
            	Sound.mp_bg_v.loop(0.5f);
            }
            else {
            	Sound.mp_bg_v.stop();
            }
            
            if (bosses.size() == 0 && CHANGE_LEVEL && CUR_LEVEL <= MAX_LEVEL) {
                showPortal = true;
                if (nextLevel) {
                	System.out.println("proximo nivel");
                    showPortal = false;
                    nextLevel = false;
                    CUR_LEVEL++;
                    // só pq n tem a luta feita ainda
                    // logo, esse bloco será removido
                    // na proxima versão
                    mapName = "/map_" + CUR_LEVEL + ".png";
                    CHANGE_LEVEL = false;
                    if(CUR_LEVEL == 1) {
                    	rmvSetToBlack = true;
                    	World.restartGame(mapName, true);
                    }
                    else {
                    	World.setLevel(mapName, true);
                    }
                    
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
	            
	            if (!(e instanceof Boss)) {
	            	if (e instanceof Portal) {
		                if (showPortal) {e.render(gfx);}
		            }
		            else {
		            	e.render(gfx);
		            }

		            if (e instanceof SaveBeam) {
		            	e.render(gfx);
	                    if (((SaveBeam) e).isCollidingPlayer()) {
	                        ((SaveBeam) e).renderSaveMSG(gfx);
	                        saveGame = true;
	                    } else {
	                        saveGame = false;
	                    }
		            }
	            }
	        }
	        for (var b : bosses) {
	        	b.render(gfx);
	        }
        }
        else {
        	for(int i = 0; i < entities.size(); i++) {
        		Entity en = entities.get(i);
        		if(en instanceof Merchant) {
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

        if(GAME_STATE != 3){
        	ui.render(gfx);
        }

        if(rmvSetToBlack) {
        	gfx.setColor(Color.BLACK);
        	isInScene = true;
	    	gfx.fillRect(0, 0, 
	          		WIDTH_SCALE, 
	          		HEIGHT_SCALE);
	    	Sound.mp_bg_1.stop();
        }

        if(currentDialogue == 0) {
        	gfx.setColor(Color.BLACK);
        	gfx.fillRect(0, 0, 
        		WIDTH_SCALE, 
        		HEIGHT_SCALE);
    	}
     
        // Dialogos;
        if(isInScene) {
    		falas.get(currentDialogue).render(gfx);
        }
        
        if (bossDestroyed) {
        	gfx.setColor(new Color( 0, 0, 0, 140 ));
        	gfx.fillRect(0, 0, WIDTH_SCALE, HEIGHT_SCALE);
        	currentFrameOfBossDestroyed++;
            if (currentFrameOfBossDestroyed >= maxFramesBossDestroyed) {
            	currentFrameOfBossDestroyed = 0;
            	bossDestroyed = false;
            }
            var img = bossBestroyedSequence.get(currentFrameOfBossDestroyed);
            var w = img.getWidth();
            var h = img.getHeight();
            gfx.drawImage(img, (WIDTH / 2) - (w / 2), (HEIGHT / 2) - (h / 2), null);
            
            gfx.setColor(new Color(255, 255, 255, 100));
            gfx.setFont(main_font.deriveFont(20f));
        	gfx.drawString("Você agora possui: " + player.getGoldAmount() + " Moedas!", WIDTH / 2 - 110, HEIGHT / 2 + 110);
            
            /*
        	gfx.setColor(new Color(0,0,0, destroyedBossAplhaChannel));
        	var scaleY = (int)(HEIGHT / 2) -50;
        	var scaleX = (int)(WIDTH / 2) - 90;
        	
        	gfx.fillRect(0, scaleY, WIDTH, 125);
        	gfx.setFont(main_font.deriveFont(50f));
        	gfx.setColor(new Color(255,255,255, destroyedBossAplhaChannel));
        	gfx.drawString("Sala limpa", scaleX, scaleY + 50);
        	
        	*/
        }
        
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
            currentFrameOfGameOver++;
            if (currentFrameOfGameOver >= maxFramesGameOver) {
            	currentFrameOfGameOver = 0;
            }
            gfx2.setColor(new Color(0,0,0,140));
            gfx2.fillRect(0, 0, Game.WIDTH_SCALE, Game.HEIGHT_SCALE);
            gfx2.drawImage(this.gameOverSequence.get(currentFrameOfGameOver), Game.WIDTH / 2 + 30, Game.HEIGHT / 2 + 50, null);
            gfx2.setColor(new Color(255, 255, 255, 190));
            gfx2.setFont(main_font.deriveFont(35f));
            gfx2.drawString(">> Pressione \"ENTER\" para reiniciar <<", WIDTH_SCALE / 2 - 260, HEIGHT_SCALE / 2 + 60);
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
    	
    	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
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
            	Game.mapName = "/map_1.png";
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

    	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
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
    		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
    			Menu.selectSaveGame = false;
    			Menu.selectDifficult = false;
    		}
        	
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                menu.up = false;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                menu.down = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_ENTER && !Menu.selectSaveGame) {
                Menu.context = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER && Menu.selectSaveGame) {
            	Menu.selectSave();
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER && Menu.selectDifficult) {
            	Menu.selectDifficultSet();
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

    @Override
    public void mousePressed(MouseEvent me) {

        if (me.getButton() == 1) {
        	var arma = player.getGunLeft();
        	if (arma == Entity.GUN_LEFT) {
        		timer = new Timer(100, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	player.shoot = true;
                        player.mx = me.getX() / SCALE;
                        player.my = me.getY() / SCALE;
                    }
                });
                timer.start();
        	}
        	else {
        		if (!player.isWeaponCooldown()) {
        			player.shoot = true;
                    player.mx = me.getX() / SCALE;
                    player.my = me.getY() / SCALE;
        		}
        	}
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	if (timer != null) {
            timer.stop();
        }
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