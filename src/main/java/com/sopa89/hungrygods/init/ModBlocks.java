package com.sopa89.hungrygods.init;

import com.sopa89.hungrygods.block.BlockHG;
import com.sopa89.hungrygods.block.BlockItemAltar;
import com.sopa89.hungrygods.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks 
{
	public static final BlockHG itemAltar=new BlockItemAltar();
	
	public static void init()
	{
		GameRegistry.registerBlock(itemAltar, "itemAltar");
	}
	
}
