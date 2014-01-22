package com.ichmed.knightcraft;

import java.util.HashMap;

public class WeaponRegistry
{
	public static HashMap<String, Weapon> weapons = new HashMap<String, Weapon>();
	
	static
	{
		weapons.put("LegendaryDawnSlayer", new Weapon());
	}
	
	public static class Weapon
	{
		HashMap<String, Integer> damage = new HashMap<String, Integer>();
	}

}
