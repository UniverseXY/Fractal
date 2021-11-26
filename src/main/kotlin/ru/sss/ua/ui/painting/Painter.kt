package ru.sss.ua.ui.painting

import java.awt.Dimension
import java.awt.Graphics

interface Painter {
    fun paint(g: Graphics)
    var size: Dimension
}