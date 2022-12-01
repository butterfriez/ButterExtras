package com.butter.config

import ButterExtras
import gg.essential.vigilance.Vigilant
import java.io.File


object Config : Vigilant(
    File(ButterExtras.configDirectory, "config.toml"),
    ButterExtras.metadata.name
) {
    var StampedChat:Boolean = false
    var ChatCopy:Boolean = false
    init {
        category("Misc") {
            switch(
                ::StampedChat,
                description = "Adds a timestamp to every chat message.",
                name = "Stamped Chat"
            )
            switch(
                ::ChatCopy,
                description = "Adds a message click event on each message in chat, when clicked it copies the message.",
                name = "Chat Copy"
            )
        }
    }


}