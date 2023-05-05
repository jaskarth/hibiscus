package jaskarth.hibiscus.api.random;

import java.util.List;

public class HibiscusRandom extends ImprovedChunkRandom {
    public HibiscusRandom(long seed) {
        super(seed);
    }

    public double nextTriangle(double bound) {
        return this.nextDouble(bound) - this.nextDouble(bound);
    }

    public int nextTriangle(int bound) {
        return this.nextInt(bound) - this.nextInt(bound);
    }

    public <T> T choose(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }

        return list.get(this.nextInt(list.size()));
    }

    public <T> T choose(T... entries) {
        if (entries.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        return entries[this.nextInt(entries.length)];
    }
}
