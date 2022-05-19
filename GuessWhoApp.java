/**
 * @author Kevin Li
 * 
 */

/*Name: Kevin Li 
 * 
 *Date: 2021-12-17
 *
 *Course Number/Teacher: ICS3U1-01 Mr. Sin
 *
 *Title: Guess Who Game(With upgrades :))
 *
 *This is similar to Guess Who, which is a popular game. In this game, you will be presented with 24 characters, as well as 100 seconds. You will then have to ask questions, such as
 *Do they have [color] clothing, or do they have a beard. When you ask a question, all of the characters who have these features, but are not the mystery character, will be closed. 
 *During your prompts, you can receive powerups that increase your time, or Close an extra character off. Once the user asks enough questions, they will be able to guess the mystery character.
 *If they are right, then they win. If they are wrong, they lose. There is also a start screen and a help screen.
 *
 *Major Skills: Swing Components(New Ones like JProgressBar), but also using already learned methods, AudioInputStream, Array Lists, Arrays, File Scans, Action Listeners, Item Listeners, 
 *Lambda Expressions(HelpFrame), JProgressBar, Class/Objects, Sets and Gets, Override ToString
 *
 *Added Features:
 *-List of Characters on the start menu, and during the game(Basic Upgrade)
 *-Added Titles and JLabels for GUI(Basic Upgrade)
 *-Added a timer(Basic Upgrades)
 *-Added an option to reset the game, or return to the main menu(Basic Upgrade)
 *-Added PowerUps, 2 to be exact(Basic Upgrade)
 *-Added Debuffs, 2 to be exact, which have a chance when accepting a gift from Rockstar Foxy(Basic Upgrade?)
 *-Added HelpFrame (Basic Upgrade)
 *-Added StartFrame(Basic Upgrade)
 *-Added Music and Sound Effects(Basic Upgrade)
 *-Added a JProgress Bar, just to make the game feel more gamey(Basic/Advanced Function)
 *-Added funny messages below the bar, which are randomized.
 *-Placed the character randomly onto the panel every game(Advanced Function)
 *-Parsed the question string so that prompts can be asked once
 *
 *Areas of Concern:
 *-In GuessWhoGUI.java, I used a deprecated method called .stop() for thread objects. This was deprecated because it was unsafe to silently kill threads without the knowledge of the JVM.
 *If utilized incorrectly, it could corrupt a java file. However, using it in such a simple way won't put anything at risk.
 *
 *-This program uses multiple threads. In concept, using multiple threads increases the chances of your computer overheating, as your computer has to more and more complex instructions at the same time as others.
 *However, Java multithreading is incredibly lightweight, and after doing a couple of tests(Checking activity monitor), I found little to no memory problems.
 *
 */

//packages 
package GuessWhoGame;

//public class scope
public class GuessWhoApp {

	//main method scope
	public static void main(String[] args) {
	
		//Create a new StartFrame.
		new StartFrame();
	}

}
