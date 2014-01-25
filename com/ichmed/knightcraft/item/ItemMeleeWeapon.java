package com.ichmed.knightcraft.item;

import java.util.HashMap;
import java.util.List;

import com.ichmed.knightcraft.ElementalDammageType;
import com.ichmed.knightcraft.WeaponRegistry;
import com.ichmed.knightcraft.WeaponRegistry.Weapon;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;

public class ItemMeleeWeapon extends Item
{
	public HashMap<String, Icon> icons = new HashMap<String, Icon>();

	public ItemMeleeWeapon(int par1)
	{
		super(par1);
	}

	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		// TODO Auto-generated method stub
		return super.getStrVsBlock(par1ItemStack, par2Block);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		Weapon w = WeaponRegistry.weapons.get(stack.getTagCompound().getString("name"));
		for(ElementalDammageType e : ElementalDammageType.values())
		{
			int damage = w.damage.get(e) == null ? 0 : w.damage.get(e);
			target.attackEntityFrom(DamageSource.generic, damage);
			
		}
		return true;
	}

	@Override
	public void getSubItems(int i, CreativeTabs creativeTabs, List list)
	{
		for (String s : WeaponRegistry.weapons.keySet())
		{
			ItemStack stack = new ItemStack(this);
			NBTTagCompound tagCompound = new NBTTagCompound();
			tagCompound.setString("name", s);
			stack.setTagCompound(tagCompound);

			list.add(stack);
		}

	}

	@Override
	public Icon getIconIndex(ItemStack stack)
	{
		if (stack.getTagCompound() != null) return icons.get(stack.getTagCompound().getString("name"));
		return icons.get("default");
	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		if (stack.getTagCompound() != null) return icons.get(stack.getTagCompound().getString("name"));
		return icons.get("default");
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		icons.put("deafault", iconRegister.registerIcon("iron_sword"));
		for (String s : WeaponRegistry.weapons.keySet())
		{
			icons.put(s, iconRegister.registerIcon("knightcraft:weapon" + s));
		}
	}
	
	

}
