/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author User
 */
public class Sound {
    
    Clip clip;
    URL soundURL;
    
    public Sound(){
       try{
         soundURL = getClass().getResource("/res/sound/Suzume sound.wav");
        AudioInputStream audiostream = AudioSystem.getAudioInputStream(soundURL); 
         clip = AudioSystem.getClip();
         clip.open(audiostream);
       }
       catch(IOException | UnsupportedAudioFileException | LineUnavailableException e){        
       }
               }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
     public void stop(){
        clip.stop();
    }
      
        
}
