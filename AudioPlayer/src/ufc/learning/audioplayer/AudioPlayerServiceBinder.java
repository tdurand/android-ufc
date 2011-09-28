package ufc.learning.audioplayer;

import android.os.Binder;

public class AudioPlayerServiceBinder extends Binder{ 
    
    private IAudioPlayer service = null; 
  
    public AudioPlayerServiceBinder(IAudioPlayer service) { 
        super(); 
        this.service = service; 
    } 
 
    public IAudioPlayer getService(){ 
        return service; 
    } 
};
