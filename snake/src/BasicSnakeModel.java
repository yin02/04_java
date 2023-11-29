import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class BasicSnakeModel implements Model {
    private Coordinate appleCo;
    private LinkedList<Coordinate> snakeCo;
    // use this hashset to check whether the head is overlapping
    // with any of the existing dots
    private HashSet<Coordinate> snakeCo2;
    private Direction dir = Direction.RIGHT;
    private boolean gameOver = false;

    public BasicSnakeModel() {
        appleCo = new Coordinate(Configuration.WIDTH/2,
                Configuration.HEIGHT/2);
        snakeCo = new LinkedList<>();
        snakeCo2 = new HashSet<>();
        Coordinate c1 = new Coordinate(120, 100);
        snakeCo.add(c1);
        snakeCo2.add(c1);
        Coordinate c2 = new Coordinate(110, 100);
        snakeCo.add(c2);
        snakeCo2.add(c2);
        Coordinate c3 = new Coordinate(100, 100);
        snakeCo.add(c3);
        snakeCo2.add(c3);
    }

    @Override
    public Coordinate getAppleLoc() {
        return appleCo;
    }

    @Override
    public LinkedList<Coordinate> getSnakeLoc() {
        return snakeCo;
    }

    @Override
    public boolean getIsGameOver() {
        return gameOver;
    }

    @Override
    public void setSnakeDir(Direction dir) {
        this.dir = dir;
    }

    @Override
    public void compute() {
        // 1. compute the location of the new head
        int newX = snakeCo.getFirst().x;
        int newY = snakeCo.getFirst().y;
        if (dir == Direction.UP) {
            newY -= 10;
        } else if (dir == Direction.DOWN) {
            newY += 10;
        } else if (dir == Direction.LEFT) {
            newX -= 10;
        } else if (dir == Direction.RIGHT) {
            newX += 10;
        }

        // check whether the new head is going to hit the wall
        // or any part of the snake itself
        if (newX == 0 || newX == Configuration.WIDTH
                || newY == 0 || newY == Configuration.HEIGHT) {
            System.out.println("Game over");
            this.gameOver = true;
        }
        Coordinate newHead = new Coordinate(newX, newY);
        System.out.println(newX + " " + newY);
        if (snakeCo2.contains(newHead)) {
            System.out.println("Game over1");
            // the new head overlaps with one of the existing dots
            // game over
            this.gameOver = true;
        }

        // 2. add a new head
        snakeCo.addFirst(newHead);
        snakeCo2.add(newHead);

        // 3. chop off the tail if the snake is NOT eating the apple
        if (!snakeCo.getFirst().equals(appleCo)) {
            Coordinate removedTail = snakeCo.removeLast();
            snakeCo2.remove(removedTail);
        } else {
            // keep the tail so that the length increase by 1
        }
    }
}
