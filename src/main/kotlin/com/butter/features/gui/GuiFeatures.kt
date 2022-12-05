package com.butter.features.gui

import ButterExtras
import com.butter.config.Config
import com.butter.events.GuiContainerEvent
import com.butter.util.DiscordWebhook
import com.butter.util.EmbedObject
import com.butter.util.MainUtil.slotClick
import gg.essential.universal.UChat
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.inventory.ContainerChest
import net.minecraftforge.client.event.ClientChatReceivedEvent
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
            //auto bazaar orders | Main Gui Of Bazaar
            if(name.contains("Bazaar ➜") && Config.AutoBazaarClaimOrder && e.slot?.stack?.displayName?.contains("Manage Orders") == true && Config.AutoBazaarClaimClaim == 0 && autoBz) {
                if(System.currentTimeMillis() - lastClickTime > Config.AutoBazaarClaimDelay) {
                    slotClick(chest.windowId, e.slot.slotNumber)
                }
            }

            //Bazaar Orders
            if(name.contains("Bazaar Orders") && Config.AutoBazaarClaimOrder && e.slot?.stack?.displayName?.contains("Click to claim!") == true && Config.AutoBazaarClaimClaim == 0 && autoBz) {
                if(System.currentTimeMillis() - lastClickTime > Config.AutoBazaarClaimDelay) {
                    slotClick(chest.windowId, e.slot.slotNumber)
                }
            } else if(
                name.contains("Bazaar Orders")
                && Config.AutoBazaarClaimOrder
                && e.slot?.stack?.displayName?.contains("Click to claim!") == true
                && e.slot?.stack?.displayName?.contains(ButterExtras.mc.thePlayer.name) == true
                && autoBz)
            {
                if(System.currentTimeMillis() - lastClickTime > Config.AutoBazaarClaimDelay) {
                    slotClick(chest.windowId, e.slot.slotNumber)
                }
            }

            // auto confirm
            if(name.contains("Confirm Purchase") && Config.AutoConfirmAH) {
                if(Config.AutoConfirmAHMethod == 0) {
                    if(e.slot?.stack?.displayName?.contains("Confirm") == true) {
                        if(System.currentTimeMillis() - lastClickTime > Config.AutoConfirmAHClickDelay) {
                            slotClick(chest.windowId, e.slot.slotNumber)
                            lastClickTime = System.currentTimeMillis()
                        }
                    }
                }
            }
            if(name.contains("BIN Auction View") && Config.AutoConfirmAH) {
                if(Config.AutoConfirmAHMethod == 0) {
                    if(e.slot?.stack?.displayName?.contains("Buy Item Right Now") == true) {
                        if(System.currentTimeMillis() - lastClickTime > Config.AutoConfirmAHClickDelay) {
                            slotClick(chest.windowId, e.slot.slotNumber)
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

    /*@SubscribeEvent
    fun onChatReceived(e: ClientChatReceivedEvent) {
        if(e.message.formattedText.contains("You purchased")) {
            UChat.chat("works")
            DiscordWebhook(Config.Webhook)
                .setUsername(ButterExtras.mc.thePlayer.displayName.formattedText)
                .setContent("Purchased")
                .addEmbed(
                    EmbedObject()
                        .addField(
                            "Purchased",
                            e.message.formattedText,
                            false
                        )
                )
                .execute()
        }
    }*/
}

