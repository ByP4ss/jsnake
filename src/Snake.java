import javax.swing.JFrame;

public class Snake extends JFrame{
    public int posX, posY;
    private final Main main;
    private final boolean head;
    public Snake(Main in, int inPosX, int inPosY, boolean head) {
        posX = inPosX;
        posY = inPosY;
        main = in;
        this.head = head;
    }
    public void right() {
        clear();
        posX += 50;
        main.draw();
    }
    public void left() {
        clear();
        posX -= 50;
        main.draw();
    }
    public void up() {
        clear();
        posY -= 50;
        main.draw();
    }
    public void down() {
        clear();
        posY += 50;
        main.draw();
    }

    public void tp(int x, int y) {
        clear();
        posX = x;
        posY = y;
        main.draw();
    }
    private void clear() {
        main.g.clearRect(posX, posY, 50, 50);
    }
    public void checkContact() {
        if (posX < -17 || posX > 783 || posY < 0 || posY > 800) {
            //TODO: Fix Game Over not showing when snake hits wall
            main.gameover();
            return;
        }
        if (head) {
            for (Snake snake:Main.snakes) {
                if (posX == snake.posX && posY == snake.posY && !snake.head) {
                    main.gameover();
                }
            }
        }
    }
}

