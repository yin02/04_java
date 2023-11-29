import java.util.Iterator;

public class CustomLinkedList implements Iterable<IntegerItem> {
    private class Node {
        IntegerItem item;
        Node next;

        Node(IntegerItem item) {
            this.item = item;
            this.next = null;
        }
    }

    Node head;

    void add(IntegerItem item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    boolean contains(IntegerItem item) {
        Node current = head;
        while (current != null) {
            if (current.item.equals(item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<IntegerItem> iterator() {
        return new Iterator<IntegerItem>() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public IntegerItem next() {
                if (current != null) {
                    IntegerItem item = current.item;
                    current = current.next;
                    return item;
                }
                return null;
            }
        };
    }
}
