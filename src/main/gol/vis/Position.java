package gol.vis;

import gol.log.Map;
import gol.log.Vector2d;

import javax.swing.*;
import java.awt.*;

public class Position extends JButton {
    private int x;
    private int y;
    private Color onColor;
    private Color offColor;
    private boolean status;
    private Map map;

    public Position(int x, int y, Map map, Color onColor, Color offColor){
        this.x=x;
        this.y=y;
        this.map=map;
        this.offColor=offColor;
        this.onColor=onColor;
        this.status=false;
        this.setBackground(offColor);
        this.addActionListener(actionEvent -> {
            Position currentPosition=(Position) actionEvent.getSource();
            currentPosition.toggle();
            System.out.println(String.valueOf(currentPosition.x)+" "+String.valueOf(currentPosition.y));
            currentPosition.revalidate();
            currentPosition.repaint();
        });
        this.setBorder(BorderFactory.createLineBorder(Color.RED.darker(), 1, false));
    }

    public void toggle(){
        this.status=!this.status;
        this.map.map[this.x][this.y]=!this.map.map[this.x][this.y];
        if(this.status)
            this.setBackground(onColor);
        else
            this.setBackground(offColor);
    }

}
