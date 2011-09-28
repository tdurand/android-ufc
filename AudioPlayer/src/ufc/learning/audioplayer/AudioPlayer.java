package ufc.learning.audioplayer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import android.media.MediaPlayer;
import android.util.Log;

public class AudioPlayer  {
    
    private MediaPlayer audioPlayer; 
    private int currentTrack;
    private List<String> listMusic=Arrays.asList("/data/local/tmp/06.mp3",
                                                "/data/local/tmp/01.mp3",
                                                "/data/local/tmp/07.mp3",
                                                "/data/local/tmp/04.mp3");
    
    public AudioPlayer() {
        currentTrack=0;
        audioPlayer=new MediaPlayer();
        setTrack(currentTrack);
    }
    
    public void pause() {
        audioPlayer.pause();
    }
    
    public void play() {
        audioPlayer.start();
    }
    
    public void next() {
        if(currentTrack+1<listMusic.size()) {
            //Go to next track
            setTrack(currentTrack+1);
            currentTrack++;
        }
        else {
            //We reload this track
            setTrack(currentTrack);
        }
        play();
    }
    
    public void previous() {
        if(currentTrack-1>0) {
            //Go to previous track
            setTrack(currentTrack-1);
            currentTrack--;
        }
        else {
            //We reload this track
            setTrack(currentTrack);
        }
        play();
    }
    
    public void stop() {
        //reinit the currentTrack
        audioPlayer.stop();
        currentTrack=0;
        setTrack(0);
    }
    
    public void setTrack(int newTrack) {
        try {
            Log.i("AudioPlayer", "Reset"); 
            audioPlayer.reset();
            Log.i("AudioPlayer", "Set new Track"); 
            audioPlayer.setDataSource(listMusic.get(newTrack));
            Log.i("AudioPlayer", "Preparing new track"); 
            audioPlayer.prepare();
            Log.i("AudioPlayer", "New track prepared");
        } catch (IllegalArgumentException e) {
            Log.i("AudioPlayer", "Error while configuring new track");
        } catch (IllegalStateException e) {
            Log.i("AudioPlayer", "Error while configuring new track");
        } catch (IOException e) {
            Log.i("AudioPlayer", "Error while configuring new track");
        }
    }

}
