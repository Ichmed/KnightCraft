package com.ichmed.knightcraft.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class BlockOre extends Block
{
	public HashMap<String, List<Icon>> icons = new HashMap<String, List<Icon>>();
	String oreGroup;
	public static Icon stone;
	public String[] names;

	public BlockOre(int i, String s)
	{
		super(i, Material.rock);
		oreGroup = s;
		if(s.equals("Precious")) names = new String[]{ "Gold" };
		if(s.equals("Basic")) names = new String[]{ "Iron" };
		if(s.equals("Utility")) names = new String[]{ "Coal" };
		if(s.equals("Gem")) names = new String[]{ "Diamond" };
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		stone = iconRegister.registerIcon("stone");
		for (String name : names)
		{
			List l = new ArrayList<Icon>();
			l.add(iconRegister.registerIcon("knightcraft:ore" + name + "Small"));
			l.add(iconRegister.registerIcon("knightcraft:ore" + name + "Medium"));
			l.add(iconRegister.registerIcon("knightcraft:ore" + name + "Large"));
			icons.put(name, l);
		}
	}

	@Override
	public Icon getBlockTexture(IBlockAccess iBlockAcces, int x, int y, int z, int d)
	{
		int i = Math.abs(x + y - z + d) % 4;
		if (i == 3) return stone;
		return icons.get(names[iBlockAcces.getBlockMetadata(x, y, z)]).get(i);
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		return icons.get(names[meta]).get(1);
	}

	@Override
	public String getUnlocalizedName()
	{
		return oreGroup.toLowerCase();
	}
	
	

}
