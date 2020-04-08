package paint;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.*;

public class Canvas extends JPanel {
    private static final int SELECTION = 0;
    private static final int ERASER = 1;
    private static final int LINE = 2;
    private static final int CIRCLE = 3;
    private static final int RECTANGLE = 4;
    private static final int FILL = 5;

    private ToolPanel toolPanel;
    private ColorPanel colorPanel;
    private paint.strokeThicknessPanel strokeThicknessPanel;
    private int currTool = 0; //initially set to Selection Tool
    private Color currColor = Color.BLUE; //initially set as Black
    private int mouseClickX;
    private int mouseClickY;
    private Shape selectedShape;

    ArrayList<Shape> shapes;
    Shape newShape;
    private boolean isShapeSelected = false;

    public Canvas(ToolPanel tp, ColorPanel cp, strokeThicknessPanel stp) {
        shapes = new ArrayList<Shape>();
        this.setPreferredSize(new Dimension(635,545));
        this.addMouseListener(new MousePressedAdapter());
        this.addMouseListener(new MouseReleasedAdapter());
        this.addMouseMotionListener(new MouseDraggedAdapter());
        this.toolPanel = tp;
        this.colorPanel = cp;
        this.strokeThicknessPanel = stp;
    }

    class MousePressedAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {

            mouseClickX = e.getX();
            mouseClickY = e.getY();

            if (currTool == LINE || currTool == CIRCLE || currTool == RECTANGLE) {
                createShape(e);
            }
            if (currTool == SELECTION) {
                selectShape(e);
            }
        }
    }

    class MouseReleasedAdapter extends MouseAdapter {
        public void mouseReleased(MouseEvent e) {
            if (currTool == LINE || currTool == CIRCLE || currTool == RECTANGLE) {
                addShape(e);
            }
            if (currTool == ERASER) {
                selectShape(e);
                eraseShape(e);
            }
            if (currTool == FILL) {
                selectShape(e);
                fillShape(e);
            }
            repaint();
        }
    }

    class MouseDraggedAdapter extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if ((currTool == LINE) || currTool == CIRCLE || currTool == RECTANGLE) {
                newShape.addEndPoint(e.getX(), e.getY());
            }
            else if (currTool == SELECTION) {
                moveShape(e);
            }
            repaint();
        }
    }

    // custom graphics drawing
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (Shape shape : shapes) {
            shape.draw(g2);
        }
        if (newShape != null) newShape.draw(g2);
    }

    public void createShape(MouseEvent e) {
        if (currTool == LINE) {
            newShape = new Line(e.getX(), e.getY(), currColor, strokeThicknessPanel.getCurrStroke());
        } else if (currTool == CIRCLE) {
            newShape = new Circle(e.getX(), e.getY(), currColor, strokeThicknessPanel.getCurrStroke());
        } else if (currTool == RECTANGLE) {
            newShape = new Rectangle(e.getX(), e.getY(), currColor, strokeThicknessPanel.getCurrStroke());
        }
    }

    // This is called when the user is finished drawing a shape. It adds it to the array
    public void addShape(MouseEvent e) {
        newShape.addEndPoint(e.getX(), e.getY());
        shapes.add(newShape);
        newShape = null; // this has been into a full on shape so remove it as a "shape-in-the-making"
        isShapeSelected = false;
        selectedShape = null;
    }

    public void selectShape(MouseEvent e) {
        for (int i = shapes.size()-1; i >= 0; i--) {
            if (shapes.get(i).contains(e.getX(), e.getY())) {
                for (Shape s : shapes) {
                    if (s.isSelected) s.deselect();
                }
                selectedShape = shapes.get(i);
                isShapeSelected = true;
                selectedShape.setOffsets(e.getX(), e.getY());
                colorPanel.setColor(selectedShape);
                if (currTool == SELECTION) {
                    selectedShape.select();
                    strokeThicknessPanel.setStroke(selectedShape.getStroke().getLineWidth());
                }
                break;
            }
        }
    }

    public void deselectShape() {
        for (Shape s : shapes) {
            if (s.isSelected) s.deselect();
        }
        selectedShape = null;
        isShapeSelected = false;
        repaint();
    }

    public void moveShape(MouseEvent e) {
        if (selectedShape != null && selectedShape.contains(mouseClickX, mouseClickY)) {
            mouseClickX = e.getX();
            mouseClickY = e.getY();
            selectedShape.drag(e.getX(), e.getY());
        }
    }

    public void eraseShape(MouseEvent e) {
        shapes.remove(selectedShape);
        selectedShape = null;
        isShapeSelected = false;
    }

    public void fillShape(MouseEvent e) {
        if (isShapeSelected) {
            selectedShape.setFillColor(this.currColor);
            colorPanel.setColor(selectedShape);
        }
    }

    public void setTool(int identifier) {
        this.currTool = identifier;
        if (currTool != SELECTION) {
            deselectShape();
        }
    }

    public void setColour(Color newColor) {
        this.currColor = newColor;
        if (isShapeSelected) {
            selectedShape.setFillColor((newColor));
            repaint();
        }
    }

    public void newCanvas() {
        shapes.clear();
        currTool = 0;
        currColor = Color.BLUE;
        repaint();
    }

}

