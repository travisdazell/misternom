package travisdazell.net.misternom.framework;

import java.util.List;

/**
 * Created by Travis_Dazell on 3/22/2015.
 */
public interface Input {
    /**
     * Encodes a key pressed event
     */
    public static class KeyEvent {
        //TODO: make these types an ENUM
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type; /* the type of key event */
        public int keyCode; /* the key code for the event */
        public char keyChar; /* the character that was pressed, taking into account upper and lower case */
    }

    /**
     * Encodes a finger touch event
     */
    public static class TouchEvent {
        //TODO: make these types an ENUM
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;

        public int type; /* the type of touch event */
        public int x; /* x-coordinate of the touch event */
        public int y; /* y-coordinate of the touch event */
        public int pointer; /* the pointer for the touch event */
    }

    /**
     * Evaluates whether or not the specified keyCode is pressed
     * @param keyCode the key to evalute
     * @return true if the key specified by keyCode is pressed, false otherwise
     */
    public boolean isKeyPressed(int keyCode);

    /**
     * Evaluates whether or not the specified pointer is down
     * @param pointer the pointer to evaluate
     * @return true if pointer is down, false otherwise
     */
    public boolean isTouchDown(int pointer);

    /**
     * Returns the X-coordinate of the specified pointer
     * @param pointer the pointer to evaluate
     * @return the X-coordinate of the pointer
     */
    public int getTouchX(int pointer);

    /**
     * Returns the Y-coordinate of the specified pointer
     * @param pointer the pointer to evaluate
     * @return the Y-coordinate of the pointer
     */
    public int getTouchY(int pointer);

    /**
     * Returns the acceleration value of the X-axis
     * @return the acceleration value of the X-axis
     */
    public float getAccelX();

    /**
     * Returns the acceleration value of the Y-axis
     * @return the acceleration value of the Y-axis
     */
    public float getAccelY();

    /**
     * Returns the acceleration value of the Z-axis
     * @return the acceleration value of the Z-axis
     */
    public float getAccelZ();

    /**
     * Returns a list of key events that have been recorded since the last time this method was called
     * Key events are listed in order, with the most recent key event at the end of the list
     * @return a list of key events
     */
    public List<KeyEvent> getKeyEvents();

    /**
     * Returns a list of touch events that have been recorded since the last time this method was called
     * Touch events are listed in order, with the most recent touch event at the end of the list
     * @return a list of touch events
     */
    public List<TouchEvent> getTouchEvents();
}
