package travisdazell.net.misternom.framework;

/**
 * Music to be streamed, such as background music for the game
 * Created by Travis_Dazell on 3/22/2015.
 */
public interface Music {
    public void play();

    public void stop();

    public void pause();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isStopped();

    public boolean isLooping();

    public void dispose();
}
