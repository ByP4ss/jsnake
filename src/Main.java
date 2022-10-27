import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends JFrame {
    public static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public static final List<Snake> snakes = new ArrayList<>();
    public static final Main main = new Main();
    private static Apple apple;
    public final Graphics g;
    public static int score;

    public static void main(String[] args) {
        Input.main();
        snakes.add(new Snake(main, 408, 375, true));
        apple = new Apple();
        executor.scheduleWithFixedDelay(run, 1000, 500, TimeUnit.MILLISECONDS);
    }

    public Main() {
        super("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(816, 833);
        setVisible(true);
        setResizable(false);
        score = 0;
        g = getGraphics();
    }

    public void draw() {
        paint(g);
        g.setColor(Color.GREEN);
        for (Snake snake:snakes) {
            g.fillRect(snake.posX, snake.posY, 50 , 50);
        }
        apple.check(snakes.get(0));
        g.setColor(Color.RED);
        g.fillOval(apple.posX, apple.posY, 50, 50);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.clearRect(0, 0, 50, 100);
        g.drawString(String.valueOf(Main.score), 10, 50);
    }

    private void movement() {
        for (int i = snakes.size()-1; i >= 0; i--) {
            Snake snake = snakes.get(i);
            if (i == 0) {
                switch (Input.inputState) {
                    case 1 -> snake.right();
                    case 2 -> snake.left();
                    case 3 -> snake.up();
                    case 4 -> snake.down();
                    default -> draw();
                }
                return;
            }
            snake.tp(snakes.get(i-1).posX, snakes.get(i-1).posY);
        }
    }

    public void gameover() {
        executor.shutdown();
        paint(g);
        g.setColor(Color.RED);
        g.setFont(new Font("Courier", Font.BOLD,50));
        g.drawString("Game Over", 275, 375);
        g.setFont(new Font("Courier", Font.PLAIN, 25));
        g.drawString("Press Space to try again!", 265, 400);
        snakes.clear();
    }

    public static void restart() {
        Input.inputState = 0;
        executor.shutdown();
        score = 0;
        snakes.clear();
        manageSnakes();
        apple.clear();
        apple.rePos();
        main.getGraphics().clearRect(225, 275, 350, 200);
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(run, 1000, 500, TimeUnit.MILLISECONDS);
    }

    private static void manageSnakes() {
        if (snakes.isEmpty()) {
            snakes.add(new Snake(main, 408, 375, true));
        }
        if (snakes.size() <= score) {
            Snake snake = snakes.get(snakes.size() - 1);
            int xFact = 0;
            int yFact = 0;
            int x = snake.posX + xFact;
            int y = snake.posY + yFact;
            snakes.add(new Snake(main, x+1, y, false));
        }
    }
    private static void run() {
        manageSnakes();
        main.movement();
        snakes.get(0).checkContact();
        main.draw();
    }

    private static final Runnable run = Main::run;
}
