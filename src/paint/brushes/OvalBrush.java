package paint.brushes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

public class OvalBrush extends GhostBrush {
    private Ellipse2D ellipse;
    private boolean fill;

    public OvalBrush(Color color, boolean fill) {
        super(color);
        this.fill = fill;
        ellipse = new Ellipse2D.Double();
        ellipse.setFrame(0, 0, 0, 0);
    }

    private Point start;

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g) {
        start = e.getPoint();
        ellipse.setFrame(0, 0, 0, 0);
        shape = ellipse;
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g) {
        int startX = Math.min(start.x, e.getX());
        int startY = Math.min(start.y, e.getY());
        int sizeX = Math.abs(start.x - e.getX());
        int sizeY = Math.abs(start.y - e.getY());
        ellipse.setFrame(startX, startY, sizeX, sizeY);
        shape = ellipse;
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g) {
        g.setColor(color);
        int startX = Math.min(start.x, e.getX());
        int startY = Math.min(start.y, e.getY());
        int sizeX = Math.abs(start.x - e.getX());
        int sizeY = Math.abs(start.y - e.getY());
        if (fill)
            g.fillOval(startX, startY, sizeX, sizeY);
        else
            g.drawOval(startX, startY, sizeX, sizeY);
        shape = null;
    }

}
