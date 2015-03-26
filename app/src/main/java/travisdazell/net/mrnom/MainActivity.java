package travisdazell.net.mrnom;

import travisdazell.net.misternom.framework.Screen;
import travisdazell.net.misternom.framework.impl.AndroidGame;


public class MainActivity extends AndroidGame {
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
