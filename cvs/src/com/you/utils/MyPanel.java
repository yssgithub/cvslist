package com.you.utils;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    public MyPanel(){
        super();
    }
    public MyPanel(LayoutManager layout){
        super(layout);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(285, 195+50, 375, 195+50);


    }

    protected void paintComponent(Graphics g) {
    }

}
