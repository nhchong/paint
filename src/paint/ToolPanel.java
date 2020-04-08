package paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolPanel extends JPanel {
    Canvas canvas;
    public static final int SELECTION = 0;
    public static final int ERASER = 1;
    public static final int LINE = 2;
    public static final int CIRCLE = 3;
    public static final int RECTANGLE = 4;
    public static final int FILL = 5;
    public int currTool;

    public ToolPanel() {
        currTool = 0;
        setPreferredSize(new Dimension(150, 200));
    }

    public void setTool(int currTool) {

    }
}
