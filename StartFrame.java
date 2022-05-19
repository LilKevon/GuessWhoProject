/**
 * @author Kevin Li
 * Creates the starting screen for the game.
 * 
 */


//packages
package GuessWhoGame;

//imports
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

//Public class
@SuppressWarnings("serial")
public class StartFrame extends JFrame {


	//Various Swing Components
	static JButton StartButton = new JButton("Start");
	static JButton HelpButton = new JButton("Help ");
	static JLabel Title = new JLabel("Guess Who");
	static JFrame StartWindow = new JFrame();
	static JButton ListOfChars = new JButton("Characters");

	//ArrayList to store the list of characters
	static ArrayList<String> w = new ArrayList<>();

	//Constructor
	StartFrame(){


		//Call the FillStartWindow and StartWindowSetup methods.
		FillStartWindow();
		StartWindowSetup();

		//Add a action listener for start button.
		StartButton.addActionListener(new ActionListener() {

			//Add an action performed method
			@Override
			public void actionPerformed(ActionEvent e) {

				//Start up a progress bar window, creating a new thread, and start it.
				ProgressBarStart tb = new ProgressBarStart();
				tb.start();


				//Dispose of this current Start Window
				StartWindow.dispose();
			}


		});

		//Add action Listener to HelpButton
		HelpButton.addActionListener(new ActionListener() {


			//Add a action performed method
			@Override
			public void actionPerformed(ActionEvent e ) {

				//Create a new HelpFrame, and dispose of the current start window
				HelpFrame possibleframe = new HelpFrame();
				StartWindow.dispose();
			}

		});

		//Add action listener to ListOfChars
		ListOfChars.addActionListener(new ActionListener() {


			//Created a action performed method
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					//Create a new file with the file location of characters
					File Characters = new File("C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\GuessWhoGame\\characters.csv");

					//Create a new Scanner that scans the file yes
					Scanner myReader = new Scanner(Characters);


					//Create a new ArrayList
					w = new ArrayList<>();

					//While there are still lines in the Scanner, then keep looping
					while(myReader.hasNextLine()) {
						
						//Add these lines to w the ArrayList
						w.add(myReader.nextLine());

					}

					//Catch the possible FileNotFound Exception
				}catch(FileNotFoundException o) {
					System.out.println("Sorry, the file was not found.");
				}
				
				//Create a variable called Characters. This will store all of the character's names at least once.
				String Characters = "";
				
				//For loop looping from 0 to w.size(), incrementing by 1 each loop. This will output the list of all of the characters.
				for(int i =0; i<w.size(); i++) {
					
					//Split the String line of the current iteration of w.get(i) by ",", and store it into String[]characterarr.
					String[] characterarr = w.get(i).split(",");
					
					//Add the character's name(located at characterarr[0]) into Characters, and a new line as well.
					Characters += characterarr[0]+ "\n" ;

				}
				
				//Finally, show the entire List in a showMessageDialog
				JOptionPane.showMessageDialog(StartFrame.this, Characters, "List of Characters",  JOptionPane.PLAIN_MESSAGE);

			}


		});

	}

	//FillStartWindow function that fills the start window by setting bounds for all of the Swing Components, setting Fonts, setting Titles, and adding the Swing Components into the StartWindow.
	private void FillStartWindow() {

		StartButton.setBounds(10,100,100,20);
		HelpButton.setBounds(10,200, 100,20);
		ListOfChars.setBounds(10,300,200,20);

		Title.setBounds(10,0,100,100);
		Title.setFont(new Font("Serif", Font.BOLD, 20));

		StartWindow.add(StartButton);
		StartWindow.add(HelpButton);
		StartWindow.add(ListOfChars);
		StartWindow.add(Title);


	}

	//StartWindowSetup function that sets the JFrame up.
	private void StartWindowSetup() {


		StartWindow.setLayout(null);
		StartWindow.setSize(500,500);
		StartWindow.setVisible(true);

	}

}