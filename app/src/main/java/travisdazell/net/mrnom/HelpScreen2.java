package travisdazell.net.mrnom;

import java.util.List;

import travisdazell.net.misternom.framework.Game;
import travisdazell.net.misternom.framework.Graphics;
import travisdazell.net.misternom.framework.Input;
import travisdazell.net.misternom.framework.Screen;

/**
 * Created by Travis_Dazell on 3/25/2015.
 */
public class HelpScreen2 extends Screen {

    public HelpScreen2(Game game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        Graphics g = game.getGraphics();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x > g.getWidth() - 64 && event.y > g.getHeight() - 64) {
                    game.setScreen(new HelpScreen3(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.help2, 64, 100);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}