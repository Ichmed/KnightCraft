package com.ichmed.thaumicIndustries.common;

import java.util.HashMap;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.ichmed.thaumicIndustries.ThaumicIndustries;
import com.ichmed.thaumicIndustries.api.HerbRegistry;
import com.ichmed.thaumicIndustries.api.HerbRegistry.Herb;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemHerb extends ItemFood
{
	private static HashMap<String, Icon> icons = new HashMap<String, Icon>();

	public ItemHerb(int par1)
	{
		super(par1, 0, true);
	}

	@Override
	public void onCreated(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onCreated(stack, par2World, par3EntityPlayer);
		NBTTagCompound n = new NBTTagCompound();
		n.setString("name", "#ERROR");
		n.setString("state", "#ERROR");
		stack.setTagCompound(n);
		System.out.println("blub");
	}

	@Override
	public void onUpdate(ItemStack stack, World par2World, Entity entity, int par4, boolean par5)
	{
		if (stack.getTagCompound() == null)
		{
			NBTTagCompound n = new NBTTagCompound();
			n.setString("name", "#ERROR");
			n.setString("state", "#ERROR");
			n.setDouble("age", 0);
			n.setInteger("moistness", 0);
			n.setInteger("maxAge", 100);
			stack.setTagCompound(n);
		} else
		{
			NBTTagCompound n = stack.getTagCompound();
			Herb h = HerbRegistry.instance.herbs.get(n.getString("name"));
			double age = n.getDouble("age");
			int maxAge = n.getInteger("maxAge");
			int moistness = n.getInteger("moistness");

			if (age >= maxAge) n.setBoolean("withered", true);
			else if (n.getString("state").equals("Fresh")) n.setDouble("age", age + (moistness / 100000d));

			if (moistness <= 0 && n.getString("state").equals("Fresh")) n.setString("state", "Dry");
			else if (n.getString("state").equals("Fresh")) n.setInteger("moistness", Math.min(moistness + HerbRegistry.getMoistnessFromEnviroment(entity) / 10000, h.startMoistness));

		}
		super.onUpdate(stack, par2World, entity, par4, par5);
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World par2World, EntityPlayer player)
	{
		NBTTagCompound n = stack.getTagCompound();
		Herb h = HerbRegistry.instance.herbs.get(n.getString("name"));
		String state = n.getString("state");
		String withered = n.getBoolean("withered") ? "Withered" : "";
		h.applyEffects(player, "Eaten" + state + withered);
		return super.onEaten(stack, par2World, player);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(stack, player, list, par4);
		if (stack.getTagCompound() == null) return;
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) list.add("(Hold SHIFT for more Information)");
		else
		{
			NBTTagCompound n = stack.getTagCompound();
			Herb h = HerbRegistry.instance.herbs.get(n.getString("name"));
			String state = n.getString("state");
			list.add("§" + HerbRegistry.getColourForState(state) + state);
			if (stack.getTagCompound().getBoolean("withered")) list.add("§8Withered");
			list.add("§7Decay:§f     " + ((int) n.getDouble("age")) / (h.maxAge / 100) + "%");
			list.add("§9Moistness:§f " + n.getInteger("moistness") / (h.startMoistness / 100) + "%");
		}

	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if (stack.getTagCompound() == null) return "Herb";
		return stack.getTagCompound().getString("name");
	}

	@Override
	public boolean getHasSubtypes()
	{
		return true;
	}

	@Override
	public int getMaxDamage()
	{
		return 100;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return HerbRegistry.instance.herbs.get(stack.getTagCompound().getString("name")).rarity;
	}

	@Override
	public Icon getIconIndex(ItemStack stack)
	{
		if (stack.getTagCompound() != null)
		{
			String state = stack.getTagCompound().getString("state");
			String withered = stack.getTagCompound().getBoolean("withered") ? "Withered" : "";
			if (state.equals("Fresh")) return icons.get(stack.getTagCompound().getString("name") + withered);
			else if (state.equals("Dry")) return icons.get(stack.getTagCompound().getString("name") + withered + "Dry");
			else if (state.equals("Powder")) return icons.get("powder");
			else if (state.equals("Paste")) return icons.get("paste");
		}

		return icons.get("Saltmint");
	}

	@Override
	public int getColorFromItemStack(ItemStack stack, int par2)
	{
		if (stack.getTagCompound() != null)
		{
			String name = stack.getTagCompound().getString("name");
			String state = stack.getTagCompound().getString("state");
			boolean b = stack.getTagCompound().getBoolean("withered");
			Herb h = HerbRegistry.instance.herbs.get(name);
			if (state.equals("Fresh") || state.equals("Dry")) return super.getColorFromItemStack(stack, par2);
			else return b ? h.colourWithered : h.colourFresh;
		}
		return 0xFFFFFF;
	}

	@Override
	public void registerIcons(IconRegister register)
	{
		for (String name : HerbRegistry.instance.herbs.keySet())
		{
			icons.put(name, register.registerIcon("thaumicindustries:" + name.toLowerCase().replace(" ", "")));
			icons.put(name + "Dry", register.registerIcon("thaumicindustries:" + name.toLowerCase().replace(" ", "") + "Dry"));
			icons.put(name + "Withered", register.registerIcon("thaumicindustries:" + name.toLowerCase().replace(" ", "") + "Withered"));
			icons.put(name + "WitheredDry", register.registerIcon("thaumicindustries:" + name.toLowerCase().replace(" ", "") + "WitheredDry"));
		}
		icons.put("powder", register.registerIcon("thaumicindustries:herbPowder"));
		icons.put("paste", register.registerIcon("thaumicindustries:herbPaste"));
	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return getIconIndex(stack);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List list)
	{
		String[] c = { "Fresh", "Dry", "Powder", "Paste" };

		for (String name : HerbRegistry.instance.herbs.keySet())
		{
			for (String state : c)
			{
				Herb h = HerbRegistry.instance.herbs.get(name);
				ItemStack stack = new ItemStack(ThaumicIndustries.herb);
				NBTTagCompound n = new NBTTagCompound();
				n.setString("name", name);
				n.setString("state", state);
				n.setDouble("age", 0);
				n.setInteger("maxAge", h.maxAge);
				n.setBoolean("withered", false);
				if (n.getString("state").equals("Fresh")) n.setInteger("moistness", h.startMoistness);
				else n.setInteger("moistness", 0);
				stack.setTagCompound(n);

				list.add(stack);

				stack = new ItemStack(ThaumicIndustries.herb);
				n = new NBTTagCompound();
				n.setString("name", name);
				n.setString("state", state);
				n.setDouble("age", h.maxAge);
				n.setInteger("maxAge", h.maxAge);
				n.setBoolean("withered", true);
				if (n.getString("state").equals("Fresh")) n.setInteger("moistness", h.startMoistness);
				else n.setInteger("moistness", 0);
				stack.setTagCompound(n);

				list.add(stack);
			}
		}
	}
}
