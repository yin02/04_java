import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SnakeSwingView implements View {
    private Coordinate appleCo;
    private LinkedList<Coordinate> snakeCo;
    private boolean gameOver = false;
    private JFrame frame;
    private SnakeSwingPanel panel;

    public SnakeSwingView(KeyboardHandler kbh) {
        this.frame = new JFrame();
        this.panel = new SnakeSwingPanel(kbh);
        panel.setPreferredSize(new Dimension(
                Configuration.WIDTH, Configuration.HEIGHT));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void setAppleLoc(Coordinate appleLoc) {
        this.panel.setAppleLoc(appleLoc);
    }

    @Override
    public void setSnakeLoc(LinkedList<Coordinate> snakeLoc) {
        this.panel.setSnakeLoc(snakeLoc);
    }

    @Override
    public void setIsGameOver(boolean isGameOver) {
        this.panel.setIsGameOver(isGameOver);
    }

    @Override
    public void paint() {
        this.panel.repaint();
    }
}
