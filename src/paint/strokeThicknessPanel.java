package paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class strokeThicknessPanel extends JPanel {
    BasicStroke t1 = new BasicStroke(3);
    BasicStroke t2 = new BasicStroke(9);
    BasicStroke t3 = new BasicStroke(15);
    Color c1, c2, c3;
    boolean isEnabled;

    BasicStroke currStroke;
    public BasicStroke getCurrStroke() {return this.currStroke;}

    public Line2D Line1 = new Line2D.Double(30, 25, 120, 25);
    public Line2D Line2 = new Line2D.Double(30, 60, 120, 60);
    public Line2D Line3 = new Line2D.Double(30, 100, 120, 100);
    public strokeThicknessPanel() {
        currStroke = t1;
        c1 = Color.BLACK;
        c2 = Color.GRAY;
        c3 = Color.GRAY;
        isEnabled = false;
        this.setPreferredSize(new Dimension(150, 150));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(3, 3, 3, 3),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));

        this.addMouseListener(new strokeThicknessPanel.MouseClickedAdapter());
    }

    class MouseClickedAdapter extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (isEnabled) {
                if (contains(Line1, e.getX(), e.getY())) {
                    currStroke = t1;
                    c1 = Color.BLACK;
                    c2 = Color.GRAY;
                    c3 = Color.GRAY;
                } else if (contains(Line2, e.getX(), e.getY())) {
                    currStroke = t2;
                    c1 = Color.GRAY;
                    c2 = Color.BLACK;
                    c3 = Color.GRAY;
                } else if (contains(Line3, e.getX(), e.getY())) {
                    currStroke = t3;
                    c1 = Color.GRAY;
                    c2 = Color.GRAY;
                    c3 = Color.BLACK;
                }
                repaint();
            }
        }
    }

    public void setStroke(Shape s) {
        if (s.getStroke() == t1) {
            currStroke = t1;
            c1 = Color.BLACK;
            c2 = Color.GRAY;
            c3 = Color.GRAY;
        }
        if (s.getStroke() == t2) {
            currStroke = t2;
            c1 = Color.GRAY;
            c2 = Color.BLACK;
            c3 = Color.GRAY;
        }
        if (s.getStroke() == t3) {
            currStroke = t3;
            c1 = Color.GRAY;
            c2 = Color.GRAY;
            c3 = Color.BLACK;
        }
        repaint();
    }

    public void setStroke(float width) {
        if (width == t1.getLineWidth()) {
            currStroke = t1;
            c1 = Color.BLACK;
            c2 = Color.GRAY;
            c3 = Color.GRAY;
        }
        if (width == t2.getLineWidth()) {
            currStroke = t2;
            c1 = Color.GRAY;
            c2 = Color.BLACK;
            c3 = Color.GRAY;
        }
        if (width == t3.getLineWidth()) {
            currStroke = t3;
            c1 = Color.GRAY;
            c2 = Color.GRAY;
            c3 = Color.BLACK;
        }
        repaint();
    }

    public boolean contains(Line2D line, double x, double y) {
        int offset = 35;
        Rectangle2D rectangle = new Rectangle2D.Double(x-(offset/2), y-(offset/2),
                offset, offset);
        return line.intersects(rectangle);
    }

    public void enablePanel() {
        isEnabled = true;
    }

    public void disablePanel() {
        isEnabled = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(c1);
        g2.setStroke(t1);
        g2.draw(Line1);
        g2.setColor(c2);
        g2.setStroke(t2);
        g2.draw(Line2);
        g2.setColor(c3);
        g2.setStroke(t3);
        g2.draw(Line3);
    }
}
