package com.butter.features.gui

import com.butter.events.impl.GuiContainerEvent.GuiContainerEvent
import net.minecraft.inventory.ContainerChest
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object GuiFeatures {
    @SubscribeEvent
    fun onOpenGuiEvent(e: GuiContainerEvent) {
        if(e.container is ContainerChest) {

        }
    }
}