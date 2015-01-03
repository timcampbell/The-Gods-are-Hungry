package com.sopa89.hungrygods.tileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;

import com.sopa89.hungrygods.handler.ConfigurationHandler;

public class TileEntityItemAltar extends TileEntity
{
	private final int MAX_TIME;
	
	private int timer;
	private int minutes;
	private int seconds;
	private int ticks=20;
	private boolean messageSent=false;
	
	public TileEntityItemAltar()
	{
		super();
		MAX_TIME=ConfigurationHandler.itemAltarTime;
		timer=MAX_TIME;
		calcTimes();
	}
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			ticks--;
			if(ticks==0)
			{
				countDown();
				ticks=20;
			}
			if(timer<=0)
			{
				timer=MAX_TIME;
			}
			else if((!messageSent)&&(timer==90 || timer==60 || timer==30))
			{
					messageSent=true;
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("msg.itemSacrificeSoon.txt"));
			}
		}
	}
	
	private void calcTimes()
	{
		minutes=timer/60;
		seconds=timer%60;
	}
	
	private void countDown()
	{
		timer--;
		messageSent=false;
		calcTimes();
	}
	
	public String getTime()
	{
		String result;
		result=minutes+":";
		if(seconds<10)
		{
			result+="0"+seconds;
		}
		else
		{
			result+=""+seconds;
		}
		return result;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("Timer", timer);
		compound.setBoolean("Message Sent", messageSent);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		timer=compound.getInteger("Timer");
		messageSent=compound.getBoolean("Message Sent");
	}
}
