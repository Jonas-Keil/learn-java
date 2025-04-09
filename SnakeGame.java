import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    private final int TILE_SIZE = 25;
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private final int DELAY = 200;

    private ArrayList<Point> snake;
    private Point apple;
    private String direction = "RIGHT";
    private boolean gameOver = false;

    private Timer timer;

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        initGame();
    }

    private void initGame() {
        snake = new ArrayList<>();
        snake.add(new Point(5, 5));
        spawnApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void spawnApple() {
        Random rand = new Random();
        apple = new Point(rand.nextInt(WIDTH), rand.nextInt(HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Grid (optional)
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i <= WIDTH; i++) {
            g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, HEIGHT * TILE_SIZE);
        }
        for (int i = 0; i <= HEIGHT; i++) {
            g.drawLine(0, i * TILE_SIZE, WIDTH * TILE_SIZE, i * TILE_SIZE);
        }

        // Apple
        g.setColor(Color.RED);
        g.fillOval(apple.x * TILE_SIZE, apple.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Snake
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // Game Over
        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("Game Over", 50, HEIGHT * TILE_SIZE / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            checkCollision();
        }
        repaint();
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case "UP" -> newHead.y--;
            case "DOWN" -> newHead.y++;
            case "LEFT" -> newHead.x--;
            case "RIGHT" -> newHead.x++;
        }

        snake.add(0, newHead);

        if (newHead.equals(apple)) {
            spawnApple();
        } else {
            snake.remove(snake.size() - 1); // Nicht gewachsen -> letztes Segment entfernen
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);

        // Wand-Kollision
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            gameOver = true;
            timer.stop();
        }

        // Selbst-Kollision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                timer.stop();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Richtungswechsel (nicht rückwärts erlaubt)
        if (key == KeyEvent.VK_UP && !direction.equals("DOWN")) direction = "UP";
        else if (key == KeyEvent.VK_DOWN && !direction.equals("UP")) direction = "DOWN";
        else if (key == KeyEvent.VK_LEFT && !direction.equals("RIGHT")) direction = "LEFT";
        else if (key == KeyEvent.VK_RIGHT && !direction.equals("LEFT")) direction = "RIGHT";
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}