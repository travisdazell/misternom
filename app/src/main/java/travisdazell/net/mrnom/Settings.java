package travisdazell.net.mrnom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;

import travisdazell.net.misternom.framework.FileIO;

/**
 * Created by Travis_Dazell on 3/25/2015.
 *
 * This class stores user settings (sound enabled/disabled, history of high scores)
 */
//TODO: we could probably just use Context.getExternalFilesDir() to get and set the settings,
// although that requires API level 8 or greater (probably safe to do at this point)
public class Settings {
    public static boolean soundEnabled = true;
    public static int[] highScores = new int[] { 100, 80, 50, 30, 10};

    public static void load(FileIO files) {
        BufferedReader in = null;

        // we load the settings from a file named ".mrnom" which is in external storage
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile(".mrnom")));
        }
        catch (IOException e) {
            // we'll just swallow this exception and go with the default value
        }
        catch (NumberFormatException e) {
            // we'll just swallow this exception and go with the default value
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {

            }
        }
    }

    /**
     * Saves the user settings
     * This class writes the settings in 6 lines:
     *  - True or False for the sound enabled setting
     *  - A list of the high scores
     * @param files
     */
    public static void save(FileIO files) {
        BufferedWriter out = null;

        try {
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".mrnom")));
            out.write(Boolean.toString(soundEnabled));
            for (int i=0; i < 5; i++) {
                out.write(Integer.toString(highScores[i]));
            }
        }
        catch (IOException e) {

        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            }
            catch (IOException e) {

            }
        }
    }

    /**
     * Adds a new score to the high scores list
     * @param score
     */
    public static void addScore(int score) {
        for (int i=0; i < 5; i++) {
            if (highScores[i] < score) {
                for (int j=4; j > 1; j--) {
                    highScores[j] = highScores[j-1];
                    highScores[i] = score;
                    break;
                }
            }
        }
    }
}
