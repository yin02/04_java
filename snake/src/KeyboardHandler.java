import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyboardHandler implements KeyListener {
    private Direction dir = Direction.UP;

    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // this function gets called when a key on the keyboard
        // is "pressed"
        int key = e.getKeyCode();
        if (key == VK_DOWN) {
            dir = Direction.DOWN;
        } else if (key == VK_UP) {
            dir = Direction.UP;
        } else if (key == VK_LEFT) {
            dir = Direction.LEFT;
        } else if (key == VK_RIGHT) {
            dir = Direction.RIGHT;
        }
    }

    public Direction getDir() {
        return dir;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }
}
