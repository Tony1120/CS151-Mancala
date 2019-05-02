import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class CircleShape extends JComponent{
	
	public CircleShape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics2D g2) {

		Ellipse2D.Double pit = new Ellipse2D.Double(x, y, width, height);
		g2.draw(pit);

	}
	
	

	

	public boolean contains(Point2D p) {
	
		return x <= p.getX() && p.getX() <= x + width && y <= p.getY() && p.getY() <= y + width / 2;
	}

	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}

	private int x;
	private int y;
	private int width;
	private int height;
}