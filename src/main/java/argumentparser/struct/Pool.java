package argumentparser.struct;

import java.util.ArrayList;
import java.util.function.Supplier;

public class Pool<T> {
    public final Supplier<T> provider;
    public int size = 0;
    private ArrayList<T> list = new ArrayList<>();

    public Pool(int initial, Supplier<T> provider) {
        this.provider = provider;
        for (int i = 0; i < initial; i++) {
            get();
        }
        size = 0;

    }

    public T get() {
        if (list.size() < size) {
            T t = list.get(size);
            size++;
            return t;
        }
        T e = provider.get();
        list.add(e);
        size++;
        return e;
    }


}
