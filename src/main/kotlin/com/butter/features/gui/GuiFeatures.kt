package com.butter.features.gui

import ButterExtras
import com.butter.config.Config
import com.butter.events.GuiContainerEvent
import com.butter.util.Extensions.lore
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.ContainerChest
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent


object GuiFeatures {
    @SubscribeEvent
    fun onDrawSlot(e: GuiContainerEvent.DrawSlotEvent) {
        if(e.gui !is GuiChest && Config.AutoBazaarClaimOrder) return
        print("works")
        val chest = e.gui.inventorySlots
        if (chest is ContainerChest) {
            val name = chest.lowerChestInventory.displayName.formattedText
            if (name.contains("Bazaar ➜")) {
                slotClick(chest.windowId, 50)
            }
            if(name.contains("Your Bazaar Orders")) {
                try {
                    chest.inventorySlots.firstOrNull() {
                        it.stack.lore?.contains("Click to claim!") == true
                    }?.let {
                        slotClick(chest.windowId, it.slotNumber)  }
                } catch (e: Exception) {
                }
            }
            ButterExtras.autoBz = false
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
