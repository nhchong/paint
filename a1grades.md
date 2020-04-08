# CS349 A1
Student: nhchong
Marker: Greg


Total: 68 / 85 (80.00%)

Code:
(CO: won’t compile, CR: crashes, FR: UI freezes/unresponsive, NS: not submitted)


Notes:   


## FUNCTIONAL REQUIREMENTS (60)

1. [0/5] Saving data. File-Save can be used to save the model (the user should be promoted for a file-save location using a JDialog)

-5 Save feature not implemented

2. [0/5] Loading data. File-Load can be used to load and restore the shapes that were saved using a JDialog. The view (e.g. window size, view-option) does not need to be restored, but all shapes should be saved and restored to the canvas, with the appropriate color, line thickness and so on.

-5 Load feature not implemented

3. [2/2] File-New can be used to discard the current document and create a new blank document.

4. [3/3] Selection tool. The user can select this tool in the toolbar then click on a shape to select it. There should be some visual indication which shape is selected. Pressing ESC on the keyboard will clear shape selection.

5. [2/2] Erase tool. The user can select this tool in the toolbar then click on a shape to delete it.

6. [4/4] Line drawing tool. The user can select this tool in the toolbar and then draw lines in the canvas by holding and dragging the mouse, using the selected colour and line thickness.

7. [4/4] Rectangle drawing tool. The user can select this tool in the toolbar and then draw rectangles in the canvas by holding and dragging the mouse, using the selected colour and line thickness.

8. [4/4] Circle drawing tool. The user can select this tool in the toolbar and then draw circles or ellipses in the canvas by holding and dragging the mouse, using the selected colour and line thickness.


9. [3/3] Fill tool: The user can select this tool in the toolbar and then click on a shape (rectangle or circle/ellipse) to fill it with the current colour.

10. [5/5] Colour palette. Displays at least 6 colors in a graphical view, which the user can select to set the property for drawing a new shape.

11. [2/2] Colour picker. The user can click on the "Chooser" button to bring up a colour chooser dialog to select a colour.

12. [5/5] Line thickness palette. Displays at least 3 line widths, graphically, and allows the user to select line width for new shapes. [6 marks for supporting at least 3 lines; 4 marks for showing them graphically].

13. [2/2] Selecting a shape will cause the color palette and line thickness palette to update their state to reflect the currently selected shape.

14. [10/10] Moving shapes. Users can move shapes around the screen by selecting, then dragging them. There should be an indication which shape is selected. [5 marks for moving, 5 marks for selection indicator]

15. [2/2] The interface should clearly indicate which tool, color and line thickness are selected at any time.

16. [0/2] The interface should enable/disable controls if appropriate.

-2 There was at least one situation when a control could not be used, but it was not disabled. E.g. Eraser tool does not have a colour, or line thcikness.


## LAYOUT (10)

1. [1/1] The starting appication window is no larger than 1600x1200.

2. [4/4] Swing widgets are used appropriately to implement the window layout.

3. [5/5] The layout includes menu bar, colour palette, line chooser and tool palette in the appropriate layout.

## TECHNICAL REQUIREMENTS AND MVC (5)

1. [5/5] Gradle.build exists, `gradle build` and `gradle run` works successfully.

3. [0/0] If third-party graphics/images are used in the user interface, their origin and licence are included in the README file (this item can be negative).


## ADDITIONAL/BONUS FEATURES (10 + 10 optional bonus)

1. [5/10] Application incorporates one or more enhancements totalling 10%, as described in the requirements.

+5 Customizable color palette: you can wholly or partially customize color buttons in the palette (e.g. right-click a button and choose a new color for that button from a color-chooser dialog).
