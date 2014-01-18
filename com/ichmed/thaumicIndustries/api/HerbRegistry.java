package com.ichmed.thaumicIndustries.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeDirection;

public class HerbRegistry
{
	public static HerbRegistry instance = new HerbRegistry();

	public HashMap<String, Herb> herbs = new HashMap<String, Herb>();

	public HerbRegistry()
	{
		Herb h = new Herb().setName("Saltmint").setColourFresh(139, 129, 74).setColourWithered(35, 28, 8);
		h.addEffect("default", new Effect("extinguish"));
		h.addEffect("Withered", new Effect("none"));
		addHerb(h);

		h = new Herb().setName("Koftea").setColourFresh(49, 49, 24).setColourWithered(51, 38, 16);
		h.addEffect("default", new Effect("speed", 200, 1));
		h.addEffect("Withered", new Effect("nausia", 160, 1));
		addHerb(h);

		h = new Herb().setName("Death Bloom").setColourFresh(193, 174, 148).setColourWithered(105, 97, 82);
		h.addEffect("default", new Effect("instakill"));
		h.addEffect("Withered", new Effect("wither", 300, 2));
		addHerb(h);

		h = new Herb().setName("Marury").setColourFresh(61, 69, 36).setColourWithered(32, 30, 11);
		h.addEffect("default", new Effect("nausia", 4000));
		h.addEffect("Dry", new Effect("nausia", 12000));
		h.addEffect("Withered", new Effect("none"));
		addHerb(h);

		h = new Herb().setName("Hop Weed").setColourFresh(194, 192, 45).setColourWithered(101, 93, 128);
		h.addEffect("default", new Effect("catapult", 0d, 1.5d, 0d));
		h.addEffect("Withered", new Effect("flail", 3f));
		addHerb(h);

		h = new Herb().setName("Combustox").setColourFresh(194, 192, 45).setColourWithered(101, 93, 128).setRarity(EnumRarity.rare);
		h.addEffect("default", new Effect("combust", 5, 5));
		h.addEffect("Withered", new Effect("ignite", 30));
		h.addEffect("Dry", new Effect("ignite", 50));
		h.addEffect("Powder", new Effect("ignite", 50));
		h.addEffect("Paste", new Effect("ignite", 50));
		addHerb(h);
	}

	public void addHerb(Herb h)
	{
		herbs.put(h.name, h);
	}

	/**
	 * Calculates the moistness change while the herb based on the Entity's
	 * location
	 * 
	 * @param entity
	 * @return
	 */
	public static int getMoistnessFromEnviroment(Entity entity)
	{
		return getMoistnessFromBiome(entity.worldObj.getBiomeGenForCoords((int) entity.posX, (int) entity.posZ));
	}

	/**
	 * Calculates the moistness change while the herb is in a specific biome
	 * 
	 * @param biome
	 * @return
	 */
	public static int getMoistnessFromBiome(BiomeGenBase biome)
	{
		int rain = biome.getIntRainfall();
		int sun =  0;//biome.getIntTemperature();
		int biomeFactor = 0;
		return rain * 2 + biomeFactor - sun;
	}

	/**
	 * Returns the color used for the state indicator on the Herb-Items
	 * 
	 * @param state
	 * @return
	 */
	public static String getColourForState(String state)
	{
		if (state.equals("Fresh")) return "2";
		if (state.equals("Dry")) return "e";
		if (state.equals("Powder")) return "6";
		if (state.equals("Paste")) return "5";
		return "f";

	}

	public static class Herb
	{
		public String name;
		public int colourFresh = 0xFFFFFF;
		public int colourWithered = 0x000000;

		public EnumRarity rarity = EnumRarity.common;

		public int maxAge = 168000;
		public int startMoistness = 160000;

		public HashMap<String, List<Effect>> effects = new HashMap<String, List<Effect>>();

		/**
		 * Sets the name of the herb
		 * 
		 * @param name
		 * @return the herb
		 */
		public Herb setName(String name)
		{
			this.name = name;
			return this;
		}

		/**
		 * Sets the maximal age of the herb this will determine how long it
		 * takes for a fresh herb to become withered
		 * 
		 * @param maxAge
		 * @return
		 */
		public Herb setMaxAge(int maxAge)
		{
			this.maxAge = maxAge;
			return this;
		}

