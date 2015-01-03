package com.sopa89.hungrygods.tileEntity;

import com.sopa89.hungrygods.handler.ConfigurationHandler;

import net.minecraft.tileentity.TileEntity;

public class TileEntityItemAltar extends TileEntity
{
	private final int MAX_TIME;
	
	private int timer;
	private int minutes;
	private int seconds;
	
	public TileEntityItemAltar()
	{
		super();
		MAX_TIME=ConfigurationHandler.itemAltarTime;
		calcTimes();
	}
	
	private void calcTimes()
	{
		minutes=timer/60;
		seconds=timer%60;
	}
	
	private void countDown()
	{
		timer--;
		calcTimes();
	}
}
