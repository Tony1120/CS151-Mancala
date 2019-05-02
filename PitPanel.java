import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PitPanel extends JPanel implements ChangeListener {
	private ArrayList<CircleShape> stones = new ArrayList<CircleShape>();
	private CircleShape pit;
	private int pitIndex; //the index of the pit this object will represent
	private DataModel dataModel;
	private int stoneAmount;
	Random random = new Random();
	
	
	public PitPanel(DataModel dm, int pitIndex) {
		setSize(100,100);
		dataModel = dm;
		if(pitIndex > 7)
		{
			pit = new CircleShape(0,0,110, 110);
		}
		else if(pitIndex == 7 || pitIndex == 0)
		{
			pit = new CircleShape(0,getHeight()-50,105, 220);
		}
		else if(pitIndex > 0 && pitIndex < 7)
		{
			pit = new CircleShape(0, getHeight()-50, 110, 110);
		}
		this.pitIndex = pitIndex;
		addMouseListener(new MouseListeners());
	}

	class MouseListeners extends MouseAdapter {

		public void mousePressed(MouseEvent event) {

			if ((dataModel.isPlayer1Flag() == true) && (pitIndex >= 8) && (pitIndex <=13))
			{
				dataModel.update(pitIndex);
			}
			else if ((dataModel.isPlayer1Flag() == false) && (pitIndex >= 1) && (pitIndex <=6))
			{
				dataModel.update(pitIndex);

			}

		}
	}

//	public void printBoard(DataModel model)
//	{
//		System.out.print("\n\n ");
//		for (int i = 6; i > 0; i--)
//		{
//			System.out.print(" " + model.getPits().get(i));
//		}
//		System.out.print("\n" + model.getPits().get(7) + "\t\t" + model.getPits().get(0) + "\n");
//		System.out.print(" ");
//		for (int i = 8; i < 14; i++)
//		{
//			System.out.print(" " + model.getPits().get(i));
//		}
//	}
	
	

	public void paintComponent(Graphics g) {
	

		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		stoneAmount= dataModel.stoneAmtInPit(pitIndex);

		
		//For the labels
		 if(pitIndex > 0 && pitIndex < 7)
		{
			String label = "B" + Integer.toString(pitIndex);
			g2.drawString(label, 49, 30);
		}
		 else if (pitIndex > 7)
		 {
			 String label = "A" + Integer.toString(pitIndex - 7);
			g2.drawString(label, 49, 140);
		 }
		

		
		//For the stones
		for (int i = 0; i< stoneAmount; i++)
		{
			
			 if(pitIndex > 0 && pitIndex < 7)
			 {
				 int x = 5 + random.nextInt(100-5);
				 int y = 55 + random.nextInt(90);
				 
				 stones.add(new CircleShape(x, y, 10, 10));
			 }
			 else if (pitIndex > 7)
			 {
				stones.add(new CircleShape(random.nextInt(100-8), random.nextInt(50), 10, 10));
			 }
			 else if (pitIndex == 0 || pitIndex == 7)
			 {
				 int x = 5 + random.nextInt(100-5);
				 int y = 55 + random.nextInt(90);
				 stones.add(new CircleShape(x, y, 10, 10));

			 }
		}
		
		pit.draw(g2);
		if (stones.size() != 0)
		for (int i = 0; i< stoneAmount; i++)
		{
			stones.get(i).draw(g2);
		}
		

//		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		
		
	}



	@Override
	public void stateChanged(ChangeEvent arg0) {
		stoneAmount= dataModel.stoneAmtInPit(pitIndex);
		repaint();
		
	}

}