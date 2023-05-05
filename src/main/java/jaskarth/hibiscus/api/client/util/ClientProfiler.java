package jaskarth.hibiscus.api.client.util;

import net.minecraft.client.Minecraft;

public final class ClientProfiler {
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void push(String name) {
        minecraft.getProfiler().push(name);
    }

    public static void pop() {
        minecraft.getProfiler().pop();
    }
}
