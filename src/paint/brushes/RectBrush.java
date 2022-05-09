package paint.brushes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class RectBrush extends GhostBrush {
    private boolean fill;

    public RectBrush(Color color, boolean fill) {
        super(color);
        this.fill = fill;
    }

    private Point start;

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g) {
        start = e.getPoint();
        shape = new Rectangle(0, 0);
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g) {
        int startX = Math.min(start.x, e.getX());
        int startY = Math.min(start.y, e.getY());
        int sizeX = Math.abs(start.x - e.getX());
        int sizeY = Math.abs(start.y - e.getY());
        shape = new Rectangle(startX, startY, sizeX, sizeY);
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g) {
        g.setColor(color);
        int startX = Math.min(start.x, e.getX());
        int startY = Math.min(start.y, e.getY());
        int sizeX = Math.abs(start.x - e.getX());
        int sizeY = Math.abs(start.y - e.getY());
        if (fill)
            g.fillRect(startX, startY, sizeX, sizeY);
        else
            g.drawRect(startX, startY, sizeX, sizeY);
        shape = null;
    }

}
