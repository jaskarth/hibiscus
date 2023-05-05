package jaskarth.hibiscus.client;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.datafixers.util.Pair;
import jaskarth.hibiscus.api.block.BlockRegistrar;
import jaskarth.hibiscus.api.block.BlockRenderType;
import jaskarth.hibiscus.internal.VboManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import jaskarth.hibiscus.api.client.vbo.Vbo;
import jaskarth.hibiscus.api.entry.LateClientEntrypoints;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class HibiscusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LateClientEntrypoints.registerLateEntrypoint(() -> {
            for (Pair<Vbo, Consumer<BufferBuilder>> gen : VboManager.VBO_GENS) {
                VertexBuffer vbo = new VertexBuffer();
                BufferBuilder buffer = Tesselator.getInstance().getBuilder();

                gen.getSecond().accept(buffer);

                vbo.bind();
                vbo.upload(buffer.end());
                gen.getFirst().setBuffer(vbo);
                VertexBuffer.unbind();
            }
        });

        LateClientEntrypoints.registerLateEntrypoint(() -> {
            for (Map.Entry<Block, BlockRenderType> e : BlockRegistrar.renderTypes().entrySet()) {
                BlockRenderType render = e.getValue();

                if (render != BlockRenderType.OPAQUE) {
                    BlockRenderLayerMap.INSTANCE.putBlock(e.getKey(), switch (render) {
                        default -> throw new IllegalStateException("Impossible");
                        case TRANSLUCENT -> RenderType.translucent();
                        case CUTOUT -> RenderType.cutout();
                    });
                }
            }
        });
    }
}