		/**
		 * Sets the color of the herb while it's fresh
		 * 
		 * @param colour
		 * @return the herb
		 */
		public Herb setColourFresh(int colour)
		{
			this.colourFresh = colour;
			return this;
		}

		/**
		 * Sets the color of the herb while it's fresh
		 * 
		 * @param red
		 * @param green
		 * @param blue
		 * @return
		 */
		public Herb setColourFresh(int red, int green, int blue)
		{
			return this.setColourFresh(red * 65536 + green * 256 + blue);
		}

		/**
		 * Sets the color of the herb while it's withered
		 * 
		 * @param colour
		 * @return
		 */
		public Herb setColourWithered(int colour)
		{
			this.colourWithered = colour;
			return this;
		}

		/**
		 * Sets the color of the herb while it's withered
		 * 
		 * @param red
		 * @param green
		 * @param blue
		 * @return
		 */
		public Herb setColourWithered(int red, int green, int blue)
		{
			return this.setColourWithered(red * 65536 + green * 256 + blue);
		}

		/**
		 * Sets the rarity of the herb <br>
		 * Is used for displaying the Item's rarity
		 * 
		 * @param rarity
		 * @return
		 */
		public Herb setRarity(EnumRarity rarity)
		{
			this.rarity = rarity;
			return this;
		}

		/**
		 * adds a new Effect to the herb
		 * 
		 * @param conditions
		 *            under witch conditions should the effect be triggered <br>
		 *            Format: [ConsumptionMethod][State][?Withered?] (ex:
		 *            EatenFresh, InhaledDryWithered)
		 * @param effect
		 * @return
		 */
		public Herb addEffect(String conditions, Effect effect)
		{
			List<Effect> l = effects.get(conditions);
			if (l == null) l = new ArrayList<Effect>();
			l.add(effect);
			effects.put(conditions, l);
			return this;
		}

		/**
		 * Returns a list of effects that would be triggered based on the given
		 * conditions
		 * 
		 * @param conditions
		 * @return
		 */
		public List<Effect> getEffects(String conditions)
		{
			List<Effect> e;
			if ((e = effects.get(conditions)) != null) return e;
			else if (conditions.contains("Withered") && (e = effects.get("Withered")) != null) return e;
			else if (conditions.contains("Dry") && (e = effects.get("Dry")) != null) return e;
			else if (conditions.contains("Powder") && (e = effects.get("Powder")) != null) return e;
			else if (conditions.contains("Paste") && (e = effects.get("Paste")) != null) return e;
			else if (conditions.contains("Eaten") && (e = effects.get("Eaten")) != null) return e;
			else if (conditions.contains("Drunk") && (e = effects.get("Drunk")) != null) return e;
			else if (conditions.contains("Inhaled") && (e = effects.get("Inhaled")) != null) return e;
			return effects.get("default");
		}

		/**
		 * Applies all effects triggered by the given conditions to the given
		 * Entity
		 * 
		 * @param entity
		 * @param conditions
		 */
		public void applyEffects(EntityLivingBase entity, String conditions)
		{
			List<Effect> l = getEffects(conditions);
			for (Effect e : l)
				e.apply(entity);
		}
	}

	/**
	 * Represents an Effect that can be applied via a herb coupled with herb
	 * specific parameters
	 * 
	 * @author Ichmed
	 * 
	 */
	public static class Effect
	{
		public BasicEffect effect;
		public Object[] param;

		public Effect(String name, Object... param)
		{
			super();
			this.effect = BasicEffect.effects.get(name);
			this.param = param;
		}

		public boolean apply(EntityLivingBase entity)
		{
			return effect.apply(entity, param);
		}
	}

	/**
	 * Represents a basic herb effect
	 * 
	 * @author Ichmed
	 * 
	 */
	public static abstract class BasicEffect
	{

		/**
		 * A list of all basic herb effects
		 */
		public static HashMap<String, BasicEffect> effects = new HashMap<String, BasicEffect>();

