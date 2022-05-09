package paint.brushes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import paint.components.PaintArea;

public class FillBrush extends Brush {
    private final PaintArea area;

    public FillBrush(Color color, PaintArea paintArea) {
        super(color);
        this.area = paintArea;
    }

    @Override
    public void onMousePress(MouseEvent e, Graphics2D g) {
        BufferedImage image = area.getImage();
        int fillPixel = image.getRGB(e.getX(), e.getY());
        if (fillPixel == color.getRGB())
            return;
        Queue<Point> points = new LinkedList<>();
        points.add(e.getPoint());
        while (!points.isEmpty()) {
            var point = points.poll();
            if (point.x < image.getWidth() && point.x > -1 && point.y < image.getHeight() && point.y > -1 && fillPixel == image.getRGB(point.x, point.y)) {
                image.setRGB(point.x, point.y, color.getRGB());
                points.add(new Point(point.x + 1, point.y));
                points.add(new Point(point.x - 1, point.y));
                points.add(new Point(point.x, point.y + 1));
                points.add(new Point(point.x, point.y - 1));
            }
        }
    }

    @Override
    public void onMouseDrag(MouseEvent e, Graphics2D g) {
        // Unused
    }

    @Override
    public void onMouseRelease(MouseEvent e, Graphics2D g) {
        // Unused
    }

}
