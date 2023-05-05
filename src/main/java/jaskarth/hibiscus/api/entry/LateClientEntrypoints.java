package jaskarth.hibiscus.api.entry;

import jaskarth.hibiscus.internal.EntrypointManager;

import java.util.ArrayList;
import java.util.List;

public final class LateClientEntrypoints {
    public static void registerLateEntrypoint(Runnable late) {
        EntrypointManager.ENTRIES.add(late);
    }
}
