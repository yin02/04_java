import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SnakeSwingPanel extends JPanel {
    private BufferedImage appleImg;
    private BufferedImage snakeImg;
    private Coordinate appleCo;
    private LinkedList<Coordinate> snakeCo;
    private boolean gameOver = false;

    public SnakeSwingPanel(KeyboardHandler kbh) {
        try {
            appleImg =
                    ImageIO.read(new File("./img/apple.png"));
            snakeImg =
                    ImageIO.read(new File("./img/dot.png"));
        } catch (IOException e) {
            System.out.println("File not found");
        }
        // register the keyboard listener to this panel
        this.setFocusable(true);
        this.addKeyListener(kbh);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameOver) {
            g.drawString("Game Over!!",
                    Configuration.WIDTH / 2, Configuration.HEIGHT / 2);
        } else {
            // paint the apple
            g.drawImage(appleImg, appleCo.x, appleCo.y, null);
            // paint the snake
            for (int i = 0; i < snakeCo.size(); i++) {
                g.drawImage(snakeImg, snakeCo.get(i).x, snakeCo.get(i).y, null);
            }
        }
    }

    public void setAppleLoc(Coordinate appleLoc) {
        appleCo = appleLoc;
    }

    public void setSnakeLoc(LinkedList<Coordinate> snakeLoc) {
        snakeCo = snakeLoc;
    }

    public void setIsGameOver(boolean isGameOver) {
        gameOver = isGameOver;
    }
}
