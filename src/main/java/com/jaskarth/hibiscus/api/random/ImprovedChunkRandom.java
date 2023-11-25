package com.jaskarth.hibiscus.api.random;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

import java.util.Random;

/**
 * Improved version of Minecraft's ChunkRandom to handle seeds and entropy better.
 */
public class ImprovedChunkRandom extends Random implements RandomSource {
    public ImprovedChunkRandom(long seed) {
        super(seed);
    }

    public long setPopulationSeed(long worldSeed, int blockX, int blockZ) {
        return setPopulationSeedChunk(worldSeed, blockX >> 4, blockZ >> 4);
    }

    public long setPopulationSeedChunk(long worldSeed, int blockX, int blockZ) {
        this.setSeed(worldSeed);
        long a = this.nextLong() | 1L;
        long b = this.nextLong() | 1L;
        long c = this.nextLong() | 1L;
        long result = (a * blockX * blockX * blockX + b * blockZ * blockZ + c) ^ worldSeed;
        this.setSeed(result);
        return result;
    }

    public long setTerrainSeed(int chunkX, int chunkZ) {
        long l = (long) chunkX * 341873128712L + (long) chunkZ * 132897987541L;
        this.setSeed(l);
        return l;
    }

    // provides more entropy than normal but is not strictly required
    public long setPopulationSeed(long worldSeed, int blockX, int blockZ, double scale) {
        blockX = blockX >> 4;
        blockZ = blockZ >> 4;
        this.setSeed(worldSeed);
        long a = this.nextLong() | 1L;
        long b = this.nextLong() | 1L;
        long c = this.nextLong() | 1L;
        long result = (a * blockX * blockX * blockX + b * blockZ * blockZ + (c ^ Double.doubleToLongBits(scale))) ^ worldSeed;
        this.setSeed(result);
        return result;
    }

    public void setDecoratorSeed(long populationSeed, int index, int step) {
        this.setSeed(populationSeed);
        long a = this.nextLong() | 1L;
        long b = this.nextLong() | 1L;
        long c = this.nextLong() | 1L;
        long d = this.nextLong() | 1L;

        long result = (a * index * index * index + b * c * a + (step + d) * c) ^ populationSeed;
        this.setSeed(result);
//        return result;
    }

    public void setCarverSeed(long worldSeed, int chunkX, int chunkZ) {
        this.setSeed(worldSeed);
        long l = this.nextLong() | 1L;
        long m = this.nextLong() | 1L;
        long n = (long) chunkX * l ^ (long) chunkZ * m ^ worldSeed;
        this.setSeed(n);
//        return n;
    }

    public void setLayerSeed(long worldSeed, int x, int z, int index) {
        this.setSeed(worldSeed);
        long a = this.nextLong();
        long b = this.nextLong();
        long c = this.nextLong();
        setSeed((a * x * x * x + b * z * z + (index + 1) * c) ^ worldSeed);
    }

    @Deprecated
    public int nextInt(int i, int j) {
        if (i >= j) {
            throw new IllegalArgumentException("bound - origin is non positive");
        } else {
            return i + this.nextInt(j - i);
        }
    }

    @Override
    public RandomSource fork() {
        return new ImprovedChunkRandom(this.nextLong());
    }

    @Override
    public PositionalRandomFactory forkPositional() {
        return new LegacyPositionalRandomFactory(this.nextLong());
    }

    public static class LegacyPositionalRandomFactory implements PositionalRandomFactory {
        private final long seed;

        public LegacyPositionalRandomFactory(long l) {
            this.seed = l;
        }

        @Override
        public RandomSource at(int i, int j, int k) {
            long l = Mth.getSeed(i, j, k);
            long m = l ^ this.seed;
            return new LegacyRandomSource(m);
        }

        @Override
        public RandomSource fromHashOf(String string) {
            int i = string.hashCode();
            return new LegacyRandomSource((long)i ^ this.seed);
        }

        @VisibleForTesting
        @Override
        public void parityConfigString(StringBuilder stringBuilder) {
            stringBuilder.append("LegacyPositionalRandomFactory{").append(this.seed).append("}");
        }
    }
}
