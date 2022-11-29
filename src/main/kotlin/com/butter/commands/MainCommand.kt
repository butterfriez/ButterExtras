package com.butter.commands

import ButterExtras
import com.butter.gui.MainGui
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

class MainCommand : CommandBase() {
    override fun getCommandName() = "butterextras"

    override fun getCommandAliases() = listOf("be")

    override fun getCommandUsage(sender: ICommandSender?) = "/$commandName"

    override fun getRequiredPermissionLevel() = 0

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        when(args!!.getOrNull(0)) {
            "gui" -> ButterExtras.currentGui = MainGui()
            null -> ButterExtras.currentGui = MainGui()
        }
    }
}