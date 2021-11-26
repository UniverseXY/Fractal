package ru.sss.ua.ui.painting.fractals

import CartesianPlane
import java.awt.Color
import java.awt.Graphics
import org.kotlinmath.complex
import ru.sss.ua.math.fractals.AlgebraicFractal
import ru.sss.ua.ui.painting.Painter
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.awt.image.BufferedImageFilter
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.math.ceil

class FractalPainter(
    var fractal: AlgebraicFractal,
    var plane: CartesianPlane,
    var colorFunction: (Float)->Color
) : Painter {
    override var size: Dimension
        get() = plane.pixelSize
        set(value) {
            plane.pixelSize = value
        }

    override fun paint(g: Graphics) {
        val threadCount = 8
        val fracService = Executors.newFixedThreadPool(threadCount)
        with(plane) {
            val sabW = 10
            val cnt  =ceil(width.toFloat()/sabW).toInt()
            List(cnt){ i->
             fracService.submit(Callable {
                 val w = if (i == cnt -1) width%sabW else sabW
                 val img = BufferedImage(w, height, BufferedImage.TYPE_INT_RGB)
                 for (j in 0..height) {
                     val ig = img.graphics
                     for (k in 0..w){
                         ig.color = colorFunction(
                             fractal.isInSet(
                                 complex(
                                     xSct2Crt(k + i*sabW),
                                     ySct2Crt(j)
                                 )
                             )
                         )
                         ig.fillRect(k ,j, 1, 1)
                     }
                }
                Pair(img, i)
            })
        }.forEach {
                g.drawImage(it.get().first,it.get().second*sabW,0,null)
            }
        }
    }
}