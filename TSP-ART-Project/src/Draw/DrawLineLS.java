package Draw;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import localSearch.*;

public class DrawLineLS extends JComponent {

	private static class Line {
		final double x1;
		final double y1;
		final double x2;
		final double y2;
		final Color color;

		public Line(double x1, double y1, double x2, double y2, Color color) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.color = color;
		}
	}

	private final LinkedList<Line> lines = new LinkedList<Line>();

	public void addLine(double x1, double x2, double x3, double x4) {
		addLine(x1, x2, x3, x4, Color.black);
	}

	public void addLine(double x1, double x2, double x3, double x4, Color color) {
		lines.add(new Line(x1, x2, x3, x4, color));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Line line : lines) {
			g.setColor(line.color);
			g.drawLine((int)line.x1, (int)line.y1,(int) line.x2,(int) line.y2);
		}
	}

	public static void drawME(int xx, int yy,String string,Point[] points ) {
		JFrame testFrame = new JFrame();
		testFrame.setTitle(String.valueOf(string));
		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final DrawLineLS comp = new DrawLineLS();
		comp.setPreferredSize(new Dimension(xx, yy));
		testFrame.getContentPane().add(comp, BorderLayout.CENTER);
		for (int i = 0; i < points.length-1 ; i++) {
			Point a = points[i];
			Point b = points[i + 1];
			comp.addLine(a.y, a.x, b.y, b.x);
		}
		Point a = points[0];
		Point b = points[points.length-1];
		comp.addLine(a.y, a.x, b.y, b.x);
		
	
		
		System.err.println();
		testFrame.pack();
		testFrame.setVisible(true);
		//needed_method.saveImage(getScreenShot(testFrame.getContentPane()));
	}

	public static BufferedImage getScreenShot(Component component) {

		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(),BufferedImage.TYPE_INT_BGR);
		// call the Component's paint method, using
		// the Graphics object of the image.
		component.paint(image.getGraphics());
		return image;
	}

}
