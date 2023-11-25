package com.jaskarth.hibiscus.api.block.pattern;

import com.google.common.cache.LoadingCache;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;

import java.util.Map;

public class BlocksAndPatternMatch extends BlockPattern.BlockPatternMatch {
    private final Map<BlockPos, BlockState> states;

    public BlocksAndPatternMatch(Map<BlockPos, BlockState> states, BlockPos blockPos, Direction direction, Direction direction2, LoadingCache<BlockPos, BlockInWorld> loadingCache, int i, int j, int k) {
        super(blockPos, direction, direction2, loadingCache, i, j, k);
        this.states = states;
    }

    public BlockPos localToGlobal(int x, int y, int z) {
        return translateAndRotate(this.getFrontTopLeft(), this.getForwards(), this.getUp(), x, y, z);
    }

    public Map<BlockPos, BlockState> getStates() {
        return states;
    }

    protected static BlockPos translateAndRotate(BlockPos blockPos, Direction direction, Direction direction2, int i, int j, int k) {
        if (direction != direction2 && direction != direction2.getOpposite()) {
            Vec3i vec3i = new Vec3i(direction.getStepX(), direction.getStepY(), direction.getStepZ());
            Vec3i vec3i2 = new Vec3i(direction2.getStepX(), direction2.getStepY(), direction2.getStepZ());
            Vec3i vec3i3 = vec3i.cross(vec3i2);
            return blockPos.offset(
                    vec3i2.getX() * -j + vec3i3.getX() * i + vec3i.getX() * k,
                    vec3i2.getY() * -j + vec3i3.getY() * i + vec3i.getY() * k,
                    vec3i2.getZ() * -j + vec3i3.getZ() * i + vec3i.getZ() * k
            );
        } else {
            throw new IllegalArgumentException("Invalid forwards & up combination");
        }
    }
}
