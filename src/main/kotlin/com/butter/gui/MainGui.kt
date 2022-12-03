package com.butter.gui

import ButterExtras
import com.butter.config.Config
import com.butter.gui.components.Button
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import net.minecraft.client.Minecraft
import java.awt.Color
import java.awt.Desktop
import java.net.URI

class MainGui : WindowScreen(ElementaVersion.V1) {
    private var MainBlock: UIBlock = UIBlock(Color(80, 80, 80, 80)).constrain {
        x = CenterConstraint()
        y = 10.pixels()
        width = ChildBasedMaxSizeConstraint() + 20.pixels()
        height = ChildBasedMaxSizeConstraint() + 20.pixels()
    } childOf window

    private var Text:UIText = UIText("Butter Extras").constrain {
        color = Color.white.toConstraint()
        x = CenterConstraint()
        y = CenterConstraint()
        textScale = basicTextScaleConstraint { window.getHeight() / 60 }
    } childOf MainBlock

    init {
        Button("Config").constrain {
            x = CenterConstraint()
            y = SiblingConstraint() + 10.pixels()
            width = 200.pixels()
            height = 20.pixels()
        }.childOf(window).onMouseClick {
            ButterExtras.currentGui = Config.gui()
        }
        Button("Github").constrain {
            x = CenterConstraint()
            y = SiblingConstraint() + 10.pixels()
            width = 200.pixels()
            height = 20.pixels()
        }.childOf(window).onMouseClick {
            Desktop.getDesktop().browse(URI.create("https://github.com/butterfriez/ButterExtras"))
        }
    }

    override fun setWorldAndResolution(mc: Minecraft, width: Int, height: Int) {
        window.onWindowResize()
        Text.constrain {
            textScale = basicTextScaleConstraint { window.getHeight() / 60 }
        }
        super.setWorldAndResolution(mc, width, height)
    }
}