package paint;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ColorPanel extends JPanel {
    public Color currColor;
    public ArrayList<JButton> buttons;
    ArrayList<Color> colorButtonColors;

    public ColorPanel(ArrayList<JButton> df) {
        colorButtonColors = new ArrayList<>();
        colorButtonColors.add(Color.BLUE);
        colorButtonColors.add(Color.GREEN);
        colorButtonColors.add(Color.YELLOW);
        colorButtonColors.add(Color.ORANGE);
        colorButtonColors.add(Color.PINK);
        colorButtonColors.add(Color.RED);
        buttons = df;
        currColor = Color.BLUE;
    }

    public void setColor(Shape shape) {
        int mappedColor = mapColor(shape.getFillColor());
        for (int i = 0; i < 6; i++) {
            if (i == mappedColor) {
                buttons.get(i).setOpaque(true);
                buttons.get(i).setBorder(new LineBorder(Color.BLACK, 2, true));
                buttons.get(i).setBackground(mapIndex(i));
            } else {
                buttons.get(i).setOpaque(true);
                buttons.get(i).setBorder(BorderFactory.createEmptyBorder());
                buttons.get(i).setBackground(mapIndex(i));
            }
        }
        currColor = shape.getFillColor();
    }

    private int mapColor(Color c) {
        for (int i = 0; i < colorButtonColors.size(); ++i) {
            if (colorButtonColors.get(i) == c) {
                return i;
            }
        }
        return -1;
    }

    private Color mapIndex(int i) {
        return buttons.get(i).getBackground();
    }

    public void setButtonColor(int buttonIndex, Color c) {
        colorButtonColors.set(buttonIndex, c);
        buttons.get(buttonIndex).setBackground(c);
    }

    public Color getButtonColor(int buttonIndex) {
        return colorButtonColors.get(buttonIndex);
    }
}
