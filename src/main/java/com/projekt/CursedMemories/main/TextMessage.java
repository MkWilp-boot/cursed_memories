package com.projekt.CursedMemories.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class TextMessage implements UIMessages{
	
	private String[] palavras;
	private String falante;
	private Integer lastFrase;
	private boolean nextPhrase;
	private boolean closeDialogue;
	
	TriangleShape triangleShape;
	
	public TextMessage(String[] palavras, String falante) {
		this.palavras = palavras;
		this.falante = falante;
		this.lastFrase = 0;
		this.setNextPhrase(false);
		this.closeDialogue = false;
		triangleShape = 
				new TriangleShape(new Point2D.Double(420, 180),
		        new Point2D.Double(430, 190), new Point2D.Double(420, 200));
		
	}
	
	@Override
	public void emitEvent() {
		
	}
	
	@Override
	public void update() {
		
		if(isNextPhrase()) {
			setNextPhrase(false);
			lastFrase++;
			if(lastFrase > palavras.length - 1) {
				closeDialogue = true;
				lastFrase = 0;
			}
		}
		// Desabilita caixa de dialogo
		else if(closeDialogue) {
			Game.isInScene = false;
			Game.currentDialogue++;
			Game.rmvSetToBlack = false;
		}
	}
	
	@Override
	public void render(Graphics gfx) {
		update();
		// Borda
		gfx.setColor(Color.DARK_GRAY);
		gfx.fillRect(0, Game.HEIGHT - 75, Game.WIDTH, 70);
		
		// Retangulo marrom
		gfx.setColor(new Color(124, 63, 12));
		gfx.fillRect(5, Game.HEIGHT - 70, Game.WIDTH - 15, 60);
		
		//gfx.drawImage(Game.spritesheet.getSprite(256, 715, 513, 85), 10, 230, null);
		// Texto
		gfx.setColor(Color.WHITE);
		gfx.setFont(Game.main_font.deriveFont(17f));
		gfx.drawString(this.falante, 40, 275);
		
		gfx.setFont(Game.main_font.deriveFont(13f));
		gfx.drawString(palavras[lastFrase], 40, 295);
		
		Graphics2D g2d = (Graphics2D) gfx.create();
		
	    g2d.translate(50, 100);
	    g2d.fill(triangleShape);
	}

	public boolean isNextPhrase() {
		return nextPhrase;
	}

	public void setNextPhrase(boolean nextPhrase) {
		this.nextPhrase = nextPhrase;
	}
}

class TriangleShape extends Path2D.Double {
	private static final long serialVersionUID = 1L;

	public TriangleShape(Point2D... points) {
	    moveTo(points[0].getX(), points[0].getY());
	    lineTo(points[1].getX(), points[1].getY());
	    lineTo(points[2].getX(), points[2].getY());
	    closePath();
	}
}
