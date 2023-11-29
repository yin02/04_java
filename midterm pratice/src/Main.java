import java.util.Iterator;
import java.util.LinkedList;

public class IteratorExample {
    public static void main(String[] args) {
        LinkedList<String> cities = new LinkedList<String>();
        cities.add("New York");
        cities.add("London");
        cities.add("San Francisco");
        cities.add("Tokyo");

        // 获取迭代器
        Iterator<String> it = cities.iterator();

        // 使用迭代器遍历LinkedList
        while (it.hasNext()) {
            String city = it.next();
            System.out.println(city);
        }
    }
}

