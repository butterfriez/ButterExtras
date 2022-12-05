package com.butter.util

import net.minecraft.network.play.client.C0EPacketClickWindow
import java.text.SimpleDateFormat
import java.util.*

object MainUtil {
    fun getCurrentTime(): Any {
        val date = SimpleDateFormat("hh:mm:ss")
        return date.format(Date())
    }

    fun slotClick(windowId: Int, slot: Int) {
        ButterExtras.mc.playerController.windowClick(
            windowId,
            slot,
            0,
            0,
            ButterExtras.mc.thePlayer
        )
    }

    fun slotClickPacket() {
        var packet: C0EPacketClickWindow = C0EPacketClickWindow()
    }
}
