import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class GamePanel extends JPanel {
    private GameModel gameModel;

    public GamePanel(GameModel gameModel) {
        this.gameModel = gameModel;
        setPreferredSize(new Dimension(800, 600)); // Set the panel size

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }


    private void handleKeyPress(KeyEvent e) {
        GameModel.Character playerCharacter = gameModel.getPlayerCharacter();

        switch (e.getKeyCode()) {
// Corrected movement methods in GamePanel
            case KeyEvent.VK_UP:
                playerCharacter.moveCharacterUp();
                break;
            case KeyEvent.VK_DOWN:
                playerCharacter.moveCharacterDown();
                break;
            case KeyEvent.VK_LEFT:
                playerCharacter.moveCharacterLeft();
                break;
            case KeyEvent.VK_RIGHT:
                playerCharacter.moveCharacterRight();
                break;

            // Handle other key presses for item selection (e.g., 1, 2, 3, ...)
            case KeyEvent.VK_1:
                equipSelectedItem(0); // Equip the first item
                break;
            case KeyEvent.VK_2:
                equipSelectedItem(1); // Equip the second item
                break;
            // Add more cases for item selection keys as needed
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGameEnvironment(g);
        drawGameCharacters(g);
        drawGameItems(g);
    }

    private void drawGameEnvironment(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawGameCharacters(Graphics g) {
        GameModel.Character character = gameModel.getPlayerCharacter();
        g.setColor(Color.RED);
        g.fillOval(character.getX(), character.getY(), 30, 30); // Example character representation
    }

    private void drawGameItems(Graphics g) {
        g.setColor(Color.BLUE);
        for (GameModel.Item item : gameModel.getItems()) {
            g.fillRect(50, 50, 20, 20); // Placeholder position and size
        }
    }
    private void equipSelectedItem(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < gameModel.getItems().size()) {
            GameModel.Item selectedItem = gameModel.getItems().get(selectedIndex);
            gameModel.equipItem(selectedItem);
        }
    }


    // Additional game panel methods...
}
