package com.ichmed.knightcraft.item;

import java.util.HashMap;
import java.util.List;

import com.ichmed.knightcraft.WeaponRegistry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;

public class ItemMeleeWeapon extends ItemSword
{
	public HashMap<String, Icon> icons = new HashMap<String, Icon>();

	public ItemMeleeWeapon(int par1)
	{
		super(par1, EnumToolMaterial.IRON);
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
		target.attackEntityFrom(DamageSource.generic, 10);
		return true;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		super.getSubItems(par1, par2CreativeTabs, par3List);
	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
	{
		return icons.get("LegendaryDawnSlayer");
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		for(String s : WeaponRegistry.weapons.keySet())
		{
			icons.put(s, iconRegister.registerIcon("knightcraft:weapon" + s));
		}
	}
	
	
	
	
	
}
