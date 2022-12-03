package com.butter.features.chat

import ButterExtras.Companion.persistentData
import com.butter.util.Chat
import gg.essential.api.EssentialAPI
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
        EssentialAPI.getNotifications().push("ButterExtras", "Welcome to ButterExtras\nTo start, use /be gui to open the main gui.")
    }
}