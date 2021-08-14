package com.projekt.CursedMemories.main;

import java.awt.Graphics;

public interface UIMessages {

	public abstract void render(Graphics gfx);
	public abstract void emitEvent();
	public abstract void update();
}
