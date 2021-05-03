package com.xesque.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
	private Clip clip;
	public static final Sound musicGB = new Sound("/music.wav");
	public static final Sound playerHurt = new Sound("/hurt.wav");
	
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
					clip.loop(1000000);
				}
			}.start();
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
}
