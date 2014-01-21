package com.ichmed.knightcraft;

import java.lang.reflect.Field;

import com.ichmed.knightcraft.block.BlockOre;
import com.ichmed.knightcraft.item.ItemOre;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "knightcraft", name = "Knight Craft")
public class KnightCraft
{
	public static Item oreCluster, ingot;

	public static Block preciousOre;
	public static Block basicOre;
	public static Block utilityOre;

	@EventHandler
	public static void init(FMLInitializationEvent event)
	{

		oreCluster = new ItemOre(5000, "oreCluster").setCreativeTab(CreativeTabs.tabMaterials);
		
		ingot = new ItemOre(5001, "ingot").setCreativeTab(CreativeTabs.tabMaterials);

		Block.blocksList[14] = null;
		preciousOre = new BlockOre(14, "Precious").setCreativeTab(CreativeTabs.tabBlock).setHardness(1.0f);

		Block.blocksList[15] = null;
		basicOre = new BlockOre(15, "Basic").setCreativeTab(CreativeTabs.tabBlock).setHardness(1.0f);

		Block.blocksList[16] = null;
		utilityOre = new BlockOre(16, "Utility").setCreativeTab(CreativeTabs.tabBlock).setHardness(1.0f);

		GameRegistry.registerBlock(preciousOre, com.ichmed.knightcraft.item.ItemBlockOre.class, "Precious Ore");
		GameRegistry.registerBlock(basicOre, com.ichmed.knightcraft.item.ItemBlockOre.class, "Basic Ore");
		GameRegistry.registerBlock(utilityOre, com.ichmed.knightcraft.item.ItemBlockOre.class, "Utility Ore");

		LanguageRegistry.addName(new ItemStack(oreCluster, 1, 0), "Gold Ore Cluster");
		LanguageRegistry.addName(new ItemStack(oreCluster, 1, 1), "Silver Ore Cluster");
		LanguageRegistry.addName(new ItemStack(oreCluster, 1, 2), "Mithril Ore Cluster");
		LanguageRegistry.addName(new ItemStack(oreCluster, 1, 3), "Orichalcum Ore Cluster");
		LanguageRegistry.addName(new ItemStack(oreCluster, 1, 4), "Adamantium Ore Cluster");
		LanguageRegistry.addName(new ItemStack(oreCluster, 1, 5), "Iron Ore Cluster");
		LanguageRegistry.addName(new ItemStack(oreCluster, 1, 6), "Copper Ore Cluster");
		LanguageRegistry.addName(new ItemStack(oreCluster, 1, 7), "Tin Ore Cluster");

		LanguageRegistry.addName(new ItemStack(ingot, 1, 0), "Gold Ingot");
		LanguageRegistry.addName(new ItemStack(ingot, 1, 1), "Silver Ingot");
		LanguageRegistry.addName(new ItemStack(ingot, 1, 2), "Mithril Ingot");
		LanguageRegistry.addName(new ItemStack(ingot, 1, 3), "Orichalcum Ingot");
		LanguageRegistry.addName(new ItemStack(ingot, 1, 4), "Adamantium Ingot");
		LanguageRegistry.addName(new ItemStack(ingot, 1, 5), "Iron Ingot");
		LanguageRegistry.addName(new ItemStack(ingot, 1, 6), "Copper Ingot");
		LanguageRegistry.addName(new ItemStack(ingot, 1, 7), "Tin Ingot");

		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 0), "Gold Ore");
		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 1), "Silver Ore");
		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 2), "Mithril Ore");
		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 3), "Orichalcum Ore");
		LanguageRegistry.addName(new ItemStack(preciousOre, 1, 4), "Adamantium Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 0), "Iron Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 1), "Copper Ore");
		LanguageRegistry.addName(new ItemStack(basicOre, 1, 2), "Tin Ore");
		LanguageRegistry.addName(new ItemStack(utilityOre, 1, 0), "Coal Ore");
		LanguageRegistry.addName(new ItemStack(utilityOre, 1, 1), "Sulfur Ore");
		LanguageRegistry.addName(new ItemStack(utilityOre, 1, 2), "Salpeter Ore");
		LanguageRegistry.addName(new ItemStack(utilityOre, 1, 3), "Salt Ore");

	}

}
