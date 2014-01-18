package com.ichmed.thaumicIndustries;

import com.ichmed.thaumicIndustries.common.ItemHerb;
import com.ichmed.thaumicIndustries.common.block.BlockMagicRailroad;
import com.ichmed.thaumicIndustries.common.world.BiomeUndergroundForest;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "thaumicIndustries", name = "Thaumic Industries", version = "0.0.1")
public class ThaumicIndustries
{
	public static ItemFood herb;
	
	public static BlockMagicRailroad magicRailroad; 

	public static TITabs tabHerbs = new TITabs("tabHerbs");

	@EventHandler
	public static void init(FMLInitializationEvent e)
	{
		herb = (ItemFood) new ItemHerb(500).setAlwaysEdible().setCreativeTab(tabHerbs).setUnlocalizedName("herb").setTextureName("thaumicindustries:saltmint");
//		magicRailroad = (BlockMagicRailroad) new BlockMagicRailroad(200, false).setCreativeTab(CreativeTabs.tabTransport);

		BiomeGenBase biome = new BiomeUndergroundForest(50);
		biome.setBiomeName("Underground Forest").setMinMaxHeight(-1, 2);
		GameRegistry.addBiome(biome);
		BiomeDictionary.registerBiomeType(biome, BiomeDictionary.Type.FOREST);
		
//		GameRegistry.registerBlock(magicRailroad, "magicRailroad");

		registerNames();
	}

	public static void registerNames()
	{
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabHerbs", "en_US", "Herbs");

		LanguageRegistry.instance().addStringLocalization("Combustox.name", "en_US", "Combustox");
		LanguageRegistry.instance().addStringLocalization("Saltmint.name", "en_US", "Saltmint");
		LanguageRegistry.instance().addStringLocalization("Koftea.name", "en_US", "Koftea");
		LanguageRegistry.instance().addStringLocalization("Death Bloom.name", "en_US", "Death Bloom");
		LanguageRegistry.instance().addStringLocalization("Hop Weed.name", "en_US", "Hop Weed");
		LanguageRegistry.instance().addStringLocalization("Marury.name", "en_US", "Marury");
	}
}
