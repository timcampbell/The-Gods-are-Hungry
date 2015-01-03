package com.sopa89.hungrygods.creativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import com.sopa89.hungrygods.reference.Reference;

public class CreativeTabHG 
{
	public static final CreativeTabs HG_TAB=new CreativeTabs(Reference.MOD_ID.toLowerCase())
	{
		@Override
		public Item getTabIconItem()
		{
			return Items.golden_sword;
		}
	};
}
