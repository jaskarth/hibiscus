package com.jaskarth.hibiscus.mixin.client;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import com.jaskarth.hibiscus.api.client.shader.Shaders;
import com.jaskarth.hibiscus.api.client.shader.ShaderContainer;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    // Hook to load shaders
    @Inject(method = "reloadShaders", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;shutdownShaders()V", ordinal = 0, shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD)
    private void loadHibiscusShaders(ResourceManager resourceManager, CallbackInfo ci, List list, List<Pair<ShaderInstance, Consumer<ShaderInstance>>> list2) {
        try {
            for (Pair<Pair<String, VertexFormat>, ShaderContainer> gen : Shaders.SHADER_GENS) {
                list2.add(
                        Pair.of(
                                new ShaderInstance(resourceManager, gen.getFirst().getFirst(), gen.getFirst().getSecond()),
                                gen.getSecond()::setShader
                        )
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        // todo
    }

}
