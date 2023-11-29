import java.util.LinkedList;
import java.util.List;

public interface Model {
    Coordinate getAppleLoc();
    LinkedList<Coordinate> getSnakeLoc();
    boolean getIsGameOver();
    void setSnakeDir(Direction dir);

    // triggers a run of computations
    void compute();
}
