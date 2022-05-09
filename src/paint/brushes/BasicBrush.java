package paint.brushes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.RenderingHints;

public class BasicBrush extends Brush {
    public BasicBrush(Color color) {
        super(color);
    }

    private Point lastPoint;

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g) {
        g.setColor(getColor());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY());
        lastPoint = e.getPoint();
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g) {
        lastPoint = e.getPoint();
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g) {
        g.setColor(getColor());
        g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY());
        lastPoint = null;
    }
}
