package paint.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import paint.Constants;

import java.awt.*;
import java.awt.image.*;

public class PaintIconButton extends JButton {
    private static final int SIZE = 30;
    private static final int PADDING = 6;
    private static final Dimension PREFFERED_SIZE = new Dimension(SIZE + PADDING * 2, SIZE + PADDING * 2);
    private BufferedImage image;
    private boolean selected;

    public PaintIconButton(ImageIcon icon) {
        this(icon, Color.WHITE);
    }

    public PaintIconButton(ImageIcon icon, Color iconColor) {
        super();
        setBorder(null);
        selected = false;
        image = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(icon.getImage(), 0, 0, SIZE, SIZE, null);
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.setColor(iconColor);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return PREFFERED_SIZE;
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public void select() {
        selected = true;
        repaint();
    }

    public void deselect() {
        selected = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Constants.TOOLBAR_COLOR);
        g.fillRect(0, 0, SIZE * 2, SIZE * 2);
        var g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (selected || getModel().isRollover()) {
            g2d.setColor(Constants.TOOLBAR_COLOR.darker());
            g2d.fillRoundRect(0, 0, SIZE + PADDING * 2, SIZE + PADDING * 2, SIZE / 2, SIZE / 2);
        }
        g2d.fillRoundRect(0, 0, SIZE + PADDING * 2, SIZE + PADDING * 2, SIZE / 2, SIZE / 2);
        g2d.drawImage(image, PADDING, PADDING, SIZE, SIZE, null);
        g2d.setColor(Constants.TOOLBAR_COLOR.darker());
        g2d.drawRoundRect(0, 0, SIZE + PADDING * 2 - 1, SIZE + PADDING * 2 - 1, SIZE / 2, SIZE / 2);
    }
}
