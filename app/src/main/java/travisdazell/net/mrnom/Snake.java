package travisdazell.net.mrnom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Travis_Dazell on 3/26/2015.
 */
public class Snake {
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;

    // the first item in the list is the head, followed by the tail parts, starting from the head and
    // going to the end of the snake's tail
    public List<SnakePart> parts = new ArrayList<SnakePart>();
    public int direction;

    public Snake() {
        direction = UP;
        parts.add(new SnakePart(5, 6));
        parts.add(new SnakePart(5, 7));
        parts.add(new SnakePart(5, 8));
    }

    public void turnLeft() {
        direction += 1;
        if (direction > RIGHT) {
            direction = UP;
        }
    }

    public void turnRight() {
        direction -= 1;
        if (direction < UP) {
            direction = RIGHT;
        }
    }

    // adds a new snake part to the end of the list
    public void eat() {
        SnakePart end = parts.get(parts.size() - 1);
        parts.add(new SnakePart(end.x, end.y));
    }

    // to advance the snake, we move each part to the position of the part before it
    // we exclude the head from this process and then move the head according to the direction he's currently facing
    public void advance() {
        SnakePart head = parts.get(0);

        int len = parts.size() - 1;
        for (int i=len; i > 0; i--) {
            SnakePart before = parts.get(i-1);
            SnakePart part = parts.get(i);
            part.x = before.x;
            part.y = before.y;
        }

        // move the snake's head
        if (direction == UP) {
            head.y -= 1;
        }
        if (direction == LEFT) {
            head.x -= 1;
        }
        if (direction == DOWN) {
            head.y += 1;
        }
        if (direction == RIGHT) {
            head.x += 1;
        }
        if (head.x < 0) {
            head.x = 9;
        }
        if (head.x > 9) {
            head.x = 0;
        }
        if (head.y < 0) {
            head.y = 12;
        }
        if (head.y > 12) {
            head.y = 0;
        }
    }

    // returns true if the snake has wrapped around and bitten its own tail
    public boolean checkBitten() {
        int len = parts.size();
        SnakePart head = parts.get(0);
        for (int i=1; i < len; i++) {
            SnakePart part = parts.get(i);
            if (part.x == head.x && part.y == head.y) {
                return true;
            }
        }
        return false;
    }
}
