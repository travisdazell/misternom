package travisdazell.net.misternom.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Travis_Dazell on 3/22/2015.
 */
public interface FileIO {
    /**
     * Retrieves a game asset, such as an image
     * @param fileName the path to the game asset
     * @return an InputStream to the game asset, so that it can be read and used
     * @throws IOException
     */
    public InputStream readAsset(String fileName) throws IOException;

    /**
     * Retrieves a file (e.g. a file for reading in high scores)
     * @param fileName the path to the file
     * @return an InputStream to the specified file
     * @throws IOException
     */
    public InputStream readFile(String fileName) throws IOException;

    /**
     * Writes to a file (e.g. a file for storing high scores)
     * @param fileName the path to the file
     * @return an OutputStream to the specified file, for writing
     * @throws IOException
     */
    public OutputStream writeFile(String fileName) throws IOException;
}
