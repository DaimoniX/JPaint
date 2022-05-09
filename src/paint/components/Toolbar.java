package paint.components;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

import paint.Constants;
import paint.brushes.*;

public class Toolbar extends JPanel {
    private final PaintArea paintArea;
    private final PaintIconButton[] buttons;
    private final Brush[] brushes;
    private int activeId;
    private Color activeColor;

    public Toolbar(PaintArea paintArea) {
        super();
        this.paintArea = paintArea;
        final int paddingX = 10;
        final int paddingY = 5;

        activeId = -1;
        activeColor = Constants.DEFAULT_PAINT_COLOR;

        setBackground(Constants.TOOLBAR_COLOR);
        setLayout(new GridBagLayout());

        var constraints = new GridBagConstraints();
        constraints.gridx = constraints.gridy = 0;
        constraints.ipadx = constraints.ipady = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(paddingY, paddingX, paddingY, paddingX);

        var penIcon = new ImageIcon("resources/icons/pen.png");

        buttons = new PaintIconButton[] {
                new PaintIconButton(penIcon),
                new PaintIconButton(new ImageIcon("resources/icons/line.png")),
                new PaintIconButton(new ImageIcon("resources/icons/eraser.png")),
                new PaintIconButton(new ImageIcon("resources/icons/ft.png")),
                new PaintIconButton(new ImageIcon("resources/icons/rect.png")),
                new PaintIconButton(new ImageIcon("resources/icons/oval.png")),
                new PaintIconButton(new ImageIcon("resources/icons/fill.png"))
        };

        final var defColor = Constants.DEFAULT_PAINT_COLOR;

        brushes = new Brush[] {
                new BasicBrush(defColor),
                new LineBrush(defColor),
                new EraserBrush(),
                new FollowBursh(defColor),
                new RectBrush(defColor, true),
                new OvalBrush(defColor, true),
                new FillBrush(defColor, paintArea)
        };

        for (int i = 0; i < buttons.length; i++) {
            final int ID = i;
            buttons[i].addActionListener(e -> selectBrush(ID));
            constraints.gridy++;
            add(buttons[i], constraints);
        }

        var clearButton = new PaintIconButton(new ImageIcon("resources/icons/cross.png"));
        clearButton.addActionListener(e -> paintArea.clear());
        var colorPicker = new PaintIconButton(penIcon, Color.RED);
        colorPicker.addActionListener(e -> {
            var color = JColorChooser.showDialog(null, "Select color", getActiveColor(), true);
            if (color != null)
                setActiveColor(color);
        });

        constraints.gridy++;
        add(clearButton, constraints);
        constraints.gridy++;
        add(colorPicker, constraints);

        selectBrush(0);
    }

    private void selectBrush(int id) {
        if (activeId > -1)
            buttons[activeId].deselect();
        buttons[id].select();
        brushes[id].setColor(activeColor);
        paintArea.setBrush(brushes[id]);
        activeId = id;
    }

    public void setActiveColor(Color color) {
        activeColor = color;
        brushes[activeId].setColor(activeColor);
        repaint();
    }

    public Color getActiveColor() {
        return activeColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(brushes[activeId].getColor());
        g.fillRect(5, 5, getWidth() - 10, 30);
    }
}
