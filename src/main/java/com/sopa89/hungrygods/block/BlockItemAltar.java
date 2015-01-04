package com.sopa89.hungrygods.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
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
	
	public boolean isObserved(World world, int x, int y, int z, int side)
	{
		TileEntity tileEntity=world.getTileEntity(x, y, z);
		if(tileEntity!=null && tileEntity instanceof TileEntityItemAltar)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if(world.getTileEntity(x, y, z) instanceof TileEntityItemAltar)
		{
			TileEntityItemAltar altar=(TileEntityItemAltar)(world.getTileEntity(x, y, z));
			if(!world.isRemote)
			{
				if(Minecraft.getMinecraft().thePlayer.getHeldItem()==null)
				{
					if(altar.getIntTime()>0)
					{
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("msg.itemTime.txt").appendText(altar.getTime()));
					}
					else
					{
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation("msg.itemOverTime.txt").appendText(altar.getOverTime()));
					}
				}
//				else
//					altar.resetTimer();
			}
			return true;
		}
		return false;
	}
	
	
}
