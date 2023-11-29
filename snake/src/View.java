import java.util.LinkedList;
import java.util.List;

public interface View {
    void setAppleLoc(Coordinate appleLoc);
    void setSnakeLoc(LinkedList<Coordinate> snakeLoc);
    void setIsGameOver(boolean isGameOver);

    // Triggers repainting
    void paint();
}
