import java.util.Random;
import javax.swing.JFrame;

public class Apple extends JFrame{
    public int posX, posY;

    public Apple() {
        posX = randCord()-17;
        posY = randCord();
    }
    public void rePos() {
        posX = randCord()-17;
        posY = randCord();
        for (Snake snake:Main.snakes) {
            while (snake.posX == posX && snake.posY == posY) {
                posX = randCord()-17;
                posY = randCord();
            }
        }
    }
    public void clear() {
        Main.main.g.clearRect(posX, posY, 50, 50);
    }
    public int randCord() {
        Random random = new Random();
        int out = random.ints(100, 700)
                .findFirst()
                .getAsInt();
        out = ((out+49)/50 * 50)+25;
        if (out < 400 && out > 350) {
            return randCord();
        }
        return out;
    }
    public void check(Snake snake) {
        if (Math.abs(posX - snake.posX) < 50 && Math.abs(posY - snake.posY) < 50) {
            rePos();
            Main.score++;
        }
    }
}
