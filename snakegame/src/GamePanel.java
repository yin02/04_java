import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    // 游戏屏幕的宽度和高度
    static final int SCREEN_WIDTH = 1300;
    static final int SCREEN_HEIGHT = 750;
    // 游戏中的单位大小（像素）
    static final int UNIT_SIZE = 50;
    // 游戏单位的总数
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    // 游戏的更新延迟时间（毫秒）
    static final int DELAY = 175;
    // 存储蛇身体部分的坐标
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    // 蛇的初始身体长度
    int bodyParts = 6;
    // 吃掉的苹果数量
    int applesEaten;
    // 苹果的坐标
    int appleX;
    int appleY;
    // 蛇的移动方向
    char direction = 'R';
    // 游戏是否正在运行
    boolean running = false;
    // 定时器用于游戏循环
    Timer timer;
    // 用于生成随机数
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple(); // 生成一个新的苹果。这个方法会随机设置苹果的位置。

        running = true; // 将游戏的运行状态设置为 true，表示游戏正在进行中。

        timer = new Timer(DELAY, this); // 创建一个新的计时器。计时器会在指定的延迟（DELAY）后触发事件。
        // 这里的 'this' 表示 GamePanel 类的实例本身，因为它实现了 ActionListener 接口。
        // 每当计时器到达指定的延迟时间，它就会调用 GamePanel 的 actionPerformed 方法。

        timer.start(); // 启动计时器。一旦计时器启动，它就会根据设定的 DELAY 值周期性地触发事件，
        // 这将导致游戏的状态定期更新，例如蛇的移动、苹果的检查等。
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); // 调用父类的 paintComponent 方法来处理默认的绘制行为。
        // 这通常包括清除上一次的绘制内容，为新的绘制做准备。

        draw(g); // 调用自定义的 draw 方法来绘制游戏的内容。
        // 这里传递的 Graphics 对象 'g' 用于在面板上进行绘制。
        // 在 draw 方法中，你可以定义具体的绘制逻辑，如绘制蛇、苹果和得分等。
    }


    public void draw(Graphics g) {
        if (running) {
            // 当游戏正在运行时执行以下代码

            // 绘制苹果
            g.setColor(Color.red); // 设置绘制颜色为红色
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); // 在苹果的坐标位置绘制一个椭圆形（苹果）

            // 绘制蛇的身体
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    // 蛇头
                    g.setColor(Color.green); // 设置绘制蛇头的颜色为绿色
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); // 在蛇头的坐标位置绘制一个单位大小的矩形
                } else {
                    // 蛇身
                    g.setColor(new Color(45, 180, 0)); // 设置绘制蛇身的颜色为深绿色
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); // 在蛇身的每个部分的坐标位置绘制单位大小的矩形
                }
            }

            // 显示得分
            g.setColor(Color.red); // 设置绘制颜色为红色
            g.setFont(new Font("Ink Free", Font.BOLD, 40)); // 设置字体样式
            FontMetrics metrics = getFontMetrics(g.getFont()); // 获取字体度量，用于计算文本的宽度
            // 绘制得分文本，位置居中
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        } else {
            // 游戏结束时的处理
            gameOver(g); // 调用 gameOver 方法来绘制游戏结束的画面
        }
    }


    public void newApple() {
        // 生成新苹果的位置
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        // 移动蛇的身体
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // 根据方向移动蛇头
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        // 检查蛇头是否吃到苹果
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // 检查蛇头是否碰到身体
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // 检查蛇头是否触碰边界
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        // 显示游戏结束和得分
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        // 键盘事件处理，控制蛇的移动方向
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
