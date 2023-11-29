import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameView extends JFrame {
    private Map<String, ImageIcon> itemImages;

    public GameView() {
        itemImages = new HashMap<>();
        loadItemImages();
        initializeUI();
    }

    private void loadItemImages() {
        String[] itemNames = {
                "boots", "footwear", "gloves", "hat", "helmets", "hoverboard", "shield", "sneakers", "swords", "visor"
        };

        for (String itemName : itemNames) {
            URL imgUrl = getClass().getResource("/img/" + itemName + ".png");
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                itemImages.put(itemName, icon);
            } else {
                System.err.println("Resource not found: /img/" + itemName + ".png");
                // Consider a fallback image or handling strategy here
            }
        }
    }



    private void initializeUI() {
        // Set up your UI here and add your images to your components
        setTitle("RPG Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null); // Center the window

        // Example of adding an image to a label and adding it to the frame
        JLabel label = new JLabel();
        label.setIcon(itemImages.get("helmets")); // Set the icon to the helmet image
        add(label);

        // Set the frame to visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameView();
            }
        });
    }
}
