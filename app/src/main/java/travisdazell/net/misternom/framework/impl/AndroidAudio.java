package travisdazell.net.misternom.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import travisdazell.net.misternom.framework.Audio;
import travisdazell.net.misternom.framework.Music;
import travisdazell.net.misternom.framework.Sound;

/**
 * Created by Travis_Dazell on 3/23/2015.
 */
public class AndroidAudio implements Audio {
    AssetManager assets;
    SoundPool soundPool;

    /**
     * Takes an Activity, so that we can set the volume control and get access to the AssetManager of the activity
     * The SoundPool is configured to play back 20 sound effects in parallel
     * @param activity
     */
    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    /**
     * Creates a new AndroidMusic instance from a file
     * @param filename the path to the music file
     * @return
     */
    public Music newMusic(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
            return new AndroidMusic(assetFileDescriptor);
        }
        catch (IOException e) {
            // this error is likely a result of our music file not being found in our assets/ directory
            throw new RuntimeException("Couldn't load music '" + filename + "'");
        }
    }

    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetFileDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        }
        catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + filename + "'");
        }
    }
}
