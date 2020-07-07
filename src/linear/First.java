package linear;

import javax.swing.plaf.nimbus.State;

public class First {

    public static void main(String args[])
    {
        LinearHashing<MyNumber> h = new LinearHashing<MyNumber>(7);
        var s = new StatefulLinearHashing<MyNumber>(7);

//        System.out.println(h.add(new MyNumber(78)));
//        System.out.println(h.add(new MyNumber(57)));
//        System.out.println(h.add(new MyNumber(80)));
//        System.out.println(h.add(new MyNumber(23)));
        h.add(new MyNumber(23));
        h.add(new MyNumber(30));
        h.add(new MyNumber(37));
        s.add(new MyNumber(23));
        s.add(new MyNumber(30));
        s.add(new MyNumber(37));


        h.delete(new MyNumber(30));
        s.delete(new MyNumber(30));

        h.delete(new MyNumber(37));
        s.delete(new MyNumber(37));

        h.delete(new MyNumber(23));
        s.delete(new MyNumber(23));

        h.add(new MyNumber(42));
        s.add(new MyNumber(42));

        System.out.println(h);
        System.out.println(s);

        System.out.println(h.isIn(new MyNumber(42)));
        System.out.println(s.isIn(new MyNumber(42)));



//        h.delete(new MyNumber(37));
//        System.out.println(h);




    }
}
