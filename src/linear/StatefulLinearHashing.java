package linear;

public class StatefulLinearHashing<T extends Comparable<? super T>> extends OpenHashingStateful<T> {

    StatefulLinearHashing(int length)
    {
        super(length);
    }

    int nextHashingPos(int retry, T value)
    {
        return retry;
    }
}