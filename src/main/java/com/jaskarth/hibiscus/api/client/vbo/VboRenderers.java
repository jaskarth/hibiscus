package com.jaskarth.hibiscus.api.client.vbo;

import com.jaskarth.hibiscus.internal.VboManager;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.datafixers.util.Pair;

import java.util.function.Consumer;

public class VboRenderers {

    public static void register(Vbo vbo, Consumer<BufferBuilder> builder) {
       VboManager.VBO_GENS.add(Pair.of(vbo, builder));
    }
}
