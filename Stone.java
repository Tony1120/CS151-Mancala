//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
//import java.awt.Shape;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Ellipse2D;
//import java.awt.geom.PathIterator;
//import java.awt.geom.Point2D;
//import java.awt.geom.Rectangle2D;
//
//import javax.swing.JComponent;
//import javax.swing.JPanel;
//
//public class Stone extends JPanel{
//	private int w;
//
//	public Stone(int width)
//	{
//		this.w = width;
//
//	}
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
////		Ellipse2D stone1 = new Ellipse2D.Double(this.getWidth()-5, this.getWidth()-5, w, w);
//		Rectangle2D rec = new Rectangle2D.Double(this.getWidth()-5, this.getHeight()-5, w, w);
//		
//		g2.setColor(Color.BLUE);
////		g2.fill(stone1);
////		g2.draw(stone1);
//		g2.draw(rec);
//	}
//
//	
//
//}