package com.ichmed.thaumicIndustries.common.block;

import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;

public class BlockMagicRailroad extends BlockRailBase
{
	public BlockMagicRailroad(int par1, boolean par2)
	{
		super(par1, par2);
	}

	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, int y, int x, int z)
	{
		return 1000;
	}

	@Override
	public void onMinecartPass(World world, EntityMinecart cart, int y, int x, int z)
	{
		double vx = cart.motionX; 
		double vy = cart.motionY; 
		double vz= cart.motionZ; 
		cart.setVelocity(vx + 1,  0, vz + 1);
		super.onMinecartPass(world, cart, y, x, z);
	}

	@Override
	public boolean canMakeSlopes(World world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public boolean isFlexibleRail(World world, int y, int x, int z)
	{
		return false;
	}
	
	
	
}
