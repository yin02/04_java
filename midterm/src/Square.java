public interface Square extends Hashable {
  // returns the color
  Color getColor();

  // returns the area
  int getArea();

  // changes the color and expands the square
  Square transform();
}
