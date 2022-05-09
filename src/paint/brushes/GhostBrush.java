package paint.brushes;

import java.awt.Color;
import java.awt.Shape;

public abstract class GhostBrush extends Brush {

    protected Shape shape;

    protected GhostBrush(Color color) {
        super(color);
    }

    public boolean ghostExists() {
        return shape != null;
    }
    
    public Shape getGhost() {
        return shape;
    }
}
