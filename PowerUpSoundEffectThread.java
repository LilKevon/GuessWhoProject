package GuessWhoGame;
//NOTE: REFER TO MUSICTHREAD.JAVA IN ORDER TO SEE COMMENTS ABOUT THE CODE BELOW.
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PowerUpSoundEffectThread extends Thread {
  
public void run() {


    File audioFile =new File("C:\\Users\\16475\\eclipse-workspace\\GuessWhoProject2\\src\\GuessWhoGame\\Yarg.wav");
    try {
        AudioInputStream w = AudioSystem.getAudioInputStream(audioFile);

        AudioFormat a = w.getFormat();

        DataLine.Info i = new DataLine.Info(SourceDataLine.class, a);

        SourceDataLine audioL = (SourceDataLine) AudioSystem.getLine(i);

        audioL.open();
        audioL.start();

        System.out.println("PlayBackStarted");

        byte []b = new byte[4096];
        int byt = -1;

        while((byt = w.read(b)) != -1) {
            audioL.write(b, 0, byt);
        }

        audioL.drain();
        audioL.close();
        w.close();
        

        System.out.println("Done");
 
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