public interface MyHashMap extends Iterable<Integer> {
  // puts `item` into the hashmap
  void put(IntegerItem item);
  // looks for whether `item` exists in the hashmap
  boolean find(IntegerItem item);
}
