package linear;


import java.util.ArrayList;

public abstract class OpenHashingStateful <T extends Comparable<? super T>> {
    private ArrayList<T> hashTable;
    public ArrayList<Boolean> isFree;
    private int hashTableSize;

    // #####     #####     #####     #####     #####     #####     #####     #####
    //      #####     #####     #####     #####     #####     #####     #####
    // #####     #####     #####     #####     #####     #####     #####     #####

    abstract int nextHashingPos(int retry, T value);

    // #####     #####     #####     #####     #####     #####     #####     #####
    //      #####     #####     #####     #####     #####     #####     #####
    // #####     #####     #####     #####     #####     #####     #####     #####

    OpenHashingStateful()
    {
        this(997);
    }

    OpenHashingStateful(int aSize)
    {
        hashTableSize = aSize;
        hashTable = new ArrayList<T>();
        isFree = new ArrayList<Boolean>();

        for (int i=0; i<hashTableSize; i++)
        {
            hashTable.add(null);
            isFree.add(true);
        }
    }

    public String toString()
    {
        String res = "";

        for (int i=0; i<hashTableSize; i++)
        {
            res += "[" + i + "]";
            res += "<";
            T value = hashTable.get(i);
            if (value != null && !isFree.get(i)) res += value;
            res += ">\n";
        }
        return res;
    }

    public Boolean add(T value)
    {
        // Hash-Wert berechnen
        int hash = value.hashCode();
        int baseHashValue = Math.abs(hash) % hashTableSize;     // Position im Array
        int hashValue = baseHashValue;

        // Add Entry
        int retry = 1;
        while(isFree.get(hashValue) == false && (retry < hashTableSize))
        {
            hashValue = (baseHashValue + nextHashingPos(retry,value)) % hashTableSize;
            retry += 1;
        }

        if (isFree.get(hashValue))
        {
            hashTable.set(hashValue,value);
            isFree.set(hashValue,false);
            return true;
        }
        return false;
    }


    public Boolean isIn(T value)
    {
        // Hash-Wert berechnen
        int hash = value.hashCode();
        int baseHashValue = Math.abs(hash) % hashTableSize;     // Position im Array
        int hashValue = baseHashValue;

        // Find Entry
        int retry = 1;
        while((hashTable.get(hashValue)) != null && (retry < hashTableSize))
        {
            // Element gefunden
            T v = hashTable.get(hashValue);
            if (v.compareTo(value) == 0 && !isFree.get(hashValue)) return true;

            //Nächstes ausprobieren
            hashValue = (baseHashValue + nextHashingPos(retry,value)) % hashTableSize;
            retry += 1;
        }

        return (hashTable.get(hashValue) != null);
    }
    public Boolean delete(T value)
    {
        int hash = value.hashCode();
        int baseHashValue = Math.abs(hash) % hashTableSize;
        int hashValue = baseHashValue;

        int retry = 1;
        while(hashTable.get(hashValue) != null && retry < hashTableSize)
        {
            if (hashTable.get(hashValue).compareTo(value) == 0)
            {
                isFree.set(hashValue,true);
                return true;
            }
            hashValue = (baseHashValue + nextHashingPos(retry,value)) % hashTableSize;
            retry++;
        }
        return false;
    }

}