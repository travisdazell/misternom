package travisdazell.net.misternom.framework;

import android.view.View;

import java.util.List;

/**
 * Created by Travis_Dazell on 3/24/2015.
 */
public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<Input.TouchEvent> getTouchEvents();
}
