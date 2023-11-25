package com.jaskarth.hibiscus.api.block.pattern;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import org.jetbrains.annotations.Nullable;

public interface BlocksInBlockPatternFinder {
    @Nullable
    BlocksAndPatternMatch findAndReturnResults(LevelReader level, BlockPos pos);
}
