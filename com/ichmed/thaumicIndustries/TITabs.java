package com.ichmed.thaumicIndustries;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.ichmed.thaumicIndustries.api.HerbRegistry;
import com.ichmed.thaumicIndustries.api.HerbRegistry.Herb;

public class TITabs extends CreativeTabs
{

	public TITabs(String par2Str)
	{
		super(par2Str);
	}

	@Override
	public ItemStack getIconItemStack()
	{
		Herb h = HerbRegistry.instance.herbs.get("Saltmint");
		ItemStack stack = new ItemStack(ThaumicIndustries.herb);
		NBTTagCompound n = new NBTTagCompound();
		n.setString("name", "Saltmint");
		n.setString("state", "Saltmint");
		n.setDouble("age", 0);
		n.setInteger("maxAge", h.maxAge);
		n.setBoolean("withered", false);
		if (n.getString("state").equals("Fresh")) n.setInteger("moistness", h.startMoistness);
		else n.setInteger("moistness", 0);
		stack.setTagCompound(n);

		return stack;
	}

	@Override
	public Item getTabIconItem()
	{
		return ThaumicIndustries.herb;
	}

}
