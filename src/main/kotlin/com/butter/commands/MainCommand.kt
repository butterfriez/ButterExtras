package com.butter.commands

import ButterExtras
import com.butter.gui.MainGui
import gg.essential.universal.UChat
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
            "rat" -> UChat.chat("butter extras is fake, \n" +
                    "every user using this mod including me is a computer learning AI nick developed to trick people into getting ratted,\n" +
                    "every sale is fake and forged to make it believable and at the end of the day I get all your fortnite passwords and skyblock coins,\n" +
                    "\n" +
                    "why do i do this? i am butter, i live in germany and i need to get bailed out of german prison, \n" +
                    "my bond is 250,000 euro ( 250,000 united states dollars ).\n" +
                    "\n" +
                    "so please controbute by buying supporter or downloading this modification which is not a remote access trojan ( RAT ), we love you .\n" +
                    "-butter")
        }
    }
}