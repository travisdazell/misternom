package travisdazell.net.misternom.framework;

import android.util.Log;

/**
 * Created by Travis_Dazell on 3/25/2015.
 */
public abstract class Screen {
    private final String TAG = "Screen";
    protected final Game game;

    public Screen(Game game) {
        Log.i(TAG, "in constructor in Screen");
        this.game = game;
        Log.i(TAG, "end of constructor in Screen");
    }

    public abstract void update(float deltaTime);

    public abstract void present(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
