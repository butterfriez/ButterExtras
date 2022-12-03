package com.butter.features.gui

import ButterExtras
import com.butter.config.Config
import com.butter.events.GuiContainerEvent
import com.butter.util.Extensions.items
import com.butter.util.Extensions.lore
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.ContainerChest
import net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object GuiFeatures {

    @SubscribeEvent
    fun onGuiDraw(e: GuiContainerEvent.BackgroundDrawnEvent) {
        if (e.gui !is GuiChest /*&& ButterExtras.autoBz*/ && Config.AutoBazaarClaimOrder) return
        val chest = (e.gui as GuiChest).inventorySlots
        if (chest is ContainerChest) {
            val name = chest.lowerChestInventory.displayName.formattedText
            if (name.contains("Bazaar ➜")) {
                slotClick(chest.windowId, 50)
            }
            if(name.contains("Your Bazaar Orders")) {
                for (item in chest.inventorySlots) {
                    val item1 = item.inventory.getStackInSlot(item.slotNumber)
                    if(item1.lore?.contains("Click to claim!") == true) {
                        slotClick(chest.windowId, item.slotNumber)
                    }
                }
            }
        }
    }

    private fun slotClick(windowId: Int, slot: Int) {
        ButterExtras.mc.playerController.windowClick(
            windowId,
            slot,
            0,
            0,
            ButterExtras.mc.thePlayer
        )
    }
}
