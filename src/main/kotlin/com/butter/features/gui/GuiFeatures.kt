package com.butter.features.gui

import ButterExtras
import com.butter.config.Config
import com.butter.events.GuiContainerEvent
import com.butter.util.Extensions.lore
import com.butter.util.MainUtil.slotClick
import gg.essential.universal.UChat
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.ContainerChest
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent


object GuiFeatures {
    private var lastClickTime:Long = 0
    private var autoBz = false
    @SubscribeEvent
    fun onDrawSlot(e: GuiContainerEvent.DrawSlotEvent) {
        if(e.gui !is GuiChest) return
        val chest = e.gui.inventorySlots
        if (chest is ContainerChest) {
            val name = chest.lowerChestInventory.displayName.formattedText
            if (name.contains("Bazaar ➜")
                && System.currentTimeMillis() - lastClickTime > Config.AutoBazaarClaimDelay
                && autoBz) {
                slotClick(chest.windowId, 50)
                lastClickTime = System.currentTimeMillis()
            }
            if(name.contains("Your Bazaar Orders")
                && autoBz) {
                chest.inventorySlots.forEach { slot ->
                    if(slot?.stack?.lore?.contains("§eClick to claim!") == true) {
                        if(System.currentTimeMillis() - lastClickTime > Config.AutoBazaarClaimDelay) {
                            slotClick(chest.windowId, slot.slotNumber)
                            lastClickTime = System.currentTimeMillis()
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    fun onGuiClosed(e: GuiOpenEvent) {
        if(e.gui == null) {
            autoBz = false
        }
    }

    @SubscribeEvent
    fun onKeyInput(e: KeyInputEvent) {
        if(ButterExtras.keyBinds["AutoBazaar"]!!.isPressed
            && Config.AutoBazaarClaimOrder) {
            UChat.say("/bz")
            autoBz = true
        }
    }
}