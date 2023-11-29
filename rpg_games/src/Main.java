import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 创建 GameView 的实例
            GameView gameView = new GameView();

            // 配置游戏窗口
            gameView.setTitle("Game");
            gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameView.pack();
            gameView.setLocationRelativeTo(null); // 居中显示窗口
            gameView.setVisible(true);
        });
    }

    }

