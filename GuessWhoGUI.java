/**
 * @author Kevin Li
 * 
 */


//packages
package GuessWhoGame;

//imports
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;
import java.util.List;

//Public class scope start
@SuppressWarnings("serial")
public class GuessWhoGUI extends JFrame implements ActionListener {


	//Various Instance Variables for GuesWhoGUI. 

	//HashMap for Powerups
	HashMap<Integer, String> Powerups= new HashMap<>();

	//List for Prompts
	JButton ListofPrompts = new JButton("List Of Prompts");

	//Score Panel
	JPanel scorePanel = new JPanel();

	//Guess amount label
	JLabel guessAmountLabel = new JLabel("0");

	//Time Label
	JLabel TimeLabel = new JLabel("100");

	//Timer
	Timer s = new Timer(1000,this);

	//ListOfCharacterButton
	JButton ListOfCharacterButton = new JButton("List of Characters");

	//Guess button
	JButton guessButton = new JButton("Guess Who?");

	//Integers to check if pressed yes or no when prompted
	int POWERUPYESORNO;
	int YESORNO;

	//Character Panel
	JPanel characterPanel = new JPanel();

	//Character Array
	Character[]characterLabelArray = new Character[24];

	//Ask Question
	JButton askButton = new JButton("Ask Question");

	//Time Label
	JLabel TimeLeftLabel = new JLabel("Time Left");

	//Guess Label
	JLabel GuessesLabel = new JLabel("Guesses");

	//Characters Label
	JLabel CharactersLabel =new JLabel("Characters");
	
	//CharacterLabelArray copy
	Character[]CharacterLabelArrayCopy = new Character[24];

	//Numerical Values for guesses and time left 
	private int numGuesses = 0;
	private int TimeLeft = 100;

	//Music Thread for background music
	MusicThread Jeopardy = new MusicThread();

	//Mystery Character Profile
	Character mysteryCharacter = new Character();

	//Door Icon
	private final ImageIcon DOOR = new ImageIcon("C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\images\\door.jpeg");

	//Constructor
	public GuessWhoGUI() {

		//JFrame Setup
		setTitle("Guess Who Game");
		setSize(700,800);
		setLayout(null);

		//Call inputCharacters
		inputCharacters();

		//Call createMysteryCharacter
		createMysteryCharacter();

		//Call setupScorePanel
		setupScorePanel();

		//Call setupCharacterPanel
		setupCharacterPanel();

		//Call PowerUpSetUp
		PowerUpSetUp();

		//List of Prompts Button SetUp
		ListofPrompts.setBounds(10,10, 150,20);
		ListofPrompts.addActionListener(this);
		this.add(ListofPrompts);

		//List of Characters Button Setup
		ListOfCharacterButton.setBounds(500,10,180,20);
		ListOfCharacterButton.addActionListener(this);
		this.add(ListOfCharacterButton);

		//Ask Button Setup
		askButton.setBounds(285,700,200,50);
		askButton.addActionListener(this);	
		add(askButton);

		//Start Timer and Background music
		s.start();				
		Jeopardy.start();

		//Set visible true
		setVisible(true);
	}

	//Create a method called PowerUpSetUp. This will fill the PowerUp Array with the 2 powerups.
	private void PowerUpSetUp() {
		//Delete a Character, and +20 Time
		Powerups.put(0, "Delete a Character");
		Powerups.put(1, "+20 Time");

	}

	//Create a method called createMysteryCharacter. This will create the mysteryCharacter profile.
	private void createMysteryCharacter() {

		//Find a random number between 0 - 23. This will be the index of the mystery character 
		int randomNumber = (int)(Math.random()*24);

		mysteryCharacter = characterLabelArray[randomNumber];

		//Output the mysteryCharacter. Needed for reference when debugging.
		System.out.println(randomNumber);
		System.out.println(mysteryCharacter);
	}

