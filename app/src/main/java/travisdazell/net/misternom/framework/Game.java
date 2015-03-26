package travisdazell.net.misternom.framework;

/**
 * Created by Travis_Dazell on 3/25/2015.
 */
public interface Game {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();
}
