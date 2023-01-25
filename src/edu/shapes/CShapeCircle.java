package edu.shapes;

import java.awt.*;

public class CShapeCircle extends CShape{

    protected int R;

    public CShapeCircle(int x0, int y0, Color fillColor, Color borderColor, int radius) {
        super(x0, y0, fillColor, borderColor);
        this.R = radius;
    }

    @Override
    protected boolean isPointInside(int xk, int yk) {
        return (xk-X0)*(xk-X0)+(yk-Y0)*(yk-Y0) <= R*R;
    }

    @Override
    protected void drawShape(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillOval(X0-R,Y0-R,2*R,2*R);
        g2d.setColor(borderColor);
        g2d.drawOval(X0-R,Y0-R,2*R,2*R);
    }
}