	//Create a method called inputCharacters. This will input the Characters, and create the icons for them.
	private void inputCharacters() {

		//Attempt to read the file, and fill the characterLabelArray with the Character profiles.
		try {

			//Read the file with characters, and find the pattern of \n.
			Scanner input = new Scanner(new File("C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\GuessWhoGame\\characters.csv"));
			input.useDelimiter(",|\n");

			//Will be incremented by one each loop, in order to fill each element position in characterLabelArray
			int index = 0;

			//While input has a next line, keep looping.
			while(input.hasNext()) {

				//The element at the position index will be a new Character.
				characterLabelArray[index] = new Character();

				//Set all of the instance variables for the Character at position index.
				characterLabelArray[index].setName(input.next().replace("\n", "").replaceAll("\r", ""));
				characterLabelArray[index].setHairColour(input.next());
				characterLabelArray[index].setClothingColour(input.next());
				characterLabelArray[index].setWearsHat(input.nextBoolean());
				characterLabelArray[index].setHasClaw(input.nextBoolean());
				characterLabelArray[index].setHasVehicle(input.nextBoolean());
				characterLabelArray[index].setHasBeard(input.nextBoolean());
				characterLabelArray[index].setWearsEarings(input.nextBoolean());
				characterLabelArray[index].setWearsHelmet(input.nextBoolean());
				characterLabelArray[index].setWearsTie(input.nextBoolean());
				characterLabelArray[index].setHasBeak(input.nextBoolean());
				characterLabelArray[index].setHasTail(input.nextBoolean());
				characterLabelArray[index].setHasGuitar(input.nextBoolean());
				characterLabelArray[index].setHasScales(input.nextBoolean());
				characterLabelArray[index].setFileName(input.next().replace("\n", "").replaceAll("\r", ""));

				//Set the Character's icon at position index in characterLabelArray as the image at the location of the Character's filename.
				characterLabelArray[index].setIcon(new ImageIcon(characterLabelArray[index].getFileName()));
				
				//Output the Character at position index 
				System.out.println(characterLabelArray[index]);

				//Increase the index to move onto the next position at characterLabelArray
				index++;
			}

			//Catch the file not found exception if thrown
		} catch(FileNotFoundException e) {
			System.out.println("Sorry, your file was not found.");
			e.printStackTrace();

		}
		
		//Keep a copy of the characters in order before it is shuffled.
		for(int i =0; i<characterLabelArray.length; i++) {
			CharacterLabelArrayCopy[i] = characterLabelArray[i];
		}

	}

	//Create a method called setUpCharacterPanel. This method will fill the Character Panel with character icons.
	private void setupCharacterPanel() {

		//Setting the bounds of the character panel.
		characterPanel.setBounds(50,300,600,400);

		//Setting its background as blue.
		characterPanel.setBackground(Color.BLUE);

		//Convert a reference of characterLabelArray into a List, and call it ShuffleCharacters
		List<Character> ShuffleCharacters = Arrays.asList(characterLabelArray);

		//Use collections.shuffle to shuffle the positions of each icon. .shuffle() will ensure that the order of the icons will be random everytime.
		Collections.shuffle(ShuffleCharacters);

		//Set the layout of the panel as a grid of 4 rows and 6 elements in each row.
		characterPanel.setLayout(new GridLayout(4,6));

		//Add the characters into the grid layout. It will keep putting them into the layout until there are no more icons.
		for(int i =0; i<ShuffleCharacters.size(); i++) 
			characterPanel.add(ShuffleCharacters.get(i));


		//Set the character labels bounds
		CharactersLabel.setBounds(300,260,150,50);

		//Set fonts
		CharactersLabel.setFont(new Font("Ariel", Font.BOLD, 20));

		//Add the character label to the JFrame
		this.add(CharactersLabel);

		//add the character panel to the JFrame.
		add(characterPanel);

	}

	//Create a void method called setupScorePanel. This will set up the score panel. 
	private void setupScorePanel() {

		//Score panel set up
		scorePanel.setBounds(50,50,600,200);
		scorePanel.setBackground(Color.RED);
		scorePanel.setLayout(null);

		//Guess amount label set bounds
		guessAmountLabel.setBounds(300,100,50,25);

		//Score panel adds the guess amount
		scorePanel.add(guessAmountLabel);

		//TimeLabel, TimeLeftLabel, GussesLabel set bounds
		TimeLabel.setBounds(297,17,100,50);
		TimeLeftLabel.setBounds(275,3,100,50);
		GuessesLabel.setBounds(275,45,200,100);

		//Set fonts for GuessLabel and TimeLeftLabel
		GuessesLabel.setFont(new Font("Ariel", Font.BOLD, 14));
		TimeLeftLabel.setFont(new Font("Ariel", Font.BOLD, 14));

		//Setting bounds for guess button, and actionListener
		guessButton.setBounds(200,150,200,25);
		guessButton.addActionListener(this);

		//Add all of the labels and button into scorePanel
		scorePanel.add(GuessesLabel);
		scorePanel.add(guessButton);
		scorePanel.add(TimeLeftLabel);
		scorePanel.add(TimeLabel);


		//Add scorePanel to the JFrame
		add(scorePanel);
	}

	//Action performed for the action fired.
	public void actionPerformed(ActionEvent event) {

		//If the askButton was clicked, which we will know when we get source from event.
		if(event.getSource() == askButton) {

			//Boolean flag = true
			boolean flag = true;

			//When the ask Button is clicked, then create a String variable that shows a display to ask a Question
			String question = JOptionPane.showInputDialog("Ask your Question?");


			//Check for keywords such as hair, clothing, tail, etc. If found one of these key words, then correspond to the appropriate check Method.
			if(question.contains("hair")) {

				//The Hair and Clothing Checks use parse, so that there is no need for 2 prompts. All the user has to do is follow the template "Do they have [Colour] hair/clothing?" 
				String[] QuestionSplit = question.split(" ");

				//Since the String was split by " ", then the 3rd indexed element should be the color.
				checkHairColour(QuestionSplit[3]);
				
				
			//This would have the same conditions as the question containing hair
			}else if(question.contains("clothing")) {

				//The Hair and Clothing Checks use parse, so that there is no need for 2 prompts. All the user has to do is follow the template "Do they have [Colour] hair/clothing?" 
				String[] QuestionSplit = question.split(" ");

				//Since the String was split by " ", then the 3rd indexed element should be the color.
				checkClothingColour(QuestionSplit[3]);
			}

			//All of the true or false questions
			else if(question.toLowerCase().contains("tail"))checkHasTail();			
			else if(question.toLowerCase().contains("claw"))checkHasClaw();			
			else if(question.toLowerCase().contains("vehicle"))CheckHasVehicle();			
			else if(question.toLowerCase().contains("beard"))CheckHasBeard();			
			else if(question.toLowerCase().contains("earings"))CheckWearsEarrings();			
			else if(question.toLowerCase().contains("helmet"))CheckWearsHelmet();			
			else if(question.toLowerCase().contains("tie"))CheckWearsTie();			
			else if(question.toLowerCase().contains("beak"))CheckHasBeak();			
			else if(question.toLowerCase().contains("tail"))CheckHasTail();			
			else if(question.toLowerCase().contains("guitar"))CheckHasGuitar();			
			else if(question.toLowerCase().contains("scales"))CheckHasScales();
			else if(question.toLowerCase().contains("hate"))CheckWearsHat();


			//If none of the keywords are found, then the program will then give a warning message. Then set flag to false
			else {
				JOptionPane.showMessageDialog(this, "Sorry, Invalid Input. Please Try Again.", "Warning", 0);
				flag = false;
			}


			//if flag is true, then a valid guess was made. Increase numGuesses by 1. However, if it was false, that means invalid input was entered, and that does not count as a guess.
			if(flag==true) numGuesses++;

			//Refresh the label that holds the numGuesses with the new updated value of numGuesses.
			guessAmountLabel.setText(Integer.toString(numGuesses));


			//Create a random variable.
			Random randomNumber = new Random();

			//Create a variable called PowerupChance. This variable can be 0 - 9. This will be the number that will be checked to see if a power up spawns after the user enters a guess. 
			int PowerupChance = randomNumber.nextInt(10);

			//If the powerupChance variable is lower or equal to 1(~20% chance of this happening), Then select either the 2 powerups to use.
			if(1==1) {

				//Another random variable, this time from 0-1.
				int WhichPowerUp = randomNumber.nextInt(2);

				//Play the sound effect for powerup spawn, a reference to Rockstar Foxy in the game Five Nights At Freddys: Ultimate Custom Night, where the character, Rockstar Foxy, grants you a powerup if you grab his bird.
				PowerUpSoundEffectThread power = new PowerUpSoundEffectThread();

				//Play the audio
				power.start();

				//Create a shwoComfirmDialog variable which holds a int value, labeled as POWERUPYESORNO. This will hold whether the user presses yes to the powerup or no.
				POWERUPYESORNO = JOptionPane.showConfirmDialog(GuessesLabel, "Yarg! You got a Power Up! \t The power up is " + Powerups.get(WhichPowerUp) + "\n Use?", "Powerup Spawn!", 0,0, new ImageIcon("C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\GuessWhoGame\\FNAFReference.png"));

				//If the yes option is hit
				if(POWERUPYESORNO == JOptionPane.YES_OPTION) {
				
					//Find a number between 0 and 9
					int DebuffChance = randomNumber.nextInt(10);
					
					//if the DebuffChange is lower then 2(~30%), then subtract 20 from your time.
					if(DebuffChance <=2) {
						
						TimeLeft-=20;
						TimeLabel.setText(Integer.toString(TimeLeft));
						
						//Plays debuff sound effect
						DebuffSoundEffectThread evil = new DebuffSoundEffectThread();
						evil.start();
						JOptionPane.showMessageDialog(GuessesLabel, "Rats! You've been double crossed! Instead of the gift, you lose 20 seconds!", "Foxy tricked you...", 0);
						
					}
					
				//If the yes button was clicked, and the power up that was grabbed from the random index of the hashmap was delete a character.
					else if( Powerups.get(WhichPowerUp).equals("Delete a Character")) {

					//Find the closest character in the characterLabelArray from the start that does not have a door, and close the door. Check if it is not the mystery character too.
					for(int i =0; i<characterLabelArray.length; i++) {
						if(characterLabelArray[i].getIcon() != DOOR && characterLabelArray[i] != mysteryCharacter) {
							characterLabelArray[i].setIcon(DOOR);

							//when found and set icon as door, break.
							break;
						}
					}
				}

				//Otherwise, the other power up was selected, which was +20 time. This powerup will add 20 seconds to your time
				else if( Powerups.get(WhichPowerUp).equals("+20 Time")) {

					//Add 20 seconds to the time
					TimeLeft += 20;

					//Update the label holding the value of TimeLeft
					TimeLabel.setText(Integer.toString(TimeLeft));
				}

				}

			}

			//Otherwise, if the source of the action fired was guessButton, we will prompt the user with a question that asks them who they think the mystery character is.
		} else if(event.getSource() == guessButton) {

			//Create a string answer, and store the input of the showInputDialog that will be prompt the user of their guess.
			String answer = JOptionPane.showInputDialog("Who do you think the mystery character is?");

			//If the guess was right, then the user wins!
			if(answer.equalsIgnoreCase(mysteryCharacter.getName())) YESORNO = JOptionPane.showConfirmDialog(GuessesLabel, "You Win!\n Play Again?", "You Win!",0);

			//Otherwise, they lost.
			else YESORNO =JOptionPane.showConfirmDialog(GuessesLabel, "You Lose!\n Try Again?", "You Lose!",0);

			//Stop the timer
			s.stop();

			//Check if the user wanted to play again. If they do, restart the game.
			if(YESORNO == JOptionPane.YES_OPTION) {

				@SuppressWarnings("unused")
				GuessWhoGUI PossibleFrame = new GuessWhoGUI();
			}

			//Otherwise, return them back to the home screen
			else if(YESORNO == JOptionPane.NO_OPTION) {
				@SuppressWarnings("unused")
				StartFrame PossibleFrame = new StartFrame();
				Jeopardy.stop();

			}

			//Stop the background music. Keep in mind that .stop() for threads has been depricated due to it not being safe when closing threads. However, for simple tasks like this, 
			//the method shouldn't cause any trouble.
			Jeopardy.stop();

			//Dispose the current game window
			this.dispose();



		}

		//Else, if the source of the action fired was from the ListofPrompts button, then we should show the user the prompts they can enter into the ask button.
		else if(event.getSource() == ListofPrompts) {
			JOptionPane.showMessageDialog(CharactersLabel, "What Hair Colour?\nWhat Clothing Colour?\nWears Hat?\nHasClaw?\nHas Vehicle?\nHas Beard?\nWears Earings\nWears Tie\nHas Beak\nHas Tail\nHas Guitar\nHas Scales", "List of Questions to Ask",1);
		}

		else if(event.getSource()== ListOfCharacterButton) {
			String temp = "";
			for(int i =0; i<CharacterLabelArrayCopy.length; i++) {
				temp +=CharacterLabelArrayCopy[i].getName() + "\n";
			}
			
			//Shows a picture of all of the characters one by one, and a list of names ordered from start to finish.
			Icon Picture = new ImageIcon("C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\GuessWhoGame\\Characters.PNG");
			JOptionPane.showMessageDialog(CharactersLabel, temp + "\n List of names is in order to the picture", "List of Characters",1,Picture);

		}

		//Else, if the source of the action fired was from the timer, then subtract 1 second from timer, and update the label holding the value of the timer.
		else if(event.getSource() == s) {
			TimeLeft--;
			TimeLabel.setText(Integer.toString(TimeLeft));

			//If timer equals to -1, that means the player lost. Display a lose screen, and ask if they want to play again.
			if(TimeLeft <=-1 ) {
				YESORNO =JOptionPane.showConfirmDialog(GuessesLabel, "Times up You Lose!\n Try Again?", "You Lose!",0);

				//Stop the timer
				s.stop();

				//Check if the user wanted to play again. If they do, restart the game.
				if(YESORNO == JOptionPane.YES_OPTION) {

					@SuppressWarnings("unused")
					GuessWhoGUI PossibleFrame = new GuessWhoGUI();
				}

				//Otherwise, return them back to the home screen
				else if(YESORNO == JOptionPane.NO_OPTION) {
					@SuppressWarnings("unused")
					StartFrame PossibleFrame = new StartFrame();

				}

				//Dispose of this current game window.
				this.dispose();
			}
		}


	}


	//The following methods are the check Methods. Simply put, each of these methods check for their certain attribute in all of the characters. If they find a character with the 
	//attribute and is not the mystery character, then they will change their icon to a door, signifying that they are not the mystery character.
	private void checkHairColour(String hairColour) {

		if(!mysteryCharacter.getHairColour().equalsIgnoreCase(hairColour)) 
			for(int i =0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].getHairColour().equalsIgnoreCase(hairColour)) characterLabelArray[i].setIcon(DOOR);
	}

	private void checkClothingColour(String ClothingColor) {

		if(!mysteryCharacter.getClothingColour().equalsIgnoreCase(ClothingColor)) 
			for(int i =0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].getClothingColour().equalsIgnoreCase(ClothingColor)) characterLabelArray[i].setIcon(DOOR);


	}
	private void checkHasTail() {
		if(!mysteryCharacter.isHasTail()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isHasTail()) characterLabelArray[i].setIcon(DOOR);

	}


	private void checkHasClaw() {
		if(!mysteryCharacter.isHasClaw()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isHasClaw()) characterLabelArray[i].setIcon(DOOR);			
	}

	private void CheckWearsHat() {
		if(!mysteryCharacter.isWearsHat()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isWearsHat()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckHasVehicle() {
		if(!mysteryCharacter.isHasVehicle()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isHasVehicle()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckHasBeard() {
		System.out.println("Beard");
		if(!mysteryCharacter.isHasBeard()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isHasBeard()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckWearsEarrings() {
		if(!mysteryCharacter.isWearsEarings()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isWearsEarings()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckWearsHelmet() {
		if(!mysteryCharacter.isWearsHelmet()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isWearsHelmet()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckWearsTie() {
		if(!mysteryCharacter.isWearsTie()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isWearsTie()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckHasBeak() {
		if(!mysteryCharacter.isHasBeak()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isHasBeak()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckHasTail() {
		if(!mysteryCharacter.isHasTail()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isHasTail()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckHasGuitar() {
		if(!mysteryCharacter.isHasGuitar()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isHasGuitar()) characterLabelArray[i].setIcon(DOOR);

	}

	private void CheckHasScales() {
		if(!mysteryCharacter.isHasScales()) 
			for(int i = 0; i<characterLabelArray.length; i++) 
				if(characterLabelArray[i].isHasScales()) characterLabelArray[i].setIcon(DOOR);

	}
}
