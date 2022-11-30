package com.butter.features

import ButterExtras.Companion.persistentData
import com.butter.util.Chat
import gg.essential.universal.UChat
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

//FIX LATER//
object FirstTime{
    @SubscribeEvent
    fun onTickEvent(event: TickEvent.ClientTickEvent) {
        if (!persistentData.firstLoad || event.phase != TickEvent.Phase.START) return
        persistentData.firstLoad = false
        persistentData.save()
        UChat.chat("""
                     ${Chat.getChatBreak()}
                    &r&4Welcome to ButterExtras!
                 
                 To get started use &e/be &4for main gui!
                 ${Chat.getChatBreak()}
             """.trimIndent().trimIndent())
    }
}