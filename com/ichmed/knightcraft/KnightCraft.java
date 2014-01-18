package com.ichmed.knightcraft;

import java.lang.reflect.Field;

import com.ichmed.knightcraft.block.BlockOre;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "knightcraft", name = "Knight Craft")
public class KnightCraft
{
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		Block.blocksList[14] = null;
		Block.blocksList[15] = null;
		Block.blocksList[16] = null;
		Block preciousOre = new BlockOre(14, "Precious").setCreativeTab(CreativeTabs.tabBlock);
		Block basicOre = new BlockOre(15, "Basic").setCreativeTab(CreativeTabs.tabBlock);
		Block utilityOre = new BlockOre(16, "Utility").setCreativeTab(CreativeTabs.tabBlock);
		
		
		GameRegistry.registerBlock(preciousOre, "Precious Ore");
		GameRegistry.registerBlock(basicOre, "Basic Ore");
		GameRegistry.registerBlock(utilityOre, "Utility Ore");
		
		LanguageRegistry.addName(preciousOre, "Gold");
		LanguageRegistry.addName(basicOre, "Iron");
		LanguageRegistry.addName(utilityOre, "Coal");
	}

}
