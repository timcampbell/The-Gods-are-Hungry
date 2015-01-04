package com.sopa89.hungrygods.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.sopa89.hungrygods.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler 
{
	public static Configuration configuration;
	public static int itemAltarTime=600;
	public static int itemAltarSpawnTime=60;
	
	public static void init(File configFile)
	{
		if(configuration==null)
		{
			configuration=new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration()
	{
		itemAltarTime=configuration.getInt("Item Sacrifice Altar Timer", Configuration.CATEGORY_GENERAL, 600, 60, 3600, "Max time between required sacrifices");
		itemAltarSpawnTime=configuration.getInt("Item Altar Spawn Time", configuration.CATEGORY_GENERAL, 60, 10, 90, "Time taken between spawns from the item altar if no sacrifice is made in required time");
		if(configuration.hasChanged())
		{
			configuration.save();
		}
	}
}
