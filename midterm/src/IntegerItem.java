public class IntegerItem implements Hashable {
  private int integer;

  public IntegerItem(int integer) {

    this.integer = integer;
  }

  @Override
  public int computeHashCode() {
//    the hashcode of IntegerItem is simply the integer value of the IntegerItem
    return integer;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    IntegerItem that = (IntegerItem) obj;
    return integer == that.integer;
  }
  @Override
  public int hashCode() {
    return computeHashCode();
  }
}
