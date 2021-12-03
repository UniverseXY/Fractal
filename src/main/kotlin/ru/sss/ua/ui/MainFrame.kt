package ru.sss.ua.ui

import CartesianPlane
import ru.sss.ua.math.fractals.Mandelbrot
import ru.sss.ua.ui.painting.fractals.FractalPainter
import ru.sss.ua.ui.painting.fractals.grayFractal
import ru.sss.ua.ui.painting.fractals.pinkFractal
import java.awt.Color
import java.awt.Dimension
import java.awt.Rectangle
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.GroupLayout
import javax.swing.JButton
import javax.swing.JFrame

class MainFrame : JFrame() {
    val animFrame : AnimationFrame
    val fractalPanel: GraphicsPanel
    val openAnimFrame : JButton
    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(600, 400)
        openAnimFrame = JButton().apply {
            text = "Открыть экскурсию"
        }
        val plane = CartesianPlane(-2.0, 1.0, -1.0, 1.0)
        val colorizers = listOf(::grayFractal, ::pinkFractal)
        val painter = FractalPainter(Mandelbrot, plane, colorizers[1])

        fractalPanel = GraphicsPanel(
//            listOf(
  //              FractalPainter(Mandelbrot, plane, colorizers[0])
            listOf(painter)


        ).apply {
            background = Color.WHITE
            addSelectListener {
         //       println("adasdasd")
                with(plane){
                    val xMin=xSct2Crt(it.x)
                    val yMin=ySct2Crt(it.y)
                    val xMax= xSct2Crt(it.x + it.width)
                    val yMax= ySct2Crt(it.y + it.height)
                    xSegment = Pair(xMin,xMax)
                    ySegment = Pair(yMin,yMax)
                }
                repaint()
            }
        }
        animFrame = AnimationFrame(fractalPanel)
        openAnimFrame.addMouseListener( object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                animFrame.isVisible = true
            }
        })

        layout = GroupLayout(contentPane).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addComponent(fractalPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(openAnimFrame, 100, 100, 100)
                    .addGap(4)
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addComponent(fractalPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(openAnimFrame, 100, 100, 100)
                    .addGap(4)
            )
        }
    //    pack()
  //      plane.pixelSize = fractalPanel.size
    }

//    fun onSelectArea(r: Rectangle){    }
}