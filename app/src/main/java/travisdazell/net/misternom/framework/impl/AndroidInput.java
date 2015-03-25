package travisdazell.net.misternom.framework.impl;

import android.content.Context;
import android.os.Build;
import android.view.View;

import java.util.List;

import travisdazell.net.misternom.framework.Input;
import travisdazell.net.misternom.framework.TouchHandler;

/**
 * Created by Travis_Dazell on 3/24/2015.
 */
public class AndroidInput implements Input {
    AccelerometerHandler accelerometerHandler;
    KeyboardHandler keyboardHandler;
    TouchHandler touchHandler;

    public AndroidInput(Context context, View view, float scaleX, float scaleY) {
        accelerometerHandler = new AccelerometerHandler(context);
        keyboardHandler = new KeyboardHandler(view);
        if (Integer.parseInt(Build.VERSION.SDK) < 5) {
            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
        }
        else {
            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
        }
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return keyboardHandler.isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX() {
        return accelerometerHandler.getAccelX();
    }

    @Override
    public float getAccelY() {
        return accelerometerHandler.getAccelY();
    }

    @Override
    public float getAccelZ() {
        return accelerometerHandler.getAccelY();
    }

    @Override
    public List<KeyEvent> getKeyEvents() {
        return keyboardHandler.getKeyEvents();
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}
