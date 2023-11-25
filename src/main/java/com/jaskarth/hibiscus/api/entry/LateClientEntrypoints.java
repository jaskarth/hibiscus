package com.jaskarth.hibiscus.api.entry;

import com.jaskarth.hibiscus.internal.EntrypointManager;

public final class LateClientEntrypoints {
    public static void registerLateEntrypoint(Runnable late) {
        EntrypointManager.ENTRIES.add(late);
    }
}
