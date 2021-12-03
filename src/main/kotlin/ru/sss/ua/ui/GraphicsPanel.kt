package ru.sss.ua.ui

import ru.sss.ua.ui.painting.Painter
import java.awt.Color
import java.awt.Graphics
import java.awt.Point
import java.awt.Rectangle
import java.awt.event.*
import javax.swing.JPanel
import kotlin.math.abs
import kotlin.math.min

class GraphicsPanel(private val painters: List<Painter>): JPanel() {
    override fun paint(g: Graphics?) {
        super.paint(g)
        g?.let {
            painters.forEach { p-> p.paint(it) }
        }
    }

    fun addSelectListener(l: (Rectangle)->Unit){
        selectListner.add ( l )
    }
    fun removeSelectListner(l: (Rectangle)->Unit){
        selectListner.remove ( l )
    }


    private var pt1: Point? = null
    private var pt2: Point? = null
    private val selectListner: MutableList<((Rectangle)->Unit)> = mutableListOf()

    init {
        addComponentListener(object: ComponentAdapter(){
            override fun componentResized(e: ComponentEvent?) {
                painters.forEach {
                    it.size = size
                }
                repaint()
            }
        })

        addMouseListener(object : MouseAdapter(){
            override fun mousePressed(e: MouseEvent?) {
              //  graphics.setXORMode(Color.WHITE)

                pt1 = e?.point
            }

            override fun mouseReleased(e: MouseEvent?) {
                pt1?.let { p1 ->
                    pt2?.let { p2 ->
                        var r: Rectangle = Rectangle(min(p1.x,p2.x),min(p1.y,p2.y), abs(p2.x - p1.x),abs(p2.y-p1.y))
                        selectListner.forEach{ it(r)}
                    }
                }
                pt1 = null
                pt2 = null


       //         graphics.setPaintMode()
            }
        })

        addMouseMotionListener(object : MouseMotionAdapter(){
            override fun mouseDragged(e: MouseEvent?) {
                with(graphics){
                    setXORMode(Color.WHITE)
                    pt1?.let { pt->
                        pt2?.let {pt2->
              //              drawRect(pt.x,pt.y,pt2.x-pt.x,pt2.y - pt.y)
            //                drawRect(pt.x,pt2.y,pt2.x-pt.x,-pt2.y + pt.y)
          //                  drawRect(pt2.x,pt.y,-pt2.x+pt.x,pt2.y - pt.y)
        //                    drawRect(pt2.x,pt2.y,-pt2.x+pt.x,-pt2.y + pt.y)

                            drawRect(min(pt.x,pt2.x),min(pt.y,pt2.y), abs(pt2.x - pt.x),abs(pt2.y-pt.y))

                        }
                        pt2 = e?.point
                        e?.let { e->
      //                      drawRect(pt.x, pt.y, e.x - pt.x, e.y - pt.y)
    //                        drawRect(pt.x, e.y, e.x - pt.x, -e.y + pt.y)
  //                          drawRect(e.x, pt.y, -e.x + pt.x, e.y - pt.y)
//                            drawRect(e.x, e.y, -e.x + pt.x, -e.y + pt.y)

                            drawRect(min(pt.x,e.x),min(pt.y,e.y), abs(e.x - pt.x),abs(e.y-pt.y))

                        }
                    }
                    setPaintMode()
                }
            }
        })
    }
}