import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Timer timer;
    private Model m;
    private View v;
    private KeyboardHandler kbh;

    public Controller() {
        timer = new Timer(100, this);
        kbh = new KeyboardHandler();
        m = new BasicSnakeModel();
        //m = new MockSnakeModel();
        v = new SnakeSwingView(kbh);
    }

    public void run() {
        timer.start();
    }

    // This function is triggered whenever the timer fires
    @Override
    public void actionPerformed(ActionEvent e) {
        m.setSnakeDir(kbh.getDir());
        m.compute();

        v.setAppleLoc(m.getAppleLoc());
        v.setSnakeLoc(m.getSnakeLoc());
        v.setIsGameOver(m.getIsGameOver());

        v.paint();
    }

    public static void main(String[] args) {
        Controller c = new Controller();
        c.run();
    }
}
