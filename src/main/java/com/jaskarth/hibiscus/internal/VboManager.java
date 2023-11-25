package com.jaskarth.hibiscus.internal;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.datafixers.util.Pair;
import com.jaskarth.hibiscus.api.client.vbo.Vbo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class VboManager {
    public static List<Pair<Vbo, Consumer<BufferBuilder>>> VBO_GENS = new ArrayList<>();
}
