package com.butter.util

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
}
