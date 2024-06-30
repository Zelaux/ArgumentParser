package argumentparser.struct;

public class Bits {
    private long[] longs;
    private int size = 0;

    public Bits(int capacity) {
        int longAmount = capacity / Long.SIZE;
        if (longAmount * Long.SIZE < capacity) longAmount++;
        longs = new long[longAmount];
    }

    public void clear() {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = 0;
        }
        size = 0;
    }

    public void set(int i) {
        int longIdx = i / Long.SIZE;
        int inLongIdx = i % Long.SIZE;
        longs[longIdx] |= 1L << inLongIdx;
        size = Math.max(i + 1, size);
    }

    public int length() {
        return size;
    }

    public boolean get(int i) {
        int longIdx = i / Long.SIZE;
        int inLongIdx = i % Long.SIZE;
        return ((longs[longIdx] >> inLongIdx) & 1) == 1;
    }
}
