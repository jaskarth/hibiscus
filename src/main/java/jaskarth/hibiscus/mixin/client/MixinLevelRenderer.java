package jaskarth.hibiscus.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import jaskarth.hibiscus.internal.LevelRenderManager;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import jaskarth.hibiscus.api.client.render.CustomLevelRenderers;

@Mixin(LevelRenderer.class)
public class MixinLevelRenderer {
    @Shadow @Final private RenderBuffers renderBuffers;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void hibiscusConstructor(Minecraft minecraft, EntityRenderDispatcher er, BlockEntityRenderDispatcher ber, RenderBuffers buf, CallbackInfo ci) {
        for (CustomLevelRenderers.HibiscusLevelRenderer renderer : LevelRenderManager.RENDERERS) {
            renderer.initialize(minecraft, er, ber, buf);
        }
    }

    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void renderHibiscusSky(PoseStack poseStack, Matrix4f matrix4f, float f, Camera camera, boolean bl, Runnable runnable, CallbackInfo ci) {
        for (CustomLevelRenderers.HibiscusLevelRenderer renderer : LevelRenderManager.RENDERERS) {
            if (renderer.shouldRender()) {
                renderer.renderSky(poseStack, matrix4f, f, camera, bl, runnable);
                ci.cancel();
            }
        }
    }
}
