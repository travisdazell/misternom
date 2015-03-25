package travisdazell.net.misternom.framework;

/**
 * Created by Travis_Dazell on 3/22/2015.
 */
public interface Audio {
    /**
     * Retrieves a music file for streaming (e.g. background music for the game)
     * @param filename the path to the music file
     * @return the Music specified by the filename
     */
    public Music newMusic(String filename);

    /**
     * Retrieves a sound effect
     * @param filename the path to the sound effect clip
     * @return the Sound specified by the filename
     */
    public Sound newSound(String filename);
}
