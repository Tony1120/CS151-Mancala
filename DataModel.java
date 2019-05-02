import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static java.lang.System.exit;

public class DataModel 
{
	private ArrayList<Integer> pits;
	//private ArrayList<Integer> bottomPits;
	//private ArrayList<Integer> sidePits;
	
	private ArrayList<ChangeListener> listeners;
	private boolean player1Flag; //true for P1
	private int redoCounter;
	private int initialStoneAmt;
	private boolean style;
	private int winner;
	boolean gameOn = false;

	//delete this method later. used for testing purposes
	public void setPit(int pitIndex, int newValue)
	{
		pits.set(pitIndex, newValue);
	}
	
	public DataModel()
	{
		pits = new ArrayList<Integer>();
//		pits.add(0, 0);
//		pits.add(1, 8);
//		pits.add(2, 9);
//		pits.add(3, 3);
//		pits.add(4, 10);
//		pits.add(5, 1);
//		pits.add(6, 8);
//		pits.add(7, 0);
//		pits.add(8, 10);
//		pits.add(9, 5);
//		pits.add(10, 9);
//		pits.add(11, 6);
//		pits.add(12, 4);
//		pits.add(13, 8);
		initialStoneAmt = 0;
		listeners = new ArrayList<ChangeListener>();
		player1Flag = true;
		redoCounter = 0;
		winner = -1;
		style = true;		
		System.out.println("\nPlayer 1's turn: " + player1Flag);

	}
	
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}
	//listeners should check the player flag of data model before notify/updating attempt
	
	public void update(int pitIndex)
	{
		int currentPit = pitIndex + 1;
		if(currentPit > 13)
		{
			currentPit = 0;
		}
		// the pitIndex is never going to be 0 or 7 because there is no gui listener for mancala
		if(pitIndex > 7) // Player 1  
		{
			int stoneAmount= pits.get(pitIndex);
			int i = currentPit;
			pits.set(pitIndex, 0);
//			int currentPitValue = pits.get(i);  victor: causing the issue. delete
			while (stoneAmount > 0) 
			{
//				pits.set(i, currentPitValue + 1); victor: causing the issue. delete
				pits.set(i, pits.get(i) + 1); // victor: changed from ^ to this
				if(i == 13)
				{
					i = (i %13);
				}
				else if(i == 6)
				{
					i = i +2;
				}
				else
				{
					i = (i %13) +1;
				}
				stoneAmount --;
			}
			int landingPit = i -1;
			if(landingPit == 0) //for if player1's last marble goes into their own mancala 
			{
				//notify controller for P1 to take another turn
				System.out.println(landingPit + "im in line 83");
				player1Flag = true;
				System.out.println("\nPlayer 1's turn: " + player1Flag);
				
			}
			else if(landingPit > 7) //land in P1 
			{
				if(pits.get(landingPit) == 1)//end in player1's own empty pit 
				{
					
					int steal = landingPit - ((landingPit - 7)*2);
					int stolen = pits.get(steal);
					pits.set(steal, 0);
					int landingPitValue = pits.get(landingPit);
					pits.set(landingPit, 0);
					pits.set(0, pits.get(0) + stolen + landingPitValue);
					player1Flag = false;
					System.out.println("\nPlayer 1's turn: " + player1Flag);

				}
				else
				{
					player1Flag = false;
					System.out.println("\nPlayer 1's turn: " + player1Flag);

				}
			}
			else
			{
				player1Flag = false;
				System.out.println("\nPlayer 1's turn: " + player1Flag);

			}

		}
		else if(pitIndex < 7) // Player 2 
		{
			int stoneAmount= pits.get(pitIndex);
			int i = currentPit;
			pits.set(pitIndex, 0);
//			int currentPitValue = pits.get(i); victor: causing the issue. delete
			while (stoneAmount > 0) 
			{
//				pits.set(i, currentPitValue + 1); victor: causing the issue. delete
				pits.set(i, pits.get(i) + 1); // victor: changed from ^ to this
				if(i == 13)
				{
					i = 1;
				}
				else
				{
					i++;
				}
				stoneAmount --;
			}
			int landingPit = i -1;
			if(landingPit == 7) //for if player2's last marble goes into their own mancala 
			{
				//notify controller for P1 to take another turn
				System.out.println(landingPit + "im in line 83");
				player1Flag = false;
				System.out.println("Player 1's turn: " + player1Flag);
			}
			else if(landingPit < 7) //land in P2
			{
				if(pits.get(landingPit) == 1)
				{
					//end in own empty pit 
					int steal = landingPit + ((7 - landingPit)*2);
					int stolen = pits.get(steal);
					pits.set(steal, 0);
					int landingPitValue = pits.get(landingPit);
					pits.set(landingPit, 0);
					pits.set(7, pits.get(7) + stolen + landingPitValue);
					player1Flag = true;
					System.out.println("Player 1's turn: " + player1Flag);

				}
				else
				{
					player1Flag = true;
					System.out.println("Player 1's turn: " + player1Flag);
				}
			}
			else
			{
				player1Flag = true;
				System.out.println("Player 1's turn: " + player1Flag);
			}


		}

		int grabber = checkGameEnd();
		if (grabber != 0) {
		   winner = determineWinner(grabber);
         System.out.println();
      }
      notifyListeners();
	}
	
	public int checkGameEnd()
	{
		boolean P2isEmpty = true;
		for(int j = 1; j < 7; j++)
		{
			if (pits.get(j) != 0)
			{
				P2isEmpty = false;
			}
		}
		boolean P1isEmpty= true;
		for(int k = 8; k < 14; k++)
		{
			if(pits.get(k) != 0)
			{
				P1isEmpty = false;
			}
		}
		if(P1isEmpty == false && P2isEmpty == false)
		{
			return 0; //game not over
		}
		else if(P2isEmpty)
		{
			return 1; //P1 gets to add remaining stones to mancala
		}
		else if(P1isEmpty)
		{
			return 2; //P2 gets to add remaining stones to its mancala
		}
		return 3; //error
	}
	
	public int determineWinner(int result) //1 = P1 won, 2 = P2 won, 0 = tie
	{
		if(result == 1)
		{
			int collectedStones = 0;
			for(int i = 8; i < 14; i ++)
			{
				collectedStones += pits.get(i);
				pits.set(i, 0);
			}
			pits.set(0, pits.get(0)+collectedStones);
		}
		if(result == 2)
		{
			int collectedStones = 0;
			for(int i = 1; i < 7; i ++)
			{
				collectedStones += pits.get(i);
				pits.set(i, 0);
			}
			pits.set(7, pits.get(7)+collectedStones);
		}
		int P1Mancala = pits.get(0);
		int P2Mancala = pits.get(7);

		if (P1Mancala > P2Mancala) {
		   return 1;
      } else if (P1Mancala < P2Mancala) {
		   return 2;
      } else {
		   return 0;
      }

	}
	
	
	public void printBoard(DataModel model)
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
	
	
	public ArrayList<Integer> getPits() {
		return pits;
	}
	
	public int stoneAmtInPit(int index)
	{
		return pits.get(index);
	}

	public boolean isPlayer1Flag() {
		return player1Flag;
	}

	public void setPlayerFlag(boolean playerFlag) {
		this.player1Flag = playerFlag;
	}

	public int getRedoCounter() {
		return redoCounter;
	}

	public void setRedoCounter(int redoCounter) {
		this.redoCounter = redoCounter;
	}

	public boolean isStyle() {
		return style;
	}

	public void setStyle(boolean style)  //1 == style1 
	{
		this.style = style;
	}
	
	public void setStones(int s)
	{
		initialStoneAmt = s;
		for(int i = 0; i < 14; i++)
		{
			if(i == 0 || i == 7)
			{
				pits.add(i, 0);
			}
			else
			{
			pits.add(i, initialStoneAmt);
			}
		}

	}
	
	public void setGameOn(boolean v)
	{
		gameOn = v;
	}
	public boolean getGameOn()
	{
		return gameOn;
	}
	public void notifyListeners()
	{
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners)
		{
			listener.stateChanged(event);
		}

			System.out.print("\n\n ");
			for (int i = 6; i > 0; i--)
			{
				System.out.print(" " + getPits().get(i));
			}
			System.out.print("\n" + getPits().get(7) + "\t\t" + getPits().get(0) + "\n");
			System.out.print(" ");
			for (int i = 8; i < 14; i++)
			{
				System.out.print(" " + getPits().get(i));
			}
		
	}

   public int getWinner()
   {
      return winner;
   }
}