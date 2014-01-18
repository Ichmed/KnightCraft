package com.ichmed.knightcraft.item;

import com.ichmed.knightcraft.block.BlockOre;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockOre extends ItemBlock
{

	public ItemBlockOre(int par1)
	{
		super(par1);
		this.hasSubtypes = true;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return ((BlockOre) Block.blocksList[((ItemBlock) stack.getItem()).getBlockID()]).names[stack.getItemDamage()] + " Ore";
	}

	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, stack.getItemDamage());
	}

}
