package com.butter.config

import ButterExtras
import gg.essential.vigilance.Vigilant
import java.io.File


object Config : Vigilant(
    File(ButterExtras.configDirectory, "config.toml"), ButterExtras.metadata.name
) {
    //chat
    var StampedChat:Boolean = false
    var ChatCopy:Boolean = false

    //gui
    var AutoBazaarClaimOrder = false
    var AutoBazaarClaimClaim = 0
    var AutoBazaarClaimDelay = 1000

    var AutoConfirmAH = false
    var AutoConfirmAHMethod = 0
    var AutoConfirmAHClickDelay = 1000
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
            switch(
                ::AutoBazaarClaimOrder,
                description = "When click keybind, it automatically claims bazaar order. &4(MUST HAVE BOOSTER COOKIE ACTIVE)",
                name = "Auto Bazaar Order"
            )

            subcategory("Auto Bazaar") {
                selector(
                    ::AutoBazaarClaimClaim,
                    description = "How to claim the order &4This does nothing right now.",
                    name = "Auto Bazaar Claim Method",
                    options = listOf("All", "Self")
                )
                slider(
                    ::AutoBazaarClaimDelay,
                    description = "Delay when feature has to click.",
                    name = "Auto Bazaar Claim Delay",
                    min = 100,
                    max = 1000
                )
            }

            switch(
                ::AutoConfirmAH,
                description = "Automatically clicks AH confirm button on an item.",
                name = "Auto Confirm AH"
            )

            subcategory("AH Features") {
                selector(
                    ::AutoConfirmAHMethod,
                    description = "Way of pressing confirm on item &4Only Mouse Click works.",
                    name = "Auto Confirm AH Click Method",
                    options = listOf("Mouse Click", "Packet")
                )
                slider(
                    ::AutoConfirmAHClickDelay,
                    description = "Wait time to click.",
                    name = "Auto Confirm Ah Click Delay",
                    min = 0,
                    max = 1000
                )
            }
        }

        //Bazaar
        arrayOf(
            "AutoBazaarClaimClaim",
            "AutoBazaarClaimDelay"
        ).forEach { addDependency(it, "AutoBazaarClaimOrder") }

        //AH
        arrayOf(
            "AutoConfirmAHMethod",
            "AutoConfirmAHClickDelay"
        ).forEach { addDependency(it, "AutoConfirmAH") }
    }


}