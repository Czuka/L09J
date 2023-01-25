package edu.lab9;

import edu.shapes.IShape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CDocument {
    private List<IShape> shapes;
    private BufferedImage image;
    private CGraphicArea graphicArea;
    private IShape shapeSelected;

    public CDocument(CGraphicArea graphicArea) {
        this.graphicArea = graphicArea;
        image = new BufferedImage(graphicArea.getWidth(),graphicArea.getHeight(), BufferedImage.TYPE_INT_RGB);
        shapes = new ArrayList<>();
        shapeSelected = null;
    }

    private void repaintImage(){
        graphicArea.assignDrawing(image);
        graphicArea.repaint();
    }

    private void clearImage(){
        Graphics gg = image.getGraphics();
        gg.setColor(Color.WHITE);
        gg.fillRect(0,0, image.getWidth(), image.getHeight());
    }

    public void clear(){
        shapes.clear();
        clearImage();
        repaintImage();
    }

    public boolean selectShape(int ax, int ay){
        ListIterator<IShape> it = shapes.listIterator(shapes.size());
        for (;it.hasPrevious();){
            IShape F = it.previous();
            if(F.select(ax, ay)){
                shapeSelected = F;
                return true;
            }
        }
        return false;
    }

    public long reDraw(){
        long l = System.currentTimeMillis();
        clearImage();
        Graphics g = image.getGraphics();
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        for(IShape shape : shapes){
            if(shape != shapeSelected)
                shape.draw(g,false);
        }

        if(shapeSelected!=null){
            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            shapeSelected.draw(g,true);
        }
        repaintImage();;
        return System.currentTimeMillis() - l;
    }

    public void deselectShape(){
        shapeSelected = null;
    }

    public void addShape(IShape newShape){
        shapes.add(newShape);
    }

    public void moveSelectedTo(int x, int y){
        if(shapeSelected != null)
            shapeSelected.moveTo(x,y);
    }

}
