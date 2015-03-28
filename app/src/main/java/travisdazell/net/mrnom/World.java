package travisdazell.net.mrnom;

import java.util.Random;

/**
 * Created by Travis_Dazell on 3/26/2015.
 */
public class World {
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10; // number of points for each time the snake eats a stain
    static final float TICK_INITIAL = 0.5f; // the number of seconds between each time that the snake moves (which we call a tick)
    static final float TICK_DECREMENT = 0.05f; // the amount of time that we decrease the tick for every 10 stains that are eaten
                                               // this speeds up the game as the game progresses

    public Snake snake;
    public Stain stain;
    public boolean gameOver = false;
    public int score = 0;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT]; // the grid of the game field for placing stains
    Random random = new Random(); // used for generating a random location when placing new stains and the type of stain
    float tickTime = 0; // the time between ticks
    float tick = TICK_INITIAL;

    public World() {
        snake = new Snake();
        placeStain();
    }

    /**
     * Places a stain on the game grid
     */
    private void placeStain() {
        // clear the grid
        for (int x=0; x < WORLD_WIDTH; x++) {
            for (int y=0; y < WORLD_HEIGHT; y++) {
                fields[x][y] = false;
            }
        }

        // set all of the cells that are occupied by the snake to true
        int len = snake.parts.size();
        for (int i=0; i < len; i++) {
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }

        // find a free cell to add the stain
        int stainX = random.nextInt(WORLD_WIDTH);
        int stainY = random.nextInt(WORLD_HEIGHT);
        while (true) {
            if (fields[stainX][stainY] == false) {
                break;
            }

            stainX += 1;
            if (stainX >= WORLD_WIDTH) {
                stainX = 0;
                stainY += 1;
                if (stainY >= WORLD_HEIGHT) {
                    stainY = 0;
                }
            }
        }

        stain = new Stain(stainX, stainY, random.nextInt(3));
    }

    public void update(float deltaTime) {
        if (gameOver) {
            return;
        }

        tickTime += deltaTime;

        while(tickTime > tick) {
            tickTime -= tick;
            snake.advance();

            // if the snake has wrapped around and bit itself, game over
            if (snake.checkBitten()) {
                gameOver = true;
                return;
            }

            // see if the snake has eaten a stain
            SnakePart head = snake.parts.get(0);
            if (head.x == stain.x && head.y == stain.y) {
                score += SCORE_INCREMENT;
                snake.eat();

                // if the snake's size is the entire grid, then the user wins!!!
                if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT) {
                    gameOver = true;
                    return;
                }
                else {
                    // the snake hasn't reached max size, so we place a new stain
                    placeStain();
                }

                // speed up the game if the user reached a multiple of 10 stains eaten
                if ((score % 100 == 0) && ((tick - TICK_DECREMENT) > 0)) {
                    tick -= TICK_DECREMENT;
                }
            }
        }
    }
}
