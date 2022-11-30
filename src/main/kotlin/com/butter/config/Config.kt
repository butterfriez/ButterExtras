package com.butter.config

import ButterExtras
import gg.essential.vigilance.Vigilant
import java.io.File


object Config : Vigilant(
    File(ButterExtras.configDirectory, "config.toml"),
    ButterExtras.metadata.name
) {
    var StampedChat:Boolean = false

    init {
        category("Misc") {
            switch(
                ::StampedChat,
                description = "Adds a timestamp to every chat message.",
                name = "Stamped Chat"
            )
        }
    }


}