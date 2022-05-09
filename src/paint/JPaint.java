package paint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import paint.components.Controls;
import paint.components.PaintArea;
import paint.components.Toolbar;

import java.awt.*;

public class JPaint extends JFrame {
    private static final String VERSION = "0.01";
    
    public JPaint() {
        super();
        setTitle("JPaint " + VERSION);
        final var minSize = new Dimension(700, 780);
        setLayout(new BorderLayout());
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var paintArea = new PaintArea();
        var tools = new Toolbar(paintArea);
        var controls = new Controls(paintArea);
        var paintAreaContainer = new JPanel(new GridBagLayout());
        paintAreaContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        paintAreaContainer.setAlignmentY(Component.CENTER_ALIGNMENT);
        paintAreaContainer.add(paintArea, new GridBagConstraints());
        add(controls, BorderLayout.PAGE_START);
        add(tools, BorderLayout.WEST);
        add(paintAreaContainer, BorderLayout.CENTER);
        setVisible(true);
    }

}
