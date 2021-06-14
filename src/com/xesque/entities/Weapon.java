package com.xesque.entities;

import java.awt.image.BufferedImage;

public class Weapon extends Entity 
{

	private int cod;
	
	public Weapon(int x, int y, int w, int h, BufferedImage sprite, int cod) 
	{
		super(x, y, w, h, sprite);
		this.cod = cod;
	}

}
