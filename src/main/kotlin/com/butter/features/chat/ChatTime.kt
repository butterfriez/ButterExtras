package com.butter.features.chat

import ButterExtras
import com.butter.util.MainUtil.getCurrentTime
import gg.essential.universal.UChat
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object ChatTime {
    @SubscribeEvent(receiveCanceled = true)
    fun receivedEvent(e:ClientChatReceivedEvent) {
        if(e.message.formattedText.isNotEmpty() && e.type == 0.toByte() && ButterExtras.config.StampedChat) {
            e.isCanceled = true
            UChat.chat("&7[${"&e" + getCurrentTime() + "&7]"}&r  ${e.message.formattedText}")
        }
    }
}