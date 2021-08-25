package com.projekt.CursedMemories.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	//private final int W = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final int W = Game.WIDTH * Game.SCALE;
	
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
	
	public void tick()
	{
		File file = new File("save.txt");
		if(file.exists())
		{
			saveExists = true;
		}
		else
		{
			saveExists = false;
		}
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
				file = new File("save.txt");
				file.delete();
				paused = false;
				Game.GAME_STATE = 0;	
			}
			else if(curOption == 1)
			{
				file = new File("save.txt");
				if(file.exists()) 
				{
					String saver = loadSave(5);
					applyLoadLevel(saver);
				}
			}
			else if(curOption == 2)
			{
				System.exit(0);
			}
		}
	}
	
	public static void applyLoadLevel(String spr)
	{
		String[] spl = spr.split("/");
		for(int i = 0; i < spl.length; i++)
		{
			String[] spl2 = spl[i].split(";");
			
			System.out.println(spl2[0]);
			
			switch(spl2[0])
			{
			case "mapa":
				World.restartGame("/map_"+spl2[1]+".png", false);
				Game.CUR_LEVEL = Integer.parseInt(spl2[1]);
				paused = false;
			break;
			case "PlayerX":
				Game.player.setX(Integer.parseInt(spl2[1]));
			break;
			case "PlayerY":
				Game.player.setY(Integer.parseInt(spl2[1]));
			break;
			case "vida":
				Game.player.setLife(Integer.parseInt(spl2[1]));
			break;
			case "municao":
				Game.player.setAmmo(Integer.parseInt(spl2[1]));
			break;
			case "reserva":
				Game.player.setReserveAmmo(Integer.parseInt(spl2[1]));
			break;
			case "fire":
				Game.boss_fire_kill = Boolean.parseBoolean(spl2[1]);
			break;
			case "armas":
				if(!Game.player.hasWeapon) {
					Game.player.hasWeapon = true;
				}
				Game.weapon.add(new Weapon(0,0,32,32,Entity.WEAPON_ENT_RIFLE_NON_AUTO, 2));
				Game.player.max_weapon = Game.player.max_weapon + 1;
    				
				if(spl2[1].indexOf("S") != -1) {
					Weapon we = new Weapon(1,1,32,32,Entity.WEAPON_ENT_SHOTGUN, 1);
					Game.weapon.add(we);
					Game.player.max_weapon = Game.player.max_weapon + 1;
				}
			break;
			}
		}
	}
	
	public static String loadSave(int encode)
	{
		String line = "";
		File file = new File("save.txt");
		if(file.exists())
		{
			try 
			{
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try
				{
					while((singleLine = reader.readLine()) != null)
					{
						String[] transition = singleLine.split(":");
						char[] value = transition[1].toCharArray();
						transition[1] = "";
						for(int i = 0; i < value.length; i++)
						{
							value[i] -= encode;
							transition[1] += value[i];
						}
						line += transition[0];
						line += ";";
						line += transition[1];
						line += "/";
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
		return line;
	}
	
	public static void saveGame(String[] val1, String[] val2, int encode)
	{
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new FileWriter("save.txt"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for(int i = 0; i < val1.length; i++)
		{
			String current = val1[i];
			current += ":";
			char[] value = val2[i].toCharArray();
			for(int x = 0; x < value.length; x++)
			{
				value[x] += encode;
				current += value[x];
			}
			try
			{
				writer.write(current);
				if(i < val1.length - 1)
				{
					writer.newLine();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		SaveBeam.bShowSavedMessage = true;
	}
	
	public void render(Graphics gfx)
	{
		//gfx.drawImage(bg, -380, -130, null);
		gfx.drawImage(lstBackGrounds.get(runningMenuNumber), 0, 0, null);
		
		this.changeBG(gfx);
		
		/*
		gfx.setColor(new Color(0,0,0,100));
		
		gfx.fillRect(0,0,Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		
		*/
		gfx.setColor(new Color(255, 255, 255, 255));
		
		gfx.setFont(Game.main_font);
		
		gfx.drawImage(logo, W / 2 - 250, -130, null);

		// opções
		
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
		else if(options[curOption] == "Sair")
		{
			gfx.drawString(">", W / 2 - 85, 350);
			gfx.drawString("<", W / 2 + 50, 350);
		}	
	}
}
