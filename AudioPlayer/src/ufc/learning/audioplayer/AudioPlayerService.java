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
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void play() {
        audioPlayer.play();
    }

    @Override
    public void pause() {
        audioPlayer.pause();
    }
    
    

}
