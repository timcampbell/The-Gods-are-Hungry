package com.sopa89.hungrygods.init;

import net.minecraft.tileentity.TileEntity;

import com.sopa89.hungrygods.block.BlockHG;
import com.sopa89.hungrygods.block.BlockItemAltar;
import com.sopa89.hungrygods.reference.Reference;
import com.sopa89.hungrygods.tileEntity.TileEntityItemAltar;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks 
{
	public static final BlockHG itemAltar=new BlockItemAltar();
	
	public static void init()
	{
		GameRegistry.registerBlock(itemAltar, "itemAltar");
	}
	
	public static void initTileEntities()
	{
		TileEntity.addMapping(TileEntityItemAltar.class, Reference.ITEM_ALTAR_TILE_ENTITY_KEY);	
	}
	
}
