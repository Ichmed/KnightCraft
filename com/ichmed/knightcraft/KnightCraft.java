package com.ichmed.knightcraft;

import java.lang.reflect.Field;

import com.ichmed.knightcraft.block.BlockOre;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
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

		GameRegistry.registerBlock(preciousOre, com.ichmed.knightcraft.item.ItemBlockOre.class, "Precious Ore");
		GameRegistry.registerBlock(basicOre, com.ichmed.knightcraft.item.ItemBlockOre.class, "Basic Ore");
		GameRegistry.registerBlock(utilityOre, com.ichmed.knightcraft.item.ItemBlockOre.class, "Utility Ore");

		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 0), "Gold Ore");
		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 1), "Silver Ore");
		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 2), "Mithril Ore");
		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 3), "Orichalcum Ore");
		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 4), "Adamantium Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 0), "Iron Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 1), "Copper Ore");
		LanguageRegistry.addName(new ItemStack(utilityOre, 1, 0), "Coal Ore");
		LanguageRegistry.addName(new ItemStack(utilityOre, 1, 1), "Sulfur Ore");
		LanguageRegistry.addName(new ItemStack(utilityOre, 1, 2), "Salpeter Ore");
		LanguageRegistry.addName(new ItemStack(utilityOre, 1, 3), "Salt Ore");

	}

}
