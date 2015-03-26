package travisdazell.net.misternom.framework;

/**
 * Created by Travis_Dazell on 3/25/2015.
 */
public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();
}
