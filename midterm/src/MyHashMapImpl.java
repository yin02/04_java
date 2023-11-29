import java.util.Iterator;
public class MyHashMapImpl implements MyHashMap {
  private CustomLinkedList[] table;

  public MyHashMapImpl(int length) {
    table = new CustomLinkedList[length];
    for (int i = 0; i < length; i++) {
      table[i] = new CustomLinkedList();
    }
  }

  @Override
  public void put(IntegerItem item) {
    int index = item.computeHashCode() % table.length;
    if (!table[index].contains(item)) {
      table[index].add(item);
    }
  }

  @Override
  public boolean find(IntegerItem item) {
    int index = item.computeHashCode() % table.length;
    return table[index].contains(item);
  }

  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      private int currentIndex = 0;
      private Iterator<IntegerItem> listIterator = table[currentIndex].iterator();

      @Override
      public boolean hasNext() {
        while (currentIndex < table.length && !listIterator.hasNext()) {
          currentIndex++;
          if (currentIndex < table.length) {
            listIterator = table[currentIndex].iterator();
          }
        }
        return currentIndex < table.length;
      }

      @Override
      public Integer next() {
        if (hasNext()) {
          return listIterator.next().computeHashCode();
        }
        throw new RuntimeException("No more elements");
      }
    };
  }
}
