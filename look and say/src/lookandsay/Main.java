import lookandsay.LookAndSayIterator;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger seed = new BigInteger("112321");
        LookAndSayIterator cur = new LookAndSayIterator(seed);
        System.out.println(cur.hasNext());
        System.out.println(cur.next());

        System.out.println(cur.next());

        System.out.println(cur.hasPrevious());
        System.out.println(cur.prev());
        System.out.println(cur.prev());
        System.out.println(cur.prev());
        System.out.println(cur.prev());
        System.out.println(cur.hasPrevious());
        System.out.println(cur.prev());

    }


}
