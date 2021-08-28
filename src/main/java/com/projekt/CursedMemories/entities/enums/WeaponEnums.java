package com.projekt.CursedMemories.entities.enums;

public enum WeaponEnums {

	SHOTGUN(1),
	RIFLE_NON_AUTO(2),
	RIFILE_FULL_AUTO(3);
	
	private int cod;

	private WeaponEnums(int cod) {
		this.cod = cod;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public static WeaponEnums toEnum(int cod) {
		for(WeaponEnums we : WeaponEnums.values()) {
			if(cod == we.getCod()) {
				return we;
			}
		}
		throw new IllegalArgumentException("Id inválido para WEAPON, ID: " + cod + " não é um WEAPON");
	}
}
