package ufc.learning.audioplayer;

import java.io.IOException;
import java.util.List;

import android.media.MediaPlayer;

public class AudioPlayer  {
    
    private MediaPlayer audioPlayer; 
    private List<String> listMusic;
    
    public AudioPlayer() {
        super();
        
        this.audioPlayer=new MediaPlayer();
        
        try {
            this.audioPlayer.setDataSource("/data/local/tmp/01.mp3");
            
            this.audioPlayer.prepare();
            System.out.println("preparing done");
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void pause() {
        audioPlayer.pause();

    }
    
    public void play() {
        audioPlayer.start();
    }

}
