package com.sopa89.hungrygods.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.sopa89.hungrygods.tileEntity.TileEntityItemAltar;

public class BlockItemAltar extends BlockHG implements ITileEntityProvider
{
	public BlockItemAltar()
	{
		super();
		this.setBlockName("itemAltar");
		this.setBlockTextureName("itemAltar");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileEntityItemAltar();
	}
	
	
}
