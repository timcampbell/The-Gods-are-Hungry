package com.sopa89.hungrygods.tileEntity;

import java.util.Random;

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
	private final int MAX_REQUIRED;
	private final int PERCENT_INCREASE;
	private final int BASE_REQUIREMENT;
	
	private int timer;
	private int ticks=20;
	private int minutes;
	private int seconds;
	
	private int itemCount=0;
	private int requiredItems;
	
	private boolean messageSent=false;
	private boolean mobSpawned=false;
	
	public TileEntityItemAltar()
	{
		super();
		MAX_TIME=ConfigurationHandler.itemAltarTime;
		SPAWN_TIME=ConfigurationHandler.itemAltarSpawnTime;
		timer=MAX_TIME;
		
		MAX_REQUIRED=ConfigurationHandler.itemAltarMax;
		PERCENT_INCREASE=ConfigurationHandler.itemAltarPercentIncrease;
		BASE_REQUIREMENT=ConfigurationHandler.itemAltarBaseRequirement;
		requiredItems=BASE_REQUIREMENT;
	}
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			ticks--;
			if(ticks<=0)
			{
				countDown();
			}
			if(Math.abs(timer)%SPAWN_TIME==0 && timer<=0 && !mobSpawned)
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
	
	private void calcTimes(int time)
	{
		minutes=Math.abs(time)/60;
		seconds=Math.abs(time)%60;
	}
	
	private void countDown()
	{
		timer--;
		ticks=20;
		messageSent=false;
		mobSpawned=false;
	}
	
	private String getTime(int time)
	{
		String result;
		calcTimes(time);
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
	
	private void resetTimer()
	{
		timer=MAX_TIME;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("timer", timer);
		compound.setBoolean("messageSent", messageSent);
		
		compound.setInteger("itemCount", itemCount);
		compound.setInteger("requiredItems", requiredItems);
		
		compound.setBoolean("mobSpawned", mobSpawned);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		timer=compound.getInteger("timer");
		messageSent=compound.getBoolean("messageSent");
		
		itemCount=compound.getInteger("itemCount");
		requiredItems=compound.getInteger("requiredItems");
		
		mobSpawned=compound.getBoolean("mobSpawned");
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
	
	public void makeSacrifice()
	{
		Random rand=new Random();
		itemCount++;
		if(itemCount>=requiredItems)
		{
			resetTimer();
			itemCount=0;
			if(requiredItems<MAX_REQUIRED && rand.nextInt(100)+1<=PERCENT_INCREASE)
			{
				requiredItems++;
				if(!worldObj.isRemote)
				{
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("msg.itemSacrificeIncreased.txt").appendText(requiredItems+""));
				}
			}
		}
	}

	public void displayInfo()
	{
		if(timer>0)
		{
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("msg.itemTime.txt").appendText(getTime(timer)));
		}
		else
		{
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("msg.itemOverTime.txt").appendText(getTime(timer)));
		}
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("msg.currentReq.txt").appendText(requiredItems+""));
	}
}
