package ru.sss.ua.ui

import org.intellij.lang.annotations.JdkConstants
import ru.sss.ua.ui.painting.Painter
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import javax.swing.*

class AnimationFrame (private val graphicsPanel: GraphicsPanel) : JFrame() {
    val ctrlPanel : JPanel
    val animLabel : JLabel
    val frameScroll : JScrollPane
    val frameScrollPanel : JPanel
    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Создание экскурсии"
        minimumSize = Dimension(1000, 500)
        animLabel = JLabel().apply {
            text = "Создание анимации"
            font = getFont().deriveFont(16.0f)
        }

        frameScrollPanel = JPanel().apply {
            background = Color.GREEN
        }
        frameScroll = JScrollPane(frameScrollPanel).apply {

        }
        ctrlPanel = JPanel().apply {
            background = Color.WHITE
        }
        layout = GroupLayout(contentPane).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addComponent(graphicsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addComponent(ctrlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(4)
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(createParallelGroup()
                        .addComponent(graphicsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addComponent(ctrlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE)
                    )
                    .addGap(4)
            )
        }

        ctrlPanel.layout = GroupLayout(ctrlPanel).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(50)
                    .addGroup(
                        createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(animLabel,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                            .addGap(50)
                            .addComponent(frameScrollPanel, 250, 250, 250)
                    )
                    .addGap(50)
            )
            setVerticalGroup(
                createSequentialGroup()
                    .addGap(15)
                    .addComponent(animLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGap(50)
                    .addComponent(frameScrollPanel, 300, GroupLayout.PREFERRED_SIZE ,GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30 , Int.MAX_VALUE)
            )
        }
    }

}