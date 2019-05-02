import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PopUpWindow extends JFrame implements ChangeListener
{
   JLabel label;
   JButton button;
   DataModel dataModel;
   PopUpWindow(DataModel d)
   {
      super( "ending the game" );        // invoke the JFrame constructor
      dataModel = d;
      setPreferredSize(new Dimension(450,200));

      setLayout(new BorderLayout());
      label = new JLabel();
      button = new JButton("close");// / construct a JLabel

      button.addActionListener(new java.awt.event.ActionListener() {
         @Override
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            System.exit(0);
         }
      });

      add( label, BorderLayout.NORTH );                        // add the label to the JFrame
      add(button, BorderLayout.SOUTH);


      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((int) ((dimension.getWidth() - getWidth())/2 ),(int) ((dimension.getHeight() - getWidth())/2));
      pack();

   }

   @Override
   public void stateChanged(ChangeEvent e)
   {
      int winner = dataModel.getWinner();
      switch (winner) {
         case 1: {
            label.setText("play1 wins !!!");
            setVisible(true);
            break;
         }
         case 2: {
            label.setText("play2 wins !!!");
            setVisible(true);
            break;
         }
         case 0: {
            label.setText("the two fools tie");
            setVisible(true);
            break;
         }
      }
   }
}

