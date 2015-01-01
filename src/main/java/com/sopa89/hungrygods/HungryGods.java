package com.sopa89.hungrygods;

//import com.sopa89.hungrygods.client.handler.KeyEventHandler;
//import com.sopa89.hungrygods.handler.ConfigurationHandler;
//import com.sopa89.hungrygods.init.ModBlocks;
//import com.sopa89.hungrygods.init.ModItems;
//import com.sopa89.hungrygods.init.Recipes;
//import com.sopa89.hungrygods.proxy.IProxy;
import com.sopa89.hungrygods.reference.Reference;
//import com.sopa89.hungrygods.utility.LogHelper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory=Reference.GUI_FACTORY_CLASS)
public class HungryGods 
{
	@Mod.Instance(Reference.MOD_ID)
	public static HungryGods instance;
	
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler()
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		proxy.registerKeyBindings();
		
		ModItems.init();
		
		ModBlocks.init();
		
		LogHelper.info("Pre-Initialization Complete!");
	}
	
	@Mod.EventHandler()
	public void init(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(new KeyEventHandler());
		
		Recipes.init();
		
		LogHelper.info("Initialization Complete!");
	}
	
	@Mod.EventHandler()
	public void postInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Post-Initializaiton Complete!");
	}
}
