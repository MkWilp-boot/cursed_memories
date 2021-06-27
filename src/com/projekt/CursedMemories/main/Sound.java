package com.projekt.CursedMemories.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
	private Clip clip;
	public static final Sound musicGB = new Sound("/MENU_BG.wav");
	public static final Sound playerHurt = new Sound("/hurt.wav");
	// MAP 1	
	public static final Sound mp_bg_1 = new Sound("/BATTLE_1.wav");
	
	Sound(String name)
	{
		try
		{
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(name));
			clip = AudioSystem.getClip();
			clip.open( audioIn );
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		this.clip.stop();
	}
	
	public void play() 
	{
		try
		{
			new Thread() 
			{
				public void run()
				{
					if( clip.isRunning() ) 
						clip.stop();
					clip.setFramePosition( 0 );
		            clip.start();
					
				}
			}.start();
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public void loop()
	{
		try
		{
			new Thread() 
			{
				public void run()
				{
					clip.loop(100000000);
				}
			}.start();
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
}
