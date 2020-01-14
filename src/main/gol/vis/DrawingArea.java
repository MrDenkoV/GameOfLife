//package gol.vis;
//
//import javax.swing.*;
//import java.awt.*;
//
//class DrawingArea extends JComponent
//{
//    private int x;
//    private int y;
//    private int ballDiameter;
//    private Color backgroundColor;
//    private Color foregroundColor;
//
//    public DrawingArea(int x, int y
//            , Color bColor, Color fColor, int dia)
//    {
//        this.x = x;
//        this.y = y;
//        ballDiameter = dia;
//        backgroundColor = bColor;
//        foregroundColor = fColor;
//        setBorder(BorderFactory.createLineBorder(
//                Color.DARK_GRAY.darker(), 5, true));
//    }
//
//    public void setXYColourValues(int x, int y
//            , Color bColor, Color fColor)
//    {
//        this.x = x;
//        this.y = y;
//        backgroundColor = bColor;
//        foregroundColor = fColor;
//        repaint();
//    }
//
//    public Dimension getPreferredSize()
//    {
//        return (new Dimension(500, 400));
//    }
//
//    public void paintComponent(Graphics g)
//    {
//        g.setColor(backgroundColor);
//        g.fillRect(0, 0, getWidth(), getHeight());
//        g.setColor(foregroundColor);
//        g.fillOval(x, y, ballDiameter, ballDiameter);
//    }
//}