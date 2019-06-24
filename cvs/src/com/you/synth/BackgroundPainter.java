package com.you.synth;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthPainter;


public class BackgroundPainter extends SynthPainter {
	
    public void paintPanelBackground(SynthContext context, Graphics g, int x, int y, int w, int h) {
		Color start = UIManager.getColor("Panel.startBackground");
		Color end = UIManager.getColor("Panel.endBackground");
		Graphics2D g2 = (Graphics2D)g;
		GradientPaint grPaint = new GradientPaint(
		            (float)x, (float)y, start,
		            (float)w, (float)h, end);
		g2.setPaint(grPaint);
		g2.fillRect(x, y, w, h);
		g2.setPaint(null);
		g2.setColor(new Color(255, 255, 255, 120));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		CubicCurve2D.Double arc2d = new CubicCurve2D.Double(0, h/4, w/3, h/10, .66 * w, 1.5 * h, w, h/8);
		g2.draw(arc2d);

//		Line2D.Double ln1 = new Line2D.Double(20.0,20.0,80.0,40.0);
//		QuadCurve2D.Double qc = new QuadCurve2D.Double(130.0,30.0,150.0,50.0,170.0,20.0);
//		CubicCurve2D.Double cc = new CubicCurve2D.Double(220.0,40.0,240.0,60.0,260.0,20.0,300.0,35.0);
//		Rectangle2D.Double rect = new Rectangle2D.Double(10,10,100,100);
		RoundRectangle2D.Double rRect = new RoundRectangle2D.Double(60,15, 430, 220+40, 20, 20);
//		Ellipse2D.Double ellipse1 = new Ellipse2D.Double(-21,168, 100, 100);
//		Ellipse2D.Double ellipse2 = new Ellipse2D.Double(83,260, 100, 100);
//		Ellipse2D.Double ellipse3 = new Ellipse2D.Double(158,450, 100, 100);
//		Ellipse2D.Double ellipse4 = new Ellipse2D.Double(254,700, 100, 100);
//		Ellipse2D.Double ellipse5 = new Ellipse2D.Double(386,58, 100, 100);
//		Arc2D.Double arc = new Arc2D.Double(200,200, 200, 200, 0, 90,/*Arc2D.OPEN Arc2D.CHORD*/Arc2D.PIE );
//		g2.draw(ln1);
//		g2.draw(qc);
//		g2.draw(cc);
//		g2.draw(rect);
		g2.draw(rRect);
//		g2.draw(ellipse1);
//		g2.draw(ellipse2);
//		g2.draw(ellipse3);
//		g2.draw(ellipse4);
//		g2.draw(ellipse5);
//		g2.draw(arc);

      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
  }

}
