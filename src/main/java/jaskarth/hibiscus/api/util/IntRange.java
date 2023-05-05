package jaskarth.hibiscus.api.util;

import java.util.Random;

// Range of ints. Min and max are both inclusive.
public record IntRange(int min, int max) {
    public static IntRange of(int num) {
        return new IntRange(num, num);
    }

    public static IntRange of(int min, int max) {
        return new IntRange(min, max);
    }

    public int size() {
        return this.max - this.min + 1;
    }

    public int random(Random random) {
        if (size() == 0) {
            return this.min;
        }

        return random.nextInt(this.size()) + this.min;
    }
}
