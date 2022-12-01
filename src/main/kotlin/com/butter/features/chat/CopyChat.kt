package com.butter.features.chat

import net.minecraft.event.ClickEvent
import net.minecraft.util.ChatStyle
import net.minecraft.util.IChatComponent
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

object CopyChat {
    /*
    @SubscribeEvent(receiveCanceled = true)
    fun onChat(e: ClientChatReceivedEvent) {
        if(e.type == 0.toByte()) {
            var message = e.message
            message.chatStyle = ChatStyle().setChatClickEvent(copyThing(message))
        }
    }

    fun copyThing(message: IChatComponent): ClickEvent? {
    }*/
}