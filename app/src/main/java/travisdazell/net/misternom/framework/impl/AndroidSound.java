package travisdazell.net.misternom.framework.impl;

import android.media.SoundPool;

import travisdazell.net.misternom.framework.Sound;

/**
 * Created by Travis_Dazell on 3/23/2015.
 */
public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    public void dispose() {
        soundPool.unload(soundId);
    }
}
