package paint.brushes;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class LineBrush extends GhostBrush {

    public LineBrush(Color color) {
        super(color);
    }

    private Line2D line = new Line2D.Double();
    private Point start;

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g) {
        line.setLine(start, e.getPoint());
        shape = line;
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g) {
        start = e.getPoint();
        line.setLine(start, start);
        shape = line;
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g) {
        g.setColor(color);
        g.drawLine(start.x, start.y, e.getX(), e.getY());
        shape = null;
    }
    
}
