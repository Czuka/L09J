package edu.lab9;

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Grzegorz
 */
public class CGraphicArea extends JPanel {

    private int areaWidth;
    private int areaHeight;
    private Image drawing;

    public CGraphicArea() {
            setBorder(BorderFactory.createLineBorder(Color.black));
            areaWidth = 1280;
            areaHeight = 800;
            drawing = null;
        }

        public void assignDrawing(Image drw){
            drawing = drw;
        }

        @Override public Dimension getPreferredSize() {
            return new Dimension(1280,800);
        }

        @Override public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(drawing == null) {
                g.setColor(Color.white);
                g.fillRect(0, 0, areaWidth, areaHeight);
            } else {
                g.drawImage(drawing, 0, 0, this);
            }
        }
    }