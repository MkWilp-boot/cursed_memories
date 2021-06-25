package com.xesque.entities;

import java.awt.image.BufferedImage;

public class Weapon extends Entity 
{

	private int cod;
	
	public Weapon(int x, int y, int w, int h, BufferedImage sprite, int cod) 
	{
		super(x, y, w, h, sprite);
		this.setCod(cod);
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

}
