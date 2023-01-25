package edu.lab9;

import edu.shapes.CShapeCircle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CMainForm extends JFrame{
    private JPanel mainPanel;
    private JPanel graphicArea;
    private CDocument document;

    CMainForm(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        graphicArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                graphicsAreaMousePressed(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                graphicsAreaMouseDragged(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                graphicsAreaMouseReleased();
            }
        });

        document = new CDocument((CGraphicArea) graphicArea);//CAST EXEPTION XD
        document.addShape(new CShapeCircle(200,200,Color.lightGray,Color.BLACK,70));
        document.addShape(new CShapeCircle(600,300,Color.YELLOW,Color.BLUE,90));
        document.reDraw();
    }

    private void createUIComponents() {
        graphicArea = new CGraphicArea();
    }

    private void graphicsAreaMousePressed(MouseEvent evt){
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(document.selectShape(evt.getX(),evt.getY())){
                document.reDraw();
            }
        }
    }

    private void graphicsAreaMouseReleased(){
        document.deselectShape();
        document.reDraw();
    }

    private void graphicsAreaMouseDragged(MouseEvent evt){
        document.moveSelectedTo(evt.getX(), evt.getY());
        long time = document.reDraw();
        if(time > 0){
            setTitle(String.format("Kształtowniki, czas rysowania %d ms",time));
        }
    }

}
