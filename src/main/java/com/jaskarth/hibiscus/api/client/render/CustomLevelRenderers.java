package com.jaskarth.hibiscus.api.client.render;

import com.jaskarth.hibiscus.internal.LevelRenderManager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;

public class CustomLevelRenderers {

    public static void registerRenderer(HibiscusLevelRenderer renderer) {
        LevelRenderManager.RENDERERS.add(renderer);
    }

    public interface HibiscusLevelRenderer {
        void initialize(Minecraft minecraft, EntityRenderDispatcher er, BlockEntityRenderDispatcher ber, RenderBuffers buf);

        void renderSky(PoseStack poseStack, Matrix4f matrix4f, float f, Camera camera, boolean bl, Runnable runnable);

        boolean shouldRender();
    }
}
