package ufc.learning.audioplayer;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class AudioPlayerService extends Service implements IAudioPlayer {
    
    private static final String CATEGORIA = "AudioPlayerService";
    
    private AudioPlayer audioPlayer;
    private AudioPlayerServiceBinder binder;
    
    @Override
    public void onCreate() {
        Log.i(CATEGORIA, "onCreate()");
        this.audioPlayer=new AudioPlayer();
        this.binder=new AudioPlayerServiceBinder(this);
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(CATEGORIA, "onStartCommand()");
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    
    @Override
    public boolean onUnbind(Intent intent) {
        //Not needed
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void play() {
        Log.i(CATEGORIA, "play()");
        audioPlayer.play();
    }

    @Override
    public void pause() {
        Log.i(CATEGORIA, "pause()");
        audioPlayer.pause();
    }

    @Override
    public void next() {
        Log.i(CATEGORIA, "next()");
        audioPlayer.next();
    }

    @Override
    public void previous() {
        Log.i(CATEGORIA, "next()");
        audioPlayer.previous();
    }

    @Override
    public void stop() {
        Log.i(CATEGORIA, "stop()");
        audioPlayer.stop();
    }

}
