package ufc.learning.audioplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity implements ServiceConnection {
    
    private IAudioPlayer audioPlayerService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Start service in background
        startService(new Intent("SERVICE_AUDIOPLAYER"));
        //Bind service to be able to interact with it
        bindService(new Intent("SERVICE_AUDIOPLAYER"), this, Context.BIND_AUTO_CREATE);
        
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i("AudioPlayerService", "Connected!"); 
        audioPlayerService =((AudioPlayerServiceBinder)service).getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        // TODO Auto-generated method stub
        
    }
    
    public void play(View v) {
        audioPlayerService.play();
    }
    public void pause(View v) {
        audioPlayerService.pause();
    }
    
    public void next(View v) {
        audioPlayerService.next();
    }
    public void previous(View v) {
        audioPlayerService.previous();
    }
    public void stop(View v) {
        audioPlayerService.stop();
    }
}