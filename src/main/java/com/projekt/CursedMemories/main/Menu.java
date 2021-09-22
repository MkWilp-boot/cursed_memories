package com.projekt.CursedMemories.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import com.projekt.CursedMemories.entities.Entity;
import com.projekt.CursedMemories.entities.SaveBeam;
import com.projekt.CursedMemories.entities.Weapon;
import com.projekt.CursedMemories.world.World;

public class Menu 
{
	public String[] options = {"Novo jogo","Carregar jogo","Sair"};
	
	public static int curOption = 0;
	
	private final int W = Toolkit.getDefaultToolkit().getScreenSize().width;
	//private final int W = Game.WIDTH * Game.SCALE;
	
	private Boolean topDown = true;
	private Integer R = 0, A = 0;
	private Integer maxOption = options.length - 1;
	private Integer runningMenuNumber = 0;
	private Integer numberOfBackGrounds = 0;
	private Integer coolDownBG = 0;
	private Integer maxCoolDownBG = 255;
	private List<String> lstBackGroundNames = Arrays.asList("BG_0.png", "BG_1.png");
	private List<BufferedImage> lstBackGrounds = new ArrayList<>();
	public boolean down, up, rr = true, gg = true, bb = true;
	public BufferedImage logo, bg;
	public static boolean paused = false, saveExists = false, saveGame = false, context = false;
	public static boolean selectSaveGame = false;
	public static boolean selectDifficult = false;
	public static boolean excededSaveLimit = false;
	// public static boolean selectDifficult = false;
	
	public Menu(String path, String bg)
	{
		lstBackGroundNames
			.stream()
			.forEach(e -> {
				File f = new File(Game.ROOT_DIR+"\\res\\"+e);
				try { this.lstBackGrounds.add(ImageIO.read(f)); }
				catch (IOException e1) { e1.printStackTrace(); }
			});
		this.numberOfBackGrounds = lstBackGrounds.size() - 1;
		try 
		{
			this.logo = ImageIO.read(getClass().getResource(path));
			this.bg = ImageIO.read(getClass().getResource(bg));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	private void changeBG(Graphics gfx) {
		gfx.setColor(new Color(0,0,0,0));
		
		if(R >= 255) {
			topDown = false;
		}
		else if(R <= 0) {
			topDown = true;
		}
		
		if(topDown) {
			R++;
		}
		else {
			R--;
		}
		coolDownBG++;
		if(coolDownBG >= maxCoolDownBG - 100) {
			while(R != 255) {
				R++; A++;
				gfx.setColor(new Color(0, 0, 0, A));
			}
		}
		if (coolDownBG >= maxCoolDownBG) {
			coolDownBG = 0;
			runningMenuNumber++;
			R = 0; A = 0;
			if(runningMenuNumber > numberOfBackGrounds) {
				runningMenuNumber = 0;
			}
		}
		gfx.fillRect(0, 0, Game.WIDTH_SCALE, Game.HEIGHT_SCALE);
	}
	
	private static File [] numOfSaves() {
		File dir = new File(".");
		File [] files = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().contains(".pks");
			}
		});

