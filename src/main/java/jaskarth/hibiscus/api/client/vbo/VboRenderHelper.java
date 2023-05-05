package jaskarth.hibiscus.api.client.vbo;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import jaskarth.hibiscus.api.client.shader.ShaderContainer;
import net.minecraft.client.renderer.ShaderInstance;

public final class VboRenderHelper {
    public static void render(Vbo vbo, PoseStack matrices, ShaderContainer shader) {
        render(vbo.getBuffer(), matrices, shader.getShader());
    }

    public static void render(VertexBuffer buffer, PoseStack matrices, ShaderInstance shader) {
        buffer.bind();

        buffer.drawWithShader(matrices.last().pose(), RenderSystem.getProjectionMatrix(), shader);

        VertexBuffer.unbind();
    }
}
