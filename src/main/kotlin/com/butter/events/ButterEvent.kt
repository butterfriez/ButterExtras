/*
 * Skytils - Hypixel Skyblock Quality of Life Mod
 * Copyright (C) 2022 Skytils
 */
package com.butter.events

import gg.essential.universal.UChat
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.Event

abstract class ButterEvent : Event() {
    private val eventName by lazy {
        this::class.simpleName
    }

    fun postAndCatch(): Boolean {
        return runCatching {
            MinecraftForge.EVENT_BUS.post(this@ButterEvent)
        }.onFailure {
            it.printStackTrace()
            UChat.chat("caught and logged an ${it::class.simpleName ?: "error"} at ${eventName}. Please report this on the Discord server at discord.gg/skytils.")
        }.getOrDefault(isCanceled)
    }
}