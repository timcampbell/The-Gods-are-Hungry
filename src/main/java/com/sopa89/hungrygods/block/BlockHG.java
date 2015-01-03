package com.sopa89.hungrygods.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.sopa89.hungrygods.creativeTab.CreativeTabHG;

public class BlockHG extends Block
{

	public BlockHG(Material material)
	{
		super(material);
		this.setCreativeTab(CreativeTabHG.HG_TAB);
	}
	
	public BlockHG()
	{
		super(Material.rock);
		this.setCreativeTab(CreativeTabHG.HG_TAB);
	}

}
