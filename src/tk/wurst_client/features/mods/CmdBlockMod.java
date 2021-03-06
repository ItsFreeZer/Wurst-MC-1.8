/*
 * Copyright � 2014 - 2017 | Wurst-Imperium | All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.features.mods;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import tk.wurst_client.gui.mods.GuiCmdBlock;
import tk.wurst_client.utils.ChatUtils;
import tk.wurst_client.utils.InventoryUtils;

@Mod.Info(
	description = "Allows you to make a Command Block without having OP.\n"
		+ "Requires creative mode.\n" + "Appears to be patched on Spigot.",
	name = "CMD-Block",
	tags = "CmdBlock, CommandBlock, cmd block, command block",
	help = "Mods/CMD-Block")
@Mod.Bypasses
public class CmdBlockMod extends Mod
{
	@Override
	public void onEnable()
	{
		// check gamemode
		if(!mc.player.capabilities.isCreativeMode)
		{
			ChatUtils.error("Creative mode only.");
			setEnabled(false);
			return;
		}
		
		// show cmd-block screen
		mc.displayGuiScreen(new GuiCmdBlock(this, mc.currentScreen));
		setEnabled(false);
	}
	
	public void createCmdBlock(String cmd)
	{
		// generate cmd-block
		ItemStack stack = new ItemStack(Blocks.command_block);
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		nbtTagCompound.setTag("Command", new NBTTagString(cmd));
		stack.writeToNBT(nbtTagCompound);
		stack.setTagInfo("BlockEntityTag", nbtTagCompound);
		
		// give cmd-block
		if(InventoryUtils.placeStackInHotbar(stack))
			ChatUtils.message("Command Block created.");
		else
			ChatUtils.error("Please clear a slot in your hotbar.");
	}
}