		/**
		 * Applies the specific effect to an Entity <br>
		 * Only called by HerbRegistry.Effect.apply(EntityLivingBase entity)
		 * 
		 * @param entity
		 * @param parameters
		 * @return
		 */
		public abstract boolean apply(EntityLivingBase entity, Object... parameters);

		static
		{
			/**
			 * Does nothing whatsoever
			 */
			effects.put("none", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					return true;
				}
			});

			/**
			 * Extinguishes the Entity
			 */
			effects.put("extinguish", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.extinguish();
					return true;
				}
			});

			/**
			 * Ignites the Entity with a duration of param[0]
			 */
			effects.put("ignite", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.setFire((Integer) objects[0]);
					return true;
				}
			});

			/**
			 * Gives the Entity a speed-buff of duration param[0] and power
			 * param[1]
			 */
			effects.put("speed", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, (Integer) objects[0], (Integer) objects[1]));
					return true;
				}
			});

			/**
			 * Instantly kills the entity
			 */
			effects.put("instakill", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.setHealth(0);
					return true;
				}
			});

			/**
			 * Gives the Entity a nausia effect of duration param[0]
			 */
			effects.put("nausia", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.addPotionEffect(new PotionEffect(Potion.confusion.id, (Integer) objects[0]));
					return true;
				}
			});

			/**
			 * Gives the Entity a poison-debuff of duration param[0]
			 */
			effects.put("poison", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.addPotionEffect(new PotionEffect(Potion.poison.id, (Integer) objects[0]));
					return true;
				}
			});

			/**
			 * Gives the Entity a wither-debuff of duration param[0]
			 */
			effects.put("wither", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.addPotionEffect(new PotionEffect(Potion.wither.id, (Integer) objects[0]));
					return true;
				}
			});

			/**
			 * Ignites the entity for Duration param[0]*20 Causes an explosion
			 * at the entities position of power param[0] and range param[1]
			 * Ignites the surrounding of the entity in a param[1]² field
			 */
			effects.put("combust", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					int strength = (Integer) objects[0];
					int range = (Integer) objects[1];
					e.setFire(strength * 20);
					e.worldObj.createExplosion(null, e.posX, e.posY, e.posZ, strength, true);
					for (int x = (int) e.posX - range / 2; x < e.posX + range / 2; x++)
					{
						for (int y = (int) e.posY - range / 2; y < e.posY + range / 2; y++)
						{
							for (int z = (int) e.posZ - range / 2; z < e.posZ + range / 2; z++)
							{
								World w = e.worldObj;
								if (w.getBlockId(x, y, z) == 0 && w.isBlockSolidOnSide(x, y - 1, z, ForgeDirection.UP)) w.setBlock(x, y, z, Block.fire.blockID);
							}
						}
					}
					return true;
				}
			});

			/**
			 * Cataplults the Entity with the strenth X: param[0] Y: param[1] Z:
			 * param[2]
			 */
			effects.put("catapult", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.motionX += (Double) objects[0];
					e.motionY += (Double) objects[1];
					e.motionZ += (Double) objects[2];
					return true;
				}
			});

			/**
			 * Cataplults the Entity in a random direction
			 */
			effects.put("flail", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					float f = (Float) objects[0];
					e.motionX += (Math.random() - 0.5d) * f;
					e.motionY += (Math.random() - 0.5d) * f;
					e.motionZ += (Math.random() - 0.5d) * f;
					return true;
				}
			});

			effects.put("noclip", new BasicEffect()
			{

				@Override
				public boolean apply(EntityLivingBase e, Object... objects)
				{
					e.noClip = true;
					return true;
				}
			});
		}
	}
	
	public static enum HerbState
	{
		Fresh(null),
		Dry(HerbState.Fresh),
		Powder(HerbState.Dry),
		Paste(HerbState.Powder);
		
		public HerbState parentState;
		
		HerbState(HerbState parent)
		{
			parentState = parent;
		}
		
		public static HerbState getHerbState(String name)
		{
			if(name.equals("Fresh"))return Fresh;
			if(name.equals("Dry"))return Dry;
			if(name.equals("Powder"))return Powder;
			if(name.equals("Paste"))return Paste;
			return Fresh;			
		}
	}
}