		return files;
	}
	
	public static void selectSave() {
		var save = numOfSaves();
		var file = new File(save[curOption].toString());
		if(file.exists())
		{
			var saver = loadSave(file);
			try {
				applyLoadLevel(saver);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void selectDifficultSet() {
		switch(curOption) {
		case 0:
			Game.difficult = "Dificil";
			break;
		case 1:
			Game.difficult = "Normal";
			break;
		case 2:
			Game.difficult = "Facil";
			break;
		}
		gameNormalFlow();
	}
	
	public static void saveGameHM(HashMap<String, String> hm) throws IOException {
		var numFiles = numOfSaves().length;
		
		if(numFiles < 3) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");

			var file = new File("save"+ now.format(formatter) + ".pks");
			
			var writer = new BufferedWriter(new FileWriter(file));
			
			hm.forEach((key, value) -> {
				var encr = key + "->" + value + '\n';
				try {
					writer.write(encr);
				}
				catch (IOException e) { e.printStackTrace(); }
			});
			
			try {
				writer.flush();
				writer.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			SaveBeam.bShowSavedMessage = true;
			excededSaveLimit = false;
		}
		else {
			excededSaveLimit = true;
		}
	}
	
	private static void gameNormalFlow() {
		paused = false;
		Game.GAME_STATE = 0;
		Game.isGameStarded = true;
		curOption = 0;
		selectDifficult = false;
		selectSaveGame = false;
	}
	
	public void tick()
	{
		if(up)
		{
			up = false;
			curOption--;
			if(curOption < 0)
			{
				curOption = maxOption;
			}
			
		}
		else if(down)
		{
			down = false;
			curOption++;
			if(curOption > maxOption)
			{
				curOption = 0;
			}
		}
		
		if(context)
		{
			context = false;
			if(Menu.curOption == 0)
			{
				if(!Game.isGameStarded)
					selectDifficult = true;
				else
					gameNormalFlow();
			}
			else if(curOption == 1 && !selectDifficult)
			{
				if(numOfSaves().length > 0) {
					selectSaveGame = true;
				}
			}
			else if(curOption == 2 && !selectDifficult && !selectSaveGame)
			{
				System.exit(0);
			}
			
		}
	}
	
	public static void applyLoadLevel(HashMap<String, String> map) throws Exception {
		var enc = new Encryptor();
		String mapa = enc.decrypt(map.get("mapa"));
		String diff = enc.decrypt(map.get("difficult"));

		World.restartGame("/map_"+ mapa +".png", false);
		Game.difficult = diff;
		
		Game.CUR_LEVEL = Integer.valueOf(mapa.trim());
		
		
		map.forEach((key, value) -> {
			final var decrypted = enc.decrypt(value);
			
			if (key.contains("fire")) {
				Game.boss_fire_kill = Boolean.valueOf(decrypted.trim());
			}
			else if (key.contains("PlayerX")) {
				Game.player.setX(Integer.valueOf(decrypted.trim()));
			}
			else if (key.contains("PlayerY")) {
				Game.player.setY(Integer.valueOf(decrypted.trim()));
			}
			else if (key.contains("municao")) {
				Game.player.setAmmo(Integer.valueOf(decrypted.trim()));
			}
			else if (key.contains("gold")) {
				Game.player.setGoldAmount(Integer.valueOf(decrypted.trim()));
			}
			else if (key.contains("vida")) {
				Game.player.setLife(Integer.valueOf(decrypted.trim()));
			}
			else if (key.contains("reserva")) {
				Game.player.setReserveAmmo(Integer.valueOf(decrypted.trim()));
			}
			else if(key.contains("dialogos")) {
				Game.currentDialogue = Integer.valueOf(decrypted.trim());
			}
			else if(key.contains("isInScene")) {
				Game.isInScene = Boolean.valueOf(decrypted.trim());
			}
			else if(key.contains("armas")) {
				System.out.println("armas");
				Game.player.hasWeapon = true;
				
				var w = new Weapon(0,0,32,32,Entity.WEAPON_ENT_RIFLE_NON_AUTO, 2);
				Game.weapon.add(w);
				Game.player.max_weapon = Game.player.max_weapon + 1;
				Game.player.setWeapon(w);
    				
				if(decrypted.indexOf("S") != -1) {
					Weapon we = new Weapon(1,1,32,32,Entity.WEAPON_ENT_SHOTGUN, 1);
					Game.weapon.add(we);
					Game.player.max_weapon = Game.player.max_weapon + 1;
				}
			}
		});
		gameNormalFlow();
	}
	
	public static HashMap<String, String> loadSave(File file)
	{
		var hm = new HashMap<String, String>();
		if(file.exists())
		{
			try
			{
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader(file));
				try
				{
					while((singleLine = reader.readLine()) != null)
					{
						var tokens = singleLine.split("->");
						hm.put(tokens[0], tokens[1]);
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		return hm;
	}

	public void render(Graphics gfx)
	{
		//gfx.drawImage(bg, -380, -130, null);
		gfx.drawImage(lstBackGrounds.get(runningMenuNumber), 0, 0, null);
		
		this.changeBG(gfx);
		
		gfx.setColor(new Color(255, 255, 255, 255));
		
		gfx.setFont(Game.main_font);
		
		gfx.drawImage(logo, W / 2 - 250, -130, null);

		// opções
		if(!selectSaveGame && !selectDifficult) {
			if(!paused) {
				gfx.drawString("Novo Jogo", W / 2 - 90, 250);
			}
			else {
				gfx.drawString("Retomar", W / 2 - 80, 250);
			}
			gfx.drawString("Carregar Jogo", W / 2 - 130, 300);
			gfx.drawString("Sair", W / 2 - 45, 350);
			
			if(options[curOption] == "Novo jogo")
			{
				gfx.drawString(">", W / 2 - 130, 250);
				gfx.drawString("<", W / 2 + 105, 250);
			}
			else if(options[curOption] == "Carregar jogo")
			{
				gfx.drawString(">", W / 2 - 165, 300);
				gfx.drawString("<", W / 2 + 145, 300);
			}
			else if(options[curOption] == "Sair") {
				gfx.drawString(">", W / 2 - 85, 350);
				gfx.drawString("<", W / 2 + 50, 350);
			}
		}
		
		else if(selectDifficult && !Game.isGameStarded) {
			gfx.drawString("Difícil", W / 2 - 60, 270);
			gfx.drawString("Normal", W / 2 - 70, 320);
			gfx.drawString("Fácil", W / 2 - 50, 370);
			
			if(curOption == 0) {
				gfx.drawString(">", W / 2 - 90, 270);
				gfx.drawString("<", W / 2 + 60, 270);
			}
			else if(curOption == 1) {
				gfx.drawString(">", W / 2 - 100, 320);
				gfx.drawString("<", W / 2 + 70, 320);
			}
			else if(curOption == 2) {
				gfx.drawString(">", W / 2 - 90, 370);
				gfx.drawString("<", W / 2 + 50, 370);
			}
		}
		else if(selectSaveGame) {
			File dir = new File(".");
			File [] files = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return pathname.getName().contains(".pks");
				}
			});

			if(curOption == 0) {
				gfx.drawString(">", W / 2 - 290, 270);
				gfx.drawString("<", W / 2 + 320, 270);
			}
			if(curOption == 1) {
				gfx.drawString(">", W / 2 - 290, 330);
				gfx.drawString("<", W / 2 + 320, 330);
			}
			if(curOption == 2) {
				gfx.drawString(">", W / 2 - 290, 390);
				gfx.drawString("<", W / 2 + 320, 390);
			}
			
			if (files.length == 0) {
				gfx.drawString("-------Vazio-------", W / 2 - 250, 270);
				gfx.drawString("-------Vazio-------", W / 2 - 160, 330);
				gfx.drawString("-------Vazio-------", W / 2 - 160, 390);
			}
			else if (files.length == 1) {
				gfx.drawString("-------Vazio-------", W / 2 - 160, 330);
				gfx.drawString("-------Vazio-------", W / 2 - 160, 390);
			}
			else if (files.length == 2) {
				gfx.drawString("-------Vazio-------", W / 2 - 160, 390);
			}
			
			int lastPost = 270;
			for(var f : files) {
				gfx.drawString(f.getName(), W / 2 - 250, lastPost);
				lastPost += 60;
			}
		}
	}
}
