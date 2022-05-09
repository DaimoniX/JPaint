package paint.components;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import paint.Constants;
import paint.brushes.BasicBrush;
import paint.brushes.Brush;
import paint.brushes.GhostBrush;

public class PaintArea extends JPanel {
    private final class MouseController extends MouseInputAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            isActive = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            isActive = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (isActive) {
                brush.onMousePress(e, imageGraphics);
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            brush.onMouseRelease(e, imageGraphics);
            repaint();
        }

        /*
         * UNUSED
         * 
         * public void mouseMoved(MouseEvent e)
         * 
         * public void mouseClicked(MouseEvent e)
         */

        @Override
        public void mouseDragged(MouseEvent e) {
            if (isActive) {
                brush.onMouseDrag(e, imageGraphics);
                repaint();
            }
        }
    }

    private BufferedImage image;
    private Graphics2D imageGraphics;
    private Color backgroundColor;
    private boolean isActive;
    private static final BasicStroke DEFAULT_STROKE = new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private BasicStroke stroke;

    private Brush brush;
    private Dimension preferredSize;

    private static final Brush DEFAULT_BRUSH = new BasicBrush(Constants.DEFAULT_PAINT_COLOR);

    @Override
    public Dimension getPreferredSize() {
        return preferredSize;
    }

    public PaintArea() {
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        stroke = DEFAULT_STROKE;
        brush = DEFAULT_BRUSH;
        initImage(new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB));
        clear();
        isActive = false;
        backgroundColor = Color.WHITE;
        var mouseController = new MouseController();
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
    }

    public void setBrush(Brush brush) {
        this.brush = brush;
    }
    
    public void setColor(Color color) {
        brush.setColor(color);
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public void clear() {
        imageGraphics.setColor(backgroundColor);
        imageGraphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        repaint();
    }
    
    public void loadImage(BufferedImage image) {
        initImage(image);
        repaint();
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
        imageGraphics.setStroke(stroke);
    }

    public float getStrokeWidth() {
        return stroke.getLineWidth();
    }

    public void setStrokeWidth(float value) {
        if(value < 0)
            return;
        setStroke(new BasicStroke(stroke.getLineWidth(), stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), stroke.getDashArray(), stroke.getDashPhase()));
    }

    public void resetStroke() {
        setStroke(DEFAULT_STROKE);
    }
    
    private void initImage(BufferedImage image) {
        this.image = image;
        imageGraphics = (Graphics2D) image.getGraphics();
        imageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        setStroke(stroke);
        preferredSize = new Dimension(image.getWidth(), image.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        if(brush instanceof GhostBrush b && b.ghostExists()) {
            var g2 = (Graphics2D) g;
            g2.setColor(Constants.GHOST_COLOR);
            g2.draw(b.getGhost());
        }
        Toolkit.getDefaultToolkit().sync();
    }
    
}