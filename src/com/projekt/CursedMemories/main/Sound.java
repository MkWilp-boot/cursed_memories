package com.projekt.CursedMemories.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound implements AudioInternal {
    private Clip clip;
    public static final Sound musicGB = new Sound("/BG_MENU.wav");
    public static final Sound playerHurt = new Sound("/PLAYER_HURT.wav");
    // MAP 0
    public static final Sound mp_bg_0 = new Sound("/BG_M_0.wav");

    // MAP 1
    public static final Sound mp_bg_1 = new Sound("/BG_M_1.wav");
    
    // MAP VULCAO	
    public static final Sound mp_bg_v = new Sound("/BATTLE_VULCAO.wav");
    public static final Sound mp_bs_1_v = new Sound("/Fogo.wav");
    public static final Sound mp_bs_2_v = new Sound("/Fogo_2.wav");

    Sound(String name) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(name));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        this.clip.stop();
    }

    @Override
    public void play(Float volume) {
        try {
            new Thread() {
                public void run() {
                    setVolume(volume);
                    if (clip.isRunning())
                        clip.stop();
                    clip.setFramePosition(0);
                    clip.start();
                }
            }.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    @Override
    public void loop(Float volume) {
        new Thread(() -> {
    		setVolume(volume);
            clip.loop(100000000);
    	}).start();
    }
}