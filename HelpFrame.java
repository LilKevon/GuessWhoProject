
//packages
package GuessWhoGame;

//imports
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class HelpFrame extends JFrame {

	//Swing Components
	static JButton DisplayButton = new JButton("Display");
	static JFrame HelpWindow = new JFrame();
	static JComboBox<String> HelpSelection = new JComboBox<>();
	static JLabel TitleLabel = new JLabel("Help Screen");
	String item;
	
//Constructor
HelpFrame(){
	
	//Call the FillHelpBox and FillFrames functions.
	FillHelpBox();
	FillFrames();
	
	//ItemListener to listen for Item changes in JComboBox
			HelpSelection.addItemListener(event -> {//Item listener scope start

				//Will store the current selection in the JComboBox
				item = (String) event.getItem();
			


			});//Item listener scope end
			

			//ActionListener to listen for clicks on DisplayButton
			DisplayButton.addActionListener(new ActionListener() {//action listener scope start

				
				//Action performed when an action is fired.
				@Override
				public void actionPerformed(ActionEvent e) {

					//If statements checking the contents of item, and executing the proper commands based on the content.
					if(item.trim().equals("Starting out")) {
						JOptionPane.showMessageDialog(HelpFrame.this, "To start a new game, press the start button on the Welcome Screen. Wait for it to load, and then start playing the game.", "Starting Out", JOptionPane.INFORMATION_MESSAGE);

				}
					
					else if(item.trim().equals("GamePlay")) {
						Icon Picture = new ImageIcon("C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\GuessWhoGame\\Game.PNG");
						JOptionPane.showMessageDialog(HelpFrame.this, "When the game begins, the timer will start. You will have 100 seconds to find the mystery question.\n To ask a question, press the ask button and type the question.\n Refer to the prompts button to find prompts to enter.\n After an question has been asked, all of the characters that have that attribute will have their door closed. \n Keep doing this until you find the mystery character, or the time runs out!", "Gameplay", JOptionPane.INFORMATION_MESSAGE, Picture);
					}
					
					else if(item.trim().equals("Powerups")) {
						
						JOptionPane.showMessageDialog(HelpFrame.this, "Rockstar Foxy has a 10% of spawning, and when he does, he brings a power up! \n When he appears, you will recieve one of the 2 powerups. You will either get: \n +20 time: You are granted +20 seconds on your countdown, giving you more time to find the mystery character. \n Delete a Character: A character that is not the mystery character will be closed off instantly. \n You have the option to either accept his gift, or not.", "Power Ups", JOptionPane.INFORMATION_MESSAGE);

					}
					
					else if(item.trim().equals("Winning/Losing")) {
						JOptionPane.showMessageDialog(HelpFrame.this, "If you find the mystery character, you will be prompted with a winner screen. The game will then ask if you want to play again. Press yes to start the game again, and press no to go back to the start screen. \n If you guess wrong, or you run out of time, the same choices will be given.", "Winning", JOptionPane.INFORMATION_MESSAGE);

					}
					

				}

			});//action listener scope end
	
}

//Filling the JComboBox with options
private void FillHelpBox() {
	HelpSelection.addItem("Starting out");
	HelpSelection.addItem("GamePlay");
	HelpSelection.addItem("Powerups");
	HelpSelection.addItem("Winning/Losing");
}

//Filling the frames by setting bounds to the Swing Components, and adding them to the frame. Then setting the frame up.
private void FillFrames() {
	TitleLabel.setBounds(10,-20,300,100);
	TitleLabel.setFont(new Font("Ariel", Font.BOLD, 20));
	
	DisplayButton.setBounds(10, 50,100,25);
	
	HelpSelection.setBounds(10,100,200,25);
	
	HelpWindow.add(TitleLabel);
	HelpWindow.add(HelpSelection);
	HelpWindow.add(DisplayButton);
	
	HelpWindow.setTitle("Help Screen");
	HelpWindow.setLayout(null);
	HelpWindow.setSize(500,500);
	HelpWindow.setVisible(true);
}
}