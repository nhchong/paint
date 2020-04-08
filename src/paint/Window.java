package paint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.border.*;
import java.awt.event.*;

public class Window {
    //Frame
    private JFrame frame = new JFrame("Assignment 1");

    //Color buttons
    private JButton blueButton = new JButton();
    private JButton redButton = new JButton();
    private JButton pinkButton = new JButton();
    private JButton yellowButton = new JButton();
    private JButton greenButton = new JButton();
    private JButton orangeButton = new JButton();
    ArrayList<JButton> colorButtons = new ArrayList<JButton>();

    //Panels
    private static final Dimension FRAME_DIMENSION = new Dimension(800, 600);
    private static final Dimension TOOL_DIMENSION = new Dimension(150, 200);
    private static final Dimension COLOR_DIMENSION = new Dimension(150, 200);
    private static final Dimension CHOOSER_DIMENSION = new Dimension(150, 50);
    private JPanel sidePanel = new JPanel();
    private JPanel drawPanel = new JPanel();
    private ToolPanel toolPanel = new ToolPanel();
    private ColorPanel colorPanel = new ColorPanel(colorButtons);
    private paint.strokeThicknessPanel strokeThicknessPanel = new strokeThicknessPanel();
    private JPanel chooserPanel = new JPanel();

    //Canvas
    private Canvas canvas = new Canvas(toolPanel, colorPanel, strokeThicknessPanel);

    //Menu
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem newMenuItem = new JMenuItem("New");
    private JMenuItem loadMenuItem = new JMenuItem("Load");
    private JMenuItem saveMenuItem = new JMenuItem("Save");

    //Tool buttons
    private JButton selectionButton = new JButton(new ImageIcon("resources/pointer.png"));
    private JButton eraserButton = new JButton(new ImageIcon("resources/eraser.png"));
    private JButton lineButton = new JButton(new ImageIcon("resources/line.png"));
    private JButton circleButton = new JButton(new ImageIcon("resources/circle.png"));
    private JButton rectangleButton = new JButton(new ImageIcon("resources/rectangle.png"));
    private JButton fillButton = new JButton(new ImageIcon("resources/fill.png"));

    //Chooser Button
    private JButton chooserButton = new JButton("Chooser");

    private final JFileChooser fileChooser = new JFileChooser("C:/");


    //Random variables
    private static final Color UNSELECTED_COLOR = new Color(150, 150, 150);
    private Border defaultBorder = chooserButton.getBorder();
    private Border selectedBorder = new LineBorder(Color.BLACK, 2, true);

    public Window() {
        createTools();
        createColors();
        createChooser();
        createMenu();
        createWindow();
        createFrame();
    }

