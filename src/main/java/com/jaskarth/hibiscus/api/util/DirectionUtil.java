package com.jaskarth.hibiscus.api.util;

import net.minecraft.core.Direction;

public final class DirectionUtil {
    public static final Direction[] HORIZONTALS = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    public static final int[][] DIAGONALS = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
}
