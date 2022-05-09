package paint.brushes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import paint.components.PaintArea;

public class ClearBrush extends Brush {
    private PaintArea paintArea;

    public ClearBrush(PaintArea paintArea) {
        super(null);
        this.paintArea = paintArea;
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g) {
        // Unused
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g) {
        // Unused
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g) {
        // Unused
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        paintArea.clear();
    }   
}
