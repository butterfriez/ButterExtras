package com.butter.gui.components

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.RelativeConstraint
import gg.essential.elementa.constraints.animation.Animations
import gg.essential.elementa.dsl.*
import gg.essential.universal.USound
import java.awt.Color

class Button @JvmOverloads constructor(val text: String, val tall: Boolean = false, val long: Boolean = false): UIBlock(Color(80, 80, 80, 80)){
    private val t = UIText(text).constrain {
        x = CenterConstraint()
        y = CenterConstraint()
        color = Color.WHITE.toConstraint()
    } childOf this

    init {
        this
            .constrain {
                width = if (tall) {
                    RelativeConstraint()
                } else {
                    (t.getWidth() + 40).pixels()
                }
                height = if (long) {
                    RelativeConstraint()
                } else {
                    (t.getHeight() + 10).pixels()
                }
            }
            .onMouseEnter {
                t.animate {
                    setTextScaleAnimation(
                        Animations.OUT_EXP,
                        0.5f,
                        t.constraints.textScale + 2.pixels,
                        0f
                    )
                    setColorAnimation(
                        Animations.OUT_EXP,
                        0.5f,
                        Color.YELLOW.toConstraint(),
                        0f
                    )
                }
            }
            .onMouseLeave {
                t.animate {
                    setTextScaleAnimation(
                        Animations.IN_OUT_SIN,
                        0.5f,
                        t.constraints.textScale - 2.pixels(),
                        0f
                    )
                    setColorAnimation(
                        Animations.OUT_EXP,
                        0.5f,
                        Color.WHITE.toConstraint(),
                        0f
                    )
                }
            }
            .onMouseClick {
                USound.playButtonPress()
            }
    }

}