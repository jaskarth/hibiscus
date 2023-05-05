package jaskarth.hibiscus.api.client.vbo;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.datafixers.util.Pair;
import jaskarth.hibiscus.internal.VboManager;

import java.util.function.Consumer;

public class VboRenderers {

    public static void register(Vbo vbo, Consumer<BufferBuilder> builder) {
       VboManager.VBO_GENS.add(Pair.of(vbo, builder));
    }
}
