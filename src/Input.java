import java.awt.*;
import java.awt.event.KeyEvent;

public class Input {
    public static volatile int inputState;
    public static void main() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case 39 -> {
                        if (inputState == 2) inputState = 2;
                        else inputState = 1;
                    }
                    case 37 -> {
                        if (inputState == 1)inputState = 1;
                        else inputState = 2;
                    }
                    case 38 -> {
                        if (inputState == 4) inputState = 4;
                        else inputState = 3;
                    }
                    case 40 -> {
                        if (inputState == 3) inputState = 3;
                        else inputState = 4;
                    }
                }
            }
            if (e.getKeyCode() == 32) {
                Main.restart();
            }
            return false;
        });
    }
}
