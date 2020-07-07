package linear;


import java.util.ArrayList;

public abstract class OpenHashing <T extends Comparable<? super T>> {
    private ArrayList<T> hashTable;
    private int hashTableSize;

    // #####     #####     #####     #####     #####     #####     #####     #####
    //      #####     #####     #####     #####     #####     #####     #####
    // #####     #####     #####     #####     #####     #####     #####     #####

    abstract int nextHashingPos(int retry, T value);

    // #####     #####     #####     #####     #####     #####     #####     #####
    //      #####     #####     #####     #####     #####     #####     #####
    // #####     #####     #####     #####     #####     #####     #####     #####

    OpenHashing()
    {
        this(997);
    }

    OpenHashing(int aSize)
    {
        hashTableSize = aSize;
        hashTable = new ArrayList<T>();

        for (int i=0; i<hashTableSize; i++)
        {
            hashTable.add(null);
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
            if (value != null) res += value;
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
        while((hashTable.get(hashValue)) != null && (retry < hashTableSize))
        {
            hashValue = (baseHashValue + nextHashingPos(retry,value)) % hashTableSize;
            retry += 1;
        }

        if (hashTable.get(hashValue) == null)
        {
            hashTable.set(hashValue,value);
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
            if (v.compareTo(value) == 0) return true;

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

        int first = -1;
        int retry = 1;
        while(hashTable.get(hashValue) != null && retry < hashTableSize)
        {
            if (hashTable.get(hashValue).compareTo(value) == 0 && first == -1)
            {
                first = hashValue;
            }
            hashValue = baseHashValue + nextHashingPos(retry,value);
            retry++;
        }
        if (first != -1) {
            hashTable.set(first, hashTable.get(hashValue-1));
            hashTable.set(hashValue-1, null);
            return true;
        }
        else {
            return false;
        }
    }
}