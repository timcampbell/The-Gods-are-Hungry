package com.sopa89.hungrygods.tileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;

import com.sopa89.hungrygods.handler.ConfigurationHandler;

public class TileEntityItemAltar extends TileEntity
{
	private final int MAX_TIME;
	private final int SPAWN_TIME;
	
	private int timer;
	private int overTimer=0;
	private int minutes;
	private int seconds;
	private int ticks=20;
	private boolean messageSent=false;
	private boolean mobSpawned=false;
	
	public TileEntityItemAltar()
	{
		super();
		MAX_TIME=ConfigurationHandler.itemAltarTime;
		SPAWN_TIME=ConfigurationHandler.itemAltarSpawnTime;
		timer=MAX_TIME;
		calcTimes(timer);
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
			if(overTimer%SPAWN_TIME==0 && timer<=0 && !mobSpawned)
			{
				spawnMob();
			}
			else if((timer!=MAX_TIME) && (!messageSent)&&(timer==90 || timer==60 || timer==30))
			{
					messageSent=true;
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("msg.itemSacrificeSoon.txt"));
			}
		}
	}
	
	private void calcTimes(int num)
	{
		minutes=num/60;
		seconds=num%60;
	}
	
	private void countDown()
	{
		if(timer>0)
			timer--;
		else
			overTimer++;
		messageSent=false;
		mobSpawned=false;
		calcTimes(timer);
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
	
	public void resetTimer()
	{
		timer=MAX_TIME;
		overTimer=0;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("Timer", timer);
		compound.setBoolean("messageSent", messageSent);
		compound.setInteger("overTimer", overTimer);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		timer=compound.getInteger("Timer");
		messageSent=compound.getBoolean("messageSent");
		overTimer=compound.getInteger("overTimer");
	}
	
	private void spawnMob()
	{
		mobSpawned=true;
		if(!worldObj.isRemote)
		{
			EntityZombie zombie=new EntityZombie(worldObj);
			zombie.setPosition(xCoord+.5, yCoord+1, zCoord+.5);
			worldObj.spawnEntityInWorld(zombie);
		}
	}

	public int getIntTime()
	{
		return timer;
	}
	
	public String getOverTime()
	{
		String result;
		calcTimes(overTimer);
		result=minutes+":";
		if(seconds<10)
		{
			result+="0"+seconds;
		}
		else
		{
			result+=seconds+"";
		}
		return result;
	}
}
