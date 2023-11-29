import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class MockSnakeModel implements Model {
    @Override
    public Coordinate getAppleLoc() {
        return new Coordinate(250, 200);
    }

    @Override
    public LinkedList<Coordinate> getSnakeLoc() {
        LinkedList<Coordinate> snakeCo = new LinkedList<>();
        Coordinate c1 = new Coordinate(120, 100);
        snakeCo.add(c1);
        Coordinate c2 = new Coordinate(110, 100);
        snakeCo.add(c2);
        Coordinate c3 = new Coordinate(100, 100);
        snakeCo.add(c3);
        return snakeCo;
    }

    @Override
    public boolean getIsGameOver() {
        return false;
    }

    @Override
    public void setSnakeDir(Direction dir) {

    }

    @Override
    public void compute() {

    }
}
