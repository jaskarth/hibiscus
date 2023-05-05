package jaskarth.hibiscus.api.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class HibiscusBlockSettings extends FabricBlockSettings {

    private BlockRenderType renderType = BlockRenderType.OPAQUE;
    private TagKey<Block> toolTag;
    private boolean dropsSelf;
    private Block nonSilkDrop;

    /////

    protected HibiscusBlockSettings(Material material, MaterialColor color) {
        super(material, color);
    }

    protected HibiscusBlockSettings(BlockBehaviour.Properties settings) {
        super(settings);
        if (settings instanceof HibiscusBlockSettings bs) {
            renderType = bs.renderType;
            toolTag = bs.toolTag;
            dropsSelf = bs.dropsSelf;
            nonSilkDrop = bs.nonSilkDrop;
        }
    }

    public static HibiscusBlockSettings of(Material material) {
        return of(material, material.getColor());
    }

    public static HibiscusBlockSettings of(Material material, MaterialColor color) {
        return new HibiscusBlockSettings(material, color);
    }

    public static HibiscusBlockSettings of(Material material, DyeColor color) {
        return new HibiscusBlockSettings(material, color.getMaterialColor());
    }

    public static HibiscusBlockSettings copyOf(BlockBehaviour block) {
        return new HibiscusBlockSettings(((AbstractBlockAccessor) block).getSettings());
    }

    public static HibiscusBlockSettings copyOf(BlockBehaviour.Properties settings) {
        return new HibiscusBlockSettings(settings);
    }

    public BlockRenderType getRenderType() {
        return renderType;
    }

    public TagKey<Block> getToolTag() {
        return toolTag;
    }

    public boolean getDropsSelf() {
        return dropsSelf;
    }

    public Block getNonSilkDrop() {
        return nonSilkDrop;
    }

    public HibiscusBlockSettings renderType(BlockRenderType renderType) {
        this.renderType = renderType;
        return this;
    }

    public HibiscusBlockSettings useTool(TagKey<Block> toolTag) {
        this.toolTag = toolTag;
        return this;
    }

    public HibiscusBlockSettings requireTool(TagKey<Block> toolTag) {
        this.toolTag = toolTag;
        requiresCorrectToolForDrops();
        return this;
    }

    public HibiscusBlockSettings dropsSelf() {
        dropsSelf = true;
        return this;
    }

    public HibiscusBlockSettings dropsSelfWithSilkTouch(Block nonSilkDrop) {
        this.nonSilkDrop = nonSilkDrop;
        return this;
    }

    // overrides the previous two options for copied settings (for slabs)
    public HibiscusBlockSettings noAutoDrop() {
        dropsSelf = false;
        nonSilkDrop = null;
        return this;
    }
}
