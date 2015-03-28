package travisdazell.net.mrnom;

import android.util.Log;

import travisdazell.net.misternom.framework.Screen;
import travisdazell.net.misternom.framework.impl.AndroidGame;


public class MainActivity extends AndroidGame {
    private final String TAG = "Main Activity";

    @Override
    public Screen getStartScreen() {
      Log.i(TAG, "Starting main activity");
      return new LoadingScreen(this);
    }
}
