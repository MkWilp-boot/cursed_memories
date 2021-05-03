package com.xesque.main;

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

import javax.imageio.ImageIO;

import com.xesque.world.World;

public class Menu 
{
	public String[] options = {"Novo jogo","Carregar jogo","Sair"};
	
	public static int curOption = 0;

	public int maxOption = options.length - 1;
	private int r = 100, g = 100, b = 100;
	public boolean down, up, rr = true, gg = true, bb = true;
	public BufferedImage logo, bg;
	public static boolean paused = false, saveExists = false, saveGame = false, context = false;
	
	public Menu(String path, String bg)
	{
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
		if(rr)
		{
			r++;
		}
		else
		{
			r--;
		}
		if(bb)
		{
			b++;
		}
		else
		{
			b--;
		}
		if(gg)
		{
			g++;
		}
		else
		{
			g--;
		}
			
		
		
		if(r >= 200)
		{
			rr = false;
		}
		if(b >= 200)
		{
			bb = false;
		}
		if(g >= 200)
		{
			gg = false;
		}
		
		
		if(r <= 100)
		{
			rr = true;
		}
		if(b <= 100)
		{
			bb = true;
		}
		if(g <= 100)
		{
			gg = true;
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
					String saver = loadSave(10);
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
			switch(spl2[0])
			{
			case "mapa":
				World.restartGame("/map_"+spl2[1]+".png");
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
				Game.player.life = Integer.parseInt(spl2[1]);
			break;
			case "municao":
				Game.player.ammo = Integer.parseInt(spl2[1]);
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
	
	public static void saveGame(String[] val1, int[] val2, int encode)
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
			char[] value = Integer.toString(val2[i]).toCharArray();
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
	}
	
	
	public void render(Graphics gfx)
	{
		//gfx.setColor(new Color(r,g,b));
		
		gfx.fillRect(0,0,Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		
		gfx.drawImage(bg, 0, -130, null);
		
		gfx.setColor(Color.DARK_GRAY);
		
		gfx.setFont(Game.main_font);
		
		gfx.drawImage(logo, (Game.WIDTH * Game.SCALE) / 2 - 250, -130, null);

		// opções
		
		if(!paused)
		{
			gfx.drawString("Novo Jogo", (Game.WIDTH * Game.SCALE) / 2 - 90, 250);
		}
		else
		{
			gfx.drawString("Retomar", (Game.WIDTH * Game.SCALE) / 2 - 120, 250);
		}
		
		gfx.drawString("Carregar Jogo", (Game.WIDTH * Game.SCALE) / 2 - 130, 300);
		gfx.drawString("Sair", (Game.WIDTH * Game.SCALE) / 2 - 45, 350);
		
		if(options[curOption] == "Novo jogo")
		{
			gfx.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 130, 250);
			gfx.drawString("<", (Game.WIDTH * Game.SCALE) / 2 + 105, 250);
		}
		else if(options[curOption] == "Carregar jogo")
		{
			gfx.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 165, 300);
			gfx.drawString("<", (Game.WIDTH * Game.SCALE) / 2 + 145, 300);
		}
		else if(options[curOption] == "Sair")
		{
			gfx.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 85, 350);
			gfx.drawString("<", (Game.WIDTH * Game.SCALE) / 2 + 50, 350);
		}	
	}
}
