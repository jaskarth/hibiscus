package com.jaskarth.hibiscus.api.structure;

import com.jaskarth.hibiscus.mixin.StrongholdPiecesAccessor;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrongholdHooks {
    private static final List<StrongholdPieces.PieceWeight> WEIGHTS = new ArrayList<>();
    public static final Map<Class<? extends StrongholdPieces.StrongholdPiece>, PieceGen> PIECE_GENERATORS = new HashMap<>();

    public static void registerPiece(StrongholdPieces.PieceWeight weight, PieceGen gen) {
        WEIGHTS.add(weight);
        PIECE_GENERATORS.put(weight.pieceClass, gen);

        apply();
    }

    public static void apply() {
        StrongholdPieces.PieceWeight[] weights = StrongholdPiecesAccessor.getSTRONGHOLD_PIECE_WEIGHTS();

        StrongholdPieces.PieceWeight[] newWeights = new StrongholdPieces.PieceWeight[weights.length + WEIGHTS.size()];
        System.arraycopy(weights, 0, newWeights, 0, weights.length);
        for (int i = 0; i < WEIGHTS.size(); i++) {
            newWeights[weights.length + i] = WEIGHTS.get(i);
        }

        StrongholdPiecesAccessor.setSTRONGHOLD_PIECE_WEIGHTS(newWeights);
    }

    public interface PieceGen {
        StrongholdPieces.StrongholdPiece createPiece(StructurePieceAccessor structurePieceAccessor, RandomSource randomSource, int i, int j, int k, Direction direction, int l);
    }
}