    public final void createTools() {

        ArrayList<JButton> toolButtons = new ArrayList<JButton>();

        toolButtons.add(selectionButton);
        toolButtons.add(eraserButton);
        toolButtons.add(lineButton);
        toolButtons.add(circleButton);
        toolButtons.add(rectangleButton);
        toolButtons.add(fillButton);

        for (int i = 0; i < toolButtons.size(); i++) {
            final int toolIndex = i;
            toolButtons.get(i).setForeground(UNSELECTED_COLOR);
            toolButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.setTool(toolIndex);
                    toolPanel.setTool(toolIndex);
                    for (int i = 0; i < toolButtons.size(); ++i) {
                        if (i == toolIndex) {
                            toolButtons.get(i).setBorder(selectedBorder);
                        }
                        else {
                            toolButtons.get(i).setBorder(defaultBorder);
                        }
                    }
                    if (toolIndex >= 2 && toolIndex <= 4) {
                        strokeThicknessPanel.enablePanel();
                    } else {
                        strokeThicknessPanel.disablePanel();
                    }
                }
            });
            toolPanel.add(toolButtons.get(i));
        }
        selectionButton.setBorder(new LineBorder(Color.BLACK, 2, true));
    }

    public void createColors() {
        //Set each button's background color
        blueButton.setOpaque(true);
        blueButton.setBorder(BorderFactory.createEmptyBorder());
        redButton.setOpaque(true);
        redButton.setBorder(BorderFactory.createEmptyBorder());
        pinkButton.setOpaque(true);
        pinkButton.setBorder(BorderFactory.createEmptyBorder());
        yellowButton.setOpaque(true);
        yellowButton.setBorder(BorderFactory.createEmptyBorder());
        orangeButton.setOpaque(true);
        orangeButton.setBorder(BorderFactory.createEmptyBorder());
        greenButton.setOpaque(true);
        greenButton.setBorder(BorderFactory.createEmptyBorder());

        blueButton.setBackground(Color.BLUE);
        redButton.setBackground(Color.RED);
        pinkButton.setBackground(Color.PINK);
        yellowButton.setBackground(Color.YELLOW);
        orangeButton.setBackground(Color.ORANGE);
        greenButton.setBackground(Color.GREEN);

        colorButtons.add(blueButton);
        colorButtons.add(greenButton);
        colorButtons.add(yellowButton);
        colorButtons.add(orangeButton);
        colorButtons.add(pinkButton);
        colorButtons.add(redButton);

        blueButton.setBorder(new LineBorder(Color.BLACK, 3, true));
        //Set each button's style, size, and action listener
        for (int i = 0; i < colorButtons.size(); i++) {
            final int buttonIndex = i;
            //add the button to the panel;
            //colorButtons.get(i).setOpaque(true);
            colorButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvas.setColour(colorPanel.getButtonColor(buttonIndex));
                    for (int i = 0; i < colorButtons.size(); ++i) {
                        if (i == buttonIndex) {
                            colorButtons.get(i).setOpaque(true);
                            colorButtons.get(i).setBorder(new LineBorder(Color.BLACK, 3, true));
                            colorButtons.get(i).setBackground(colorPanel.getButtonColor(i));
                        } else {
                            colorButtons.get(i).setOpaque(true);
                            colorButtons.get(i).setBorder(BorderFactory.createEmptyBorder());
                            colorButtons.get(i).setBackground(colorPanel.getButtonColor(i));
                        }
                    }
                }
            });
            colorButtons.get(i).addMouseListener(new MouseListener() {
                @Override
                public void mousePressed(MouseEvent me) { }
                public void mouseReleased(MouseEvent me) { }
                public void mouseEntered(MouseEvent me) { }
                public void mouseExited(MouseEvent me) { }
                public void mouseClicked(MouseEvent me) {
                    if(me.getButton() == MouseEvent.BUTTON3) {
                        Color newColor = JColorChooser.showDialog(
                                colorButtons.get(buttonIndex),
                                "Choose New Color",
                                colorButtons.get(buttonIndex).getBackground());
                        if(newColor != null){
                            colorPanel.setButtonColor(buttonIndex, newColor);
                            colorButtons.get(buttonIndex).setOpaque(true);
                            colorButtons.get(buttonIndex).setBorder(BorderFactory.createEmptyBorder());
                        }
                    }
                }
            });
            colorPanel.add(colorButtons.get(i));
        }
    }

    public void createChooser() {
        chooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(
                        chooserButton,
                        "Choose New Color",
                        chooserButton.getBackground());
                if(newColor != null){
                    chooserButton.setBackground(newColor);
                    canvas.setColour(newColor);
                    for (int i = 0; i < colorButtons.size(); ++i) {
                        if (colorPanel.colorButtonColors.get(i) == newColor) {
                            colorButtons.get(i).setBorder(new LineBorder(Color.BLACK, 3, true));
                        } else {
                            colorButtons.get(i).setBorder(BorderFactory.createEmptyBorder());
                        }
                    }
                }
            }
        });
        chooserPanel.add(chooserButton);
    }

    public void createMenu() {
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.newCanvas();

            }
        });
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCanvas();

            }
        });
        fileMenu.add(newMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
    }

    public void createWindow() {
        //Tool Panel
        GridLayout toolsButtonLayout = new GridLayout(3, 2);
        toolsButtonLayout.setHgap(2);
        toolsButtonLayout.setVgap(2);
        toolPanel.setLayout(toolsButtonLayout);
        toolPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        toolPanel.setPreferredSize(TOOL_DIMENSION);

        //Color Panel
        GridLayout colorButtonLayout = new GridLayout(3, 2);
        colorButtonLayout.setHgap(2);
        colorButtonLayout.setVgap(2);
        colorPanel.setLayout(colorButtonLayout);
        colorPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        colorPanel.setPreferredSize(COLOR_DIMENSION);

        // Chooser Panel
        chooserPanel.setLayout(new GridLayout(1, 1));
        chooserPanel.setSize(new Dimension(100, 200));
        chooserPanel.setPreferredSize(CHOOSER_DIMENSION);

        //Side Panel
        BoxLayout sideLayout = new BoxLayout(sidePanel, BoxLayout.Y_AXIS);
        sidePanel.setBorder(BorderFactory.createEtchedBorder());
        sidePanel.setLayout(sideLayout);
        sidePanel.add(toolPanel);
        sidePanel.add(colorPanel);
        sidePanel.add(chooserPanel);
        sidePanel.add(strokeThicknessPanel);
        //Drawing Panel
        drawPanel.add(canvas);
    }

    public void createFrame() {
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(drawPanel, BorderLayout.CENTER);

        //Frame attributes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setEnabled(true);
        frame.setResizable(false);
        frame.setJMenuBar(menuBar);
        frame.setSize(FRAME_DIMENSION);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                canvas.deselectShape();
            }
            return false;
        });
        frame.setVisible(true);
    }

    public void saveCanvas() {
        BufferedImage image = new BufferedImage(635,545, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        canvas.paint(g2);
        try{
            ImageIO.write(image, "png", new File("Canvas.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadCanvas() {

    }

    private Color mapIndex(int i) {
        if (i == 0) return Color.BLUE;
        if (i == 1) return Color.GREEN;
        if (i == 2) return Color.YELLOW;
        if (i == 3) return Color.ORANGE;
        if (i == 4) return Color.PINK;
        else return Color.RED;
    }

}