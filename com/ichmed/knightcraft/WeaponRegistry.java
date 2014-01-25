package com.ichmed.knightcraft;

import java.util.HashMap;

public class WeaponRegistry
{
	public static HashMap<String, Weapon> weapons = new HashMap<String, Weapon>();
	
	static
	{
		weapons.put("LegendaryDawnSlayer", new Weapon(ElementalDammageType.PHYSICAL, 5, ElementalDammageType.DARK, 10));
		weapons.put("SwordOrichalcum", new Weapon());
	}
	
	public static class Weapon
	{
		public HashMap<ElementalDammageType, Integer> damage = new HashMap<ElementalDammageType, Integer>();
		
		public Weapon(Object... o)
		{
			for(int i = 0; i < o.length; i++)
			{
				damage.put((ElementalDammageType)o[i], (Integer)o[++i]);
			}
		}
	}

}
