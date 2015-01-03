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
		
		if(configuration.hasChanged())
		{
			configuration.save();
		}
	}
}
