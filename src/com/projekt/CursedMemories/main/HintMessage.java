package com.projekt.CursedMemories.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class HintMessage implements UIMessages{
	
	private boolean nextPhrase;
	private boolean closeDialogue;
	
	TriangleShape triangleShape;
	
	public HintMessage(String[] palavras, String falante) {

		this.setNextPhrase(false);
		this.closeDialogue = false;
		triangleShape = 
				new TriangleShape(new Point2D.Double(440, 190),
		        new Point2D.Double(450, 200), new Point2D.Double(440, 210));
	}

	@Override
	public void render(Graphics gfx) {
		update();
		gfx.setColor(new Color(0,0,0, 100));
		gfx.fillRect(185, 85, 180, 120);
		gfx.setColor(new Color(104, 43, 12));
		gfx.fillRect(190, 90, 180, 120);
		gfx.setColor(Color.YELLOW);
		gfx.fillRect(200, 100, 160, 100);
		gfx.setColor(new Color(124, 63, 12));
		gfx.fillRect(203, 103, 153, 93);
		gfx.setColor(Color.WHITE);
		gfx.setFont(Game.main_font.deriveFont(16f));
		gfx.drawString("Mova-se pelo mapa", 206, 120);
		gfx.drawString("Utilizando as teclas", 206, 145);
		gfx.drawString("W A S D", 206, 170);
	}

	@Override
	public void emitEvent() {
		
	}

	@Override
	public void update() {
		if(isNextPhrase()) {
			setNextPhrase(false);
			closeDialogue = true;
		}
		else if(closeDialogue) {
			Game.isInScene = false;
			Game.currentDialogue++;
		}
	}
	
	public boolean isNextPhrase() {
		return nextPhrase;
	}

	public void setNextPhrase(boolean nextPhrase) {
		this.nextPhrase = nextPhrase;
	}

}

