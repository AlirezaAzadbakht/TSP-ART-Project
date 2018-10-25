package Draw;

import MST.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class DrawLine extends JComponent {

	private static class Line {
		final int x1;
		final int y1;
		final int x2;
		final int y2;
		final Color color;

		public Line(int x1, int y1, int x2, int y2, Color color) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.color = color;
		}
	}

	private final LinkedList<Line> lines = new LinkedList<Line>();

	public void addLine(int x1, int x2, int x3, int x4) {
		addLine(x1, x2, x3, x4, Color.black);
	}

	public void addLine(int x1, int x2, int x3, int x4, Color color) {
		lines.add(new Line(x1, x2, x3, x4, color));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Line line : lines) {
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);
		}
	}

	public static void drawME(int xx, int yy,int title, ArrayList<node> preorederwalk ) {
		JFrame testFrame = new JFrame();
		testFrame.setTitle(String.valueOf(title));
		testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final DrawLine comp = new DrawLine();
		comp.setPreferredSize(new Dimension(xx, yy));
		testFrame.getContentPane().add(comp, BorderLayout.CENTER);
		for (int i = 0; i < preorederwalk.size() - 1; i++) {
			node a = preorederwalk.get(i);
			node b = preorederwalk.get(i + 1);
			comp.addLine(a.y, a.x, b.y, b.x);
		}
		node a = preorederwalk.get(0);
		node b = preorederwalk.get(preorederwalk.size() - 1);
		comp.addLine(a.y, a.x, b.y, b.x);
		
	
		
		System.err.println();
		testFrame.pack();
		testFrame.setVisible(true);
		needed_method.saveImage(getScreenShot(testFrame.getContentPane()));
	}

	public static BufferedImage getScreenShot(Component component) {

		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(),BufferedImage.TYPE_INT_BGR);
		// call the Component's paint method, using
		// the Graphics object of the image.
		component.paint(image.getGraphics());
		return image;
	}

}
