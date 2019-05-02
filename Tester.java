
//public class Tester {
//
//	public static void main (String[]args)
//	{
//		DataModel model = new DataModel(13);
//
//		model.update(5);
//		for(int i = 0; i < model.getPits().size(); i++)
//		{
//			
//			System.out.println(i + "    " + model.getPits().get(i));
//
//		}
////		System.out.println(model.checkGameEnd());
////		System.out.println(model.determineWinner(model.checkGameEnd()));
////		for(int i = 0; i < model.getPits().size(); i++)
////		{	
////			System.out.println(i + "    " + model.getPits().get(i));
////
////		}
//		
//		
//		
//	}
//}
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Tester {

	public static void main(String[] args) {
		
		//GUI to get user preferences 
		JFrame prompt = new JFrame();
		JLabel quest = new JLabel("Enter Number of Stones per Pit:");
		TextField question = new TextField();
		question.setColumns(5);
		JRadioButton style1 = new JRadioButton("STYLE 1 DESCRIPTION");
		JRadioButton style2 = new JRadioButton("StYLE 2 Description");
		prompt.setLayout(new FlowLayout());
		DataModel dataModel = new DataModel();

		PopUpWindow myPopUp = new PopUpWindow(dataModel);

		myPopUp.setVisible(false);

		dataModel.attach(myPopUp);

		JButton submit = new JButton("Ready to go!");
		prompt.add(quest);
		prompt.add(question);
		prompt.add(style1);
		prompt.add(style2);
		prompt.add(submit);
		prompt.pack();
		prompt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		prompt.setVisible(true);
		
		style1.addMouseListener(new MouseAdapter() {
			@Override 
			public void mousePressed(MouseEvent e)
			{
				style2.setSelected(false);
			}
		});
		style2.addMouseListener(new MouseAdapter() {
			@Override 
			public void mousePressed(MouseEvent e)
			{
				style1.setSelected(false);
			}
		});
		
		submit.addMouseListener(new MouseAdapter() {
			@Override 
			public void mousePressed(MouseEvent e)
			{
				if(style2.isSelected())
				{
					dataModel.setStyle(false);
				}
				else if(style1.isSelected())
				{
					dataModel.setStyle(true);
				}
				dataModel.setStones(Integer.parseInt(question.getText()));
				dataModel.setGameOn(true);
				prompt.setVisible(false);
				
				JFrame frame = new JFrame();
				frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			
				frame.setLayout(new BorderLayout());
					
				JPanel allPits;
				allPits = new JPanel();
				allPits.setLayout(new GridLayout(2,6,0,0));
				
				JPanel board = new JPanel();
				board.setLayout(new BorderLayout());		
				PitPanel mancalaA = new PitPanel(dataModel, 0);
				PitPanel mancalaB = new PitPanel(dataModel, 7);
				mancalaB.setPreferredSize(new Dimension(110, 220));
				mancalaA.setPreferredSize(new Dimension(110,220));
				
				board.add(mancalaB, BorderLayout.WEST);
				board.add(allPits, BorderLayout.CENTER);
				board.add(mancalaA, BorderLayout.EAST);
				frame.add(board, BorderLayout.CENTER);
				
				
				int[] pitOrder = {6, 5, 4, 3, 2, 1, 8, 9, 10, 11, 12, 13};
				int pitOrderNum = 0;
				
				ArrayList<PitPanel> pits = new ArrayList<PitPanel>();
				for(int i = 0; i < 12; i++)
				{
					pits.add(new PitPanel(dataModel, pitOrder[pitOrderNum]));
					pitOrderNum++;
				}		
				
				for(int i = 0; i < 12; i++)
				{
					allPits.add(pits.get(i));
					
				}	
				for(int i = 0; i < 12; i++)
				{
					dataModel.attach(pits.get(i));
					
				}
				dataModel.attach(mancalaA);
				dataModel.attach(mancalaB);
				
				
				JPanel buttonPanel = new JPanel();
				
				JButton button1 = new JButton("Re-Do!");
				JButton button2 = new JButton("");
				buttonPanel.add(button1);
				frame.add(buttonPanel, BorderLayout.NORTH);		
//				JLabel playerLabel = new JLabel();
//				frame.add(playerLabel, JLabel.CENTER);

				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setVisible(true);
			}
		});
		
		
		
		//GUI for set up
//		if(dataModel.getGameOn())
//		{
//		JFrame frame = new JFrame();
//		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//	
//		frame.setLayout(new BorderLayout());
//			
//		JPanel allPits;
//		allPits = new JPanel();
//		allPits.setLayout(new GridLayout(2,6,0,0));
//		
//		JPanel board = new JPanel();
//		board.setLayout(new BorderLayout());		
//		PitPanel mancalaA = new PitPanel(dataModel, 0);
//		PitPanel mancalaB = new PitPanel(dataModel, 7);
//		mancalaB.setPreferredSize(new Dimension(110, 220));
//		mancalaA.setPreferredSize(new Dimension(110,220));
//		
//		board.add(mancalaB, BorderLayout.WEST);
//		board.add(allPits, BorderLayout.CENTER);
//		board.add(mancalaA, BorderLayout.EAST);
//		frame.add(board, BorderLayout.CENTER);
//		
//		
//		int[] pitOrder = {6, 5, 4, 3, 2, 1, 8, 9, 10, 11, 12, 13};
//		int pitOrderNum = 0;
//		
//		ArrayList<PitPanel> pits = new ArrayList<PitPanel>();
//		for(int i = 0; i < 12; i++)
//		{
//			pits.add(new PitPanel(dataModel, pitOrder[pitOrderNum]));
//			pitOrderNum++;
//		}		
//		
//		for(int i = 0; i < 12; i++)
//		{
//			allPits.add(pits.get(i));
//			
//		}	
//		for(int i = 0; i < 12; i++)
//		{
//			dataModel.attach(pits.get(i));
//			
//		}
//		dataModel.attach(mancalaA);
//		dataModel.attach(mancalaB);
//		
//		
//		JPanel buttonPanel = new JPanel();
//		
//		JButton button1 = new JButton("Re-Do!");
//		JButton button2 = new JButton("");
//		buttonPanel.add(button1);
//		frame.add(buttonPanel, BorderLayout.NORTH);		
////		JLabel playerLabel = new JLabel();
////		frame.add(playerLabel, JLabel.CENTER);
//
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		frame.setVisible(true);
	}
//	}

	private static final int FRAME_WIDTH = 915;
	private static final int FRAME_HEIGHT = 400;

	public static void printBoard(DataModel model)
	{
		System.out.print(" ");
		for (int i = 6; i > 0; i--)
		{
			System.out.print(" " + model.getPits().get(i));
		}
		System.out.print("\n" + model.getPits().get(7) + "\t\t" + model.getPits().get(0) + "\n");
		System.out.print(" ");
		for (int i = 8; i < 14; i++)
		{
			System.out.print(" " + model.getPits().get(i));
		}
	}
	
}