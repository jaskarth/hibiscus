package com.jaskarth.hibiscus.api.client.shader;

import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.ShaderInstance;

import java.util.function.Supplier;

public final class ShaderContainer {
    private ShaderInstance shader;

    public void setShader(ShaderInstance shader) {
        this.shader = shader;
    }

    public ShaderInstance getShader() {
        checkNull();

        return shader;
    }

    public RenderStateShard.ShaderStateShard asState() {
        checkNull();

        return new RenderStateShard.ShaderStateShard(() -> this.shader);
    }

    public Supplier<ShaderInstance> asSupplier() {
        checkNull();

        return () -> this.shader;
    }

    private void checkNull() {
        if (shader == null) {
            throw new IllegalStateException("Shader is null!");
        }
    }
}
