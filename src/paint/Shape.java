package paint;

/*
 *  This code was based on the Shape Class from class
 *
 */
import java.awt.*;
import java.awt.geom.*;

// simple shape model class
public abstract class Shape {

    int startPointX, startPointY, endPointX, endPointY;
    int startPointOffsetX, startPointOffsetY, endPointOffsetX, endPointOffsetY;
    Color fillColor;
    BasicStroke stroke;
    BasicStroke originalStroke;
    boolean isSelected = false;
    float scale = 1.0f;

    abstract void draw(Graphics2D g2);

    // add a point to end of shape
    abstract void addEndPoint(int x, int y);

    abstract boolean contains(double x, double y);

    abstract void update();

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public BasicStroke getStroke() {
        return stroke;
    }


    public void setStroke(BasicStroke newStroke) {
        originalStroke = getStroke();
        this.stroke = newStroke;
    }

    public float getScale(){
        return scale;
    }

    public void setScale(float scale){
        this.scale = scale;
    }

    public void setOffsets(int x, int y) {
        startPointOffsetX = x - startPointX;
        startPointOffsetY = y - startPointY;
        endPointOffsetX = x - endPointX;
        endPointOffsetY = y - endPointY;
    }

    public void drag(int x, int y) {
        startPointX = x - startPointOffsetX;
        startPointY = y - startPointOffsetY;

        endPointX = x - endPointOffsetX;
        endPointY = y - endPointOffsetY;


        this.update();
    }

    public void select() {
        isSelected = true;
        originalStroke = getStroke();
        float dash[] = { 10.0f };
        setStroke( new BasicStroke(originalStroke.getLineWidth(),
                BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
    }

    public void deselect() {
        setStroke(originalStroke);
        isSelected = false;
    }
}

class Line extends Shape {

    Line2D line;

    public Line(int x, int y, Color newColour, BasicStroke newThickness) {

        startPointX = x;
        startPointY = y;


        setFillColor(newColour);
        setStroke(newThickness);
    }

    public void update() {
        line = new Line2D.Double(startPointX, startPointY, endPointX, endPointY);
    }

    public void addEndPoint(int x, int y) {
        endPointX = x;
        endPointY = y;

        this.update();
    }

    public boolean contains(double x, double y) {
        int offset = 35;
        Rectangle2D rectangle = new Rectangle2D.Double(x-(offset/2), y-(offset/2),
                offset, offset);
        return line.intersects(rectangle);
    }

    public void draw(Graphics2D g2) {
        g2.scale(scale, scale);
        g2.setColor(fillColor);
        g2.fill(line);
        g2.setStroke(stroke);
        g2.draw(line);
    }

}

class Circle extends Shape {

    Ellipse2D circle;

    public Circle(int x, int y, Color newColour, BasicStroke newThickness) {
        startPointX = x;
        startPointY = y;
        setFillColor(newColour);
        setStroke(newThickness);
    }

    public void update() {
        if (endPointX >= startPointX && endPointY >= startPointY) {
            circle = new Ellipse2D.Double(startPointX, startPointY,
                    endPointX - startPointX, endPointY - startPointY);
        }

        if (endPointX >= startPointX && endPointY <= startPointY) {
            circle = new Ellipse2D.Double(startPointX, endPointY,
                    endPointX - startPointX, startPointY - endPointY);
        }

        if (endPointX <= startPointX && endPointY >= startPointY) {
            circle = new Ellipse2D.Double(endPointX, startPointY,
                    startPointX - endPointX, endPointY - startPointY);
        }
        if (endPointX <= startPointX && endPointY <= startPointY) {
            circle = new Ellipse2D.Double(endPointX, endPointY,
                    startPointX - endPointX, startPointY - endPointY);
        }
    }

    public void addEndPoint(int x, int y) {
        endPointX = x;
        endPointY = y;

        this.update();
    }

    public boolean contains(double x, double y) {
        return circle.contains(x, y);
    }

    // let the shape draw itself
    // (note this isn't good separation of shape View from shape Model)
    public void draw(Graphics2D g2) {
        g2.scale(scale, scale);
        g2.setColor(fillColor);
        g2.fill(circle);
        g2.setColor(Color.BLACK);
        g2.setStroke(stroke);
        g2.draw(circle);
    }

}

class Rectangle extends Shape {

    Rectangle2D rectangle;

    public Rectangle(int x, int y, Color newColour, BasicStroke newThickness) {

        startPointX = x;
        startPointY = y;

        setFillColor(newColour);
        setStroke(newThickness);
    }

    public void update() {
        if (endPointX >= startPointX && endPointY >= startPointY) {
            rectangle = new Rectangle2D.Double(startPointX, startPointY,
                    endPointX - startPointX, endPointY - startPointY);
        }

        if (endPointX >= startPointX && endPointY <= startPointY) {
            rectangle = new Rectangle2D.Double(startPointX, endPointY,
                    endPointX - startPointX, startPointY - endPointY);
        }

        if (endPointX <= startPointX && endPointY >= startPointY) {
            rectangle = new Rectangle2D.Double(endPointX, startPointY,
                    startPointX - endPointX, endPointY - startPointY);
        }
        if (endPointX <= startPointX && endPointY <= startPointY) {
            rectangle = new Rectangle2D.Double(endPointX, endPointY,
                    startPointX - endPointX, startPointY - endPointY);
        }
    }

    public void addEndPoint(int x, int y) {
        endPointX = x;
        endPointY = y;

        this.update();
    }

    public boolean contains(double x, double y) {
        return rectangle.contains(x, y);
    }

    // (note this isn't good separation of shape View from shape Model)
    public void draw(Graphics2D g2) {
        g2.scale(scale, scale);
        g2.setColor(fillColor);
        g2.fill(rectangle);
        g2.setColor(Color.BLACK);
        g2.setStroke(stroke);
        g2.draw(rectangle);
    }

}



