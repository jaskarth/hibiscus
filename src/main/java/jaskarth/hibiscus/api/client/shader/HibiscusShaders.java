package jaskarth.hibiscus.api.client.shader;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HibiscusShaders {
    private static final Set<String> NAMES = new HashSet<>();
    public static final List<Pair<Pair<String, VertexFormat>, ShaderContainer>> SHADER_GENS = new ArrayList<>();

    public static void register(String name, VertexFormat format, ShaderContainer container) {
        if (NAMES.contains(name)) {
            throw new IllegalStateException("Shader name " + name + " is already registered!");
        }

        NAMES.add(name);
        SHADER_GENS.add(Pair.of(Pair.of(name, format), container));
    }
}
