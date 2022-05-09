package paint.brushes;

import java.awt.Color;

import paint.Constants;

public class EraserBrush extends BasicBrush {

    public EraserBrush() {
        super(Constants.DEFAULT_BACKGROUND_COLOR);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(Constants.DEFAULT_BACKGROUND_COLOR);
    }
}
