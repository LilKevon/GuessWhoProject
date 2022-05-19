package GuessWhoGame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

//Create a thread extended class, as the background music must be played on a separate thread in order to be able to run with the game as well.
public class MusicThread extends Thread {
  
//Run method
public void run() {

	//Get the File location
    File audioFile =new File("C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\GuessWhoGame\\Jeopardy Main Theme.wav");
    
    //Attempt to play the file. There could be several exceptions, such as a general IOException, LineUnavailableException, and UnsupportedAudioFileException. These were the exceptions I had to catch.
    try {
    	
    	//Create a new AudioInputStream. This is a input stream with a specified audio format and length.
        AudioInputStream w = AudioSystem.getAudioInputStream(audioFile);
       
        //Finds the specific audio format(mp3, wav)
        AudioFormat a = w.getFormat();

        //Attempts to collect the data lines from the audio format of the audioInputStream. It returns information about a supported line.
        DataLine.Info i = new DataLine.Info(SourceDataLine.class, a);
        
        //The SourceDataLine of the DataLine.Info. it completes the writing the audio data to the data line's buffer. This is final step in creating a playable audio file.
        SourceDataLine audioL = (SourceDataLine) AudioSystem.getLine(i);

        //Open the SourceDataLine, and start the file.
        audioL.open();
        audioL.start();

        //Inform the programmer the audio started. Used during debugging
        System.out.println("PlayBack Started");

        //Create a new byte array with size 4096, and int byr. This array will be used to hold the binary data of the file.
        byte []b = new byte[4096];
        int byt = -1;

        //As lomg as byt has another line to read in the binary data, keep looping(-1 means no more data)
        while((byt = w.read(b)) != -1) {
        	
        	//Writes audio data to the mixer via this source data line. The requested number of bytes of data are read from the specified array, starting at the given offset into the array, and written to the data line's buffer.
            audioL.write(b, 0, byt);
        }

        //Drains/Flushes any remaining data left in the SourceDataLine
        audioL.drain();
        
        //Close the file
        audioL.close();
        
        //CLose the Stream, ending the song
        w.close();

        
        //Informing the user that the play back is done. Used in debugging.
        System.out.println("Playback Done");
        
    //Catch the exceptions that might be thrown
    }catch (UnsupportedAudioFileException ex) {
        System.out.println("Unsupported Audio File.");
        ex.printStackTrace();
    } catch (LineUnavailableException ex) {
        System.out.println("Line Unavailable Exception.");
        ex.printStackTrace();
    } catch (IOException ex) {
        System.out.println("IOException Thrown.");
        ex.printStackTrace();
    }
}



}

//Inspired(Not Copied) by: https://stackoverflow.com/questions/13573281/how-to-play-a-background-music-when-the-program-run-in-java
//Extracts of Information from: https://docs.oracle.com/javase/7/docs/api/javax/sound/sampled/SourceDataLine.html