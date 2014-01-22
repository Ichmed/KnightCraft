package com.ichmed.knightcraft.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemOre extends Item
{
	public ItemOre(int par1, String s)
	{
		super(par1);
		type = s;
		this.hasSubtypes = true;
	}

	public String type;

	public static String[] names = { "Gold", "Silver", "Mithril", "Orichalcum", "Adamantium", "Iron", "Copper", "Tin" };
	public Icon[] icons;

	public static int getDamageFromName(String s)
	{
		for (int i = 0; i < names.length; i++)
			if (names[i].equals(s)) return i;
		return -1;
	}

	@Override
	public Icon getIconIndex(ItemStack stack)
	{
		return icons[stack.getItemDamage()];
	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return icons[stack.getItemDamage()];
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		icons = new Icon[names.length];
		for (int i = 0; i < names.length; i++)
		{
			icons[i] = iconRegister.registerIcon("knightcraft:" + type + names[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return type + names[stack.getItemDamage()];
	}

	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List list)
	{
		for (int i = 0; i < names.length; i++)
		{
			System.out.println(type + "  " + i);
			list.add(new ItemStack(this, 1, i));
		}
	}

}
