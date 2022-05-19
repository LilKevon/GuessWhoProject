/**
 * @author Kevin Li
 * Creates a funny progress bar when you start the game. Just a funny aesthetic that should be treated as a basic/advanced function. Your choice, Mr. Sin.
 * NOTE: Please keep in mind that this is not a actual loading bar. The game loads just fine without it, just thought it would feel more gamey-esque if I added the bar lol :).
 */

//Packages
package GuessWhoGame;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

//public class scope start(Extends Thread)
public class ProgressBarStart extends Thread  {	

	//Creates a array of funny inputs
	String[] LoadingMessages = {"Creating Characters...", "Eating Fish...", "Find the end of the Rainbow...", "Doing Comp Sci Work...", "Learning the secrets to the universe...",
			"Loading Mystery Character...", "Calming Rockstar Foxy down...", "Crediting the Coder...", "Finding more funny inputs...", "Seeing the world through a different lense...",
	"Sleeping... Come back later..."};

	

	//Creates a JFrame
	JFrame Progress = new JFrame();

	//Creates a JProgressBar
	JProgressBar bar = new JProgressBar();

	//Create a JLabel
	JLabel Loading = new JLabel("Loading... Please Wait.");

	//run method inherited by Thread
	public void run() {

		// Set the progress bar initial value to 0
		bar.setValue(0);

		//Set the progress bar bounds.
		bar.setBounds(0,200,500,50);

		//Renders a process string. This is the number at the middle of the bar that constantly rises with the bar
		bar.setStringPainted(true);

		//Set the font of the process string
		bar.setFont(new Font("Ariel", Font.BOLD, 25));

		//Set the bar foreground to red
		bar.setForeground(Color.RED);

		//Set the bar background to white
		bar.setBackground(Color.WHITE);

		//Set the loading label's bounds
		Loading.setBounds(0,180,200,20);

		//Set the loading font
		Loading.setFont(new Font("Ariel", Font.BOLD, 15));

		//Add both the bar and the label to the JFrame
		Progress.add(bar);
		Progress.add(Loading);
		
		
		//Setting the JFrame's title to "Loading... Please Wait."
		Progress.setTitle("Loading... Please Wait.");

		//JFrame Setup
		Progress.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Progress.setSize(500,500);
		Progress.setLayout(null);
		Progress.setVisible(true);

		//Call the function progressincrease
		progressincrease();
	}

	//Create a method called progressincrease. This function will constantly fill the bar until it is full.
	public void progressincrease(){


		//c will be our counter variable
		int c = 0;

			//Adding a funny message below the loading bar, like how alot of games do it.
			Random RandomNumber = new Random();
			JLabel FunnyMessage = new JLabel(LoadingMessages[RandomNumber.nextInt(LoadingMessages.length)]);
			FunnyMessage.setBounds(0,260, 200,25);
			Progress.add(FunnyMessage);
		
		//While c is smaller then 100(the bar still isnt full)
		while(c <=100) {

			//Set the bar value to c, which then changes the bar process string as well
			bar.setValue(c);

			//Try to sleep for 50 milliseconds, and catch an InterruptedException
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			//Add 1 to c
			c++;


		}

		//After we break out of the loop, we then should create the game window,and dispose of this loading screen.
		new GuessWhoGUI();
		Progress.dispose();
	}


}

//Extracts of information taken from: https://docs.oracle.com/javase/7/docs/api/javax/swing/JProgressBar.html


