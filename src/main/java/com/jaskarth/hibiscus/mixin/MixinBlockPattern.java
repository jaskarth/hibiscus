package com.jaskarth.hibiscus.mixin;

import com.google.common.cache.LoadingCache;
import com.jaskarth.hibiscus.api.block.pattern.BlocksAndPatternMatch;
import com.jaskarth.hibiscus.api.block.pattern.BlocksInBlockPatternFinder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Mixin(BlockPattern.class)
public abstract class MixinBlockPattern implements BlocksInBlockPatternFinder {
    @Shadow @Final private int width;

    @Shadow @Final private int height;

    @Shadow @Final private int depth;

    @Shadow @Final private Predicate<BlockInWorld>[][][] pattern;

    @Shadow
    protected static BlockPos translateAndRotate(BlockPos blockPos, Direction direction, Direction direction2, int i, int j, int k) {
        return null;
    }

    @Override
    public @Nullable BlocksAndPatternMatch findAndReturnResults(LevelReader level, BlockPos pos) {
        LoadingCache<BlockPos, BlockInWorld> loadingCache = BlockPattern.createLevelCache(level, false);
        int i = Math.max(Math.max(this.width, this.height), this.depth);

        for(BlockPos blockPos2 : BlockPos.betweenClosed(pos, pos.offset(i - 1, i - 1, i - 1))) {
            for(Direction direction : Direction.values()) {
                for(Direction direction2 : Direction.values()) {
                    if (direction2 != direction && direction2 != direction.getOpposite()) {
                        BlocksAndPatternMatch blockPatternMatch = this.matchAndReturnResults(blockPos2, direction, direction2, loadingCache);

                        if (blockPatternMatch != null) {
                            return blockPatternMatch;
                        }
                    }
                }
            }
        }

        return null;
    }

    @Nullable
    @Unique
    private BlocksAndPatternMatch matchAndReturnResults(BlockPos blockPos, Direction direction, Direction direction2, LoadingCache<BlockPos, BlockInWorld> loadingCache) {
        Map<BlockPos, BlockState> states = new HashMap<>();
        for(int i = 0; i < this.width; ++i) {
            for(int j = 0; j < this.height; ++j) {
                for(int k = 0; k < this.depth; ++k) {
                    BlockPos pos = translateAndRotate(blockPos, direction, direction2, i, j, k);
                    BlockInWorld res = loadingCache.getUnchecked(pos);
                    states.put(pos, res.getState());

                    if (!this.pattern[k][j][i].test(res)) {
                        return null;
                    }
                }
            }
        }

        return new BlocksAndPatternMatch(states, blockPos, direction, direction2, loadingCache, this.width, this.height, this.depth);
    }
}
