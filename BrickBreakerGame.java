import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BrickBreakerGame extends JPanel implements ActionListener {
    private int ballX = 150;
    private int ballY = 150;
    private int ballXSpeed = 1;
    private int ballYSpeed = 1;

    private int paddleX = 150;
    private int paddleSpeed = 4;

    private boolean[] bricks = new boolean[48];
    private int brickRows = 4;
    private int brickCols = 12;

    private Timer timer;

    public BrickBreakerGame() {
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    paddleX -= paddleSpeed;
                }
                if (key == KeyEvent.VK_RIGHT) {
                    paddleX += paddleSpeed;
                }
            }
        });

        initializeBricks();

        timer = new Timer(5, this);
        timer.start();
    }

    public void initializeBricks() {
        for (int i = 0; i < brickRows; i++) {
            for (int j = 0; j < brickCols; j++) {
                bricks[i * brickCols + j] = true;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        // Ball collision with walls
        if (ballX <= 0 || ballX >= getWidth()) {
            ballXSpeed = -ballXSpeed;
        }
        if (ballY <= 0) {
            ballYSpeed = -ballYSpeed;
        }

        // Ball collision with paddle
        if (ballY >= getHeight() - 20 && ballX >= paddleX && ballX <= paddleX + 60) {
            ballYSpeed = -ballYSpeed;
        }

        // Ball collision with bricks
        int brickWidth = getWidth() / brickCols;
        int brickHeight = 20;
        for (int i = 0; i < brickRows; i++) {
            for (int j = 0; j < brickCols; j++) {
                if (bricks[i * brickCols + j]) {
                    int brickX = j * brickWidth;
                    int brickY = i * brickHeight;
                    if (ballX >= brickX && ballX <= brickX + brickWidth &&
                        ballY >= brickY && ballY <= brickY + brickHeight) {
                        bricks[i * brickCols + j] = false;
                        ballYSpeed = -ballYSpeed;
                    }
                }
            }
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw ball
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 20, 20);

        // Draw paddle
        g.fillRect(paddleX, getHeight() - 20, 60, 10);

        // Draw bricks
        int brickWidth = getWidth() / brickCols;
        int brickHeight = 20;
        for (int i = 0; i < brickRows; i++) {
            for (int j = 0; j < brickCols; j++) {
                if (bricks[i * brickCols + j]) {
                    g.setColor(Color.RED);
                    g.fillRect(j * brickWidth, i * brickHeight, brickWidth, brickHeight);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker Game");
        BrickBreakerGame game = new BrickBreakerGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
