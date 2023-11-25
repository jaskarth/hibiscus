package com.jaskarth.hibiscus.api.block;

import com.jaskarth.hibiscus.mixin.BlockBehaviourAccessor;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class BlockRegistrar {
    private static final List<BlockRegistrar> ALIVE = new ArrayList<>();
    // ins
    private final String modid;
    private final CreativeModeTab tab;

    // outs
    private final List<Block> blocks = new ArrayList<>();
    private final Map<Block, BlockRenderType> renderTypes = new HashMap<>();

    public BlockRegistrar(String modid, CreativeModeTab tab) {
        this.modid = modid;
        this.tab = tab;
        ALIVE.add(this);
    }

    // Also generates a blockitem
    public void register(String id, Block block) {
        blocks.add(block);
        putRenderType(block);
        Registry.register(Registry.BLOCK, new ResourceLocation(modid, id), block);
        Registry.register(Registry.ITEM, new ResourceLocation(modid, id), new BlockItem(block, new Item.Properties().tab(this.tab)));
    }

    public void register(String id, Block block, BiFunction<Block, Item.Properties, Item> ctor, Item.Properties properties) {
        blocks.add(block);
        putRenderType(block);
        Registry.register(Registry.BLOCK, new ResourceLocation(modid, id), block);
        Registry.register(Registry.ITEM, new ResourceLocation(modid, id), ctor.apply(block, properties));
    }

    private void putRenderType(Block block) {
        if (!(((BlockBehaviourAccessor) block).getProperties() instanceof HibiscusBlockSettings bs)) {
            throw new IllegalArgumentException("Your block needs to have a hibiscus block settings!");
        }

        renderTypes.put(block, bs.getRenderType());
    }

    public void registerWithoutItem(String id, Block block) {
        blocks.add(block);
        putRenderType(block);
        Registry.register(Registry.BLOCK, new ResourceLocation(modid, id), block);
    }

    public static Map<Block, BlockRenderType> renderTypes() {
        return ALIVE.stream()
                .flatMap(r -> r.renderTypes.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
