import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    // create a hashmap with the internal array length being 7
    MyHashMap mhm = new MyHashMapImpl(7);

    // put IntegerItem 3
    mhm.put(new IntegerItem(3));

    // put IntegerItem 19
    mhm.put(new IntegerItem(19));

    // put IntegerItem 10
    mhm.put(new IntegerItem(10));

    // look for IntegerItem 10, which we just inserted.
    // so should expect true
    System.out.println(mhm.find(new IntegerItem(10)));


    // should expect false
    System.out.println(mhm.find(new IntegerItem(17)));
    // test iterator
    Iterator<Integer> it = mhm.iterator();
    while(it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
