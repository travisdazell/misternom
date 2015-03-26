package travisdazell.net.mrnom;

import java.util.List;

import travisdazell.net.misternom.framework.Game;
import travisdazell.net.misternom.framework.Graphics;
import travisdazell.net.misternom.framework.Input;
import travisdazell.net.misternom.framework.Screen;

/**
 * Created by Travis_Dazell on 3/25/2015.
 */
public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    /**
     * Loops through all of the touch events
     * For each touch event, we check if it's on a button or area we care about
     * @param deltaTime
     */
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i=0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    // the user clicked on the sound toggle
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                }

                if (inBounds(event, 64, 220, 192, 42)) {
                    // the user clicked on the button to start the game, so we transition to the Game screen
                    // and play a click sound if the sound is enabled
                    game.setScreen(new GameScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }

                if (inBounds(event, 64, 220 + 42, 192, 42)) {
                    // the user clicked on the high scores button
                    game.setScreen(new HighscoreScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }

                if (inBounds()) {
                    // the user clicked on the help button
                    game.setScreen(new HelpScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }
            }
        }
    }

    /**
     * Evaluates whether or not a TouchEvent is within the specified screen coordinates
     * @param event
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if ((event.x > x) && (event.x < x + width - 1) &&
                (event.y > y) && (event.y < y + height - 1)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Renders the background and the foreground images (e.g. buttons) on the screen
     * @param deltaTime
     */
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 32, 20);
        g.drawPixmap(Assets.mainMenu, 64, 220);

        if (Settings.soundEnabled) {
            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        }
        else {
            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        }
    }

    /**
     * Saves the settings (since the user could change one of the settings on the Pause screen) and pauses the game
     */
    public void pause() {
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}
