package paint.brushes;

import java.awt.event.MouseEvent;
import java.awt.*;

public abstract class Brush {
    protected Color color;

    protected Brush(Color color) {
        this.color = color;
    }

    public abstract void onMousePress(MouseEvent e, Graphics2D g);

    public abstract void onMouseDrag(MouseEvent e, Graphics2D g);
    
    public abstract void onMouseRelease(MouseEvent e, Graphics2D g);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
