package jaskarth.hibiscus.internal;

import java.util.ArrayList;
import java.util.List;

public class EntrypointManager {
    public static final List<Runnable> ENTRIES = new ArrayList<>();

    private static boolean initialized = false;

    public static void invoke() {
        if (initialized) {
            return;
        }

        initialized = true;

        for (Runnable runnable : ENTRIES) {
            runnable.run();
        }
    }
}
