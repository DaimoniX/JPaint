package paint.brushes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class FollowBursh extends Brush {

    public FollowBursh(Color color) {
        super(color);
    }

    private Point lastPoint;

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g) {
        g.setColor(color);
        g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY());
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g) {
        lastPoint = e.getPoint();
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g) {
        // Unused
    }
    
}
