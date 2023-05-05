package jaskarth.hibiscus.mixin;

import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StrongholdPieces.class)
public interface StrongholdPiecesAccessor {
    @Accessor
    static StrongholdPieces.PieceWeight[] getSTRONGHOLD_PIECE_WEIGHTS() {
        throw new UnsupportedOperationException();
    }

    @Mutable
    @Accessor
    static void setSTRONGHOLD_PIECE_WEIGHTS(StrongholdPieces.PieceWeight[] STRONGHOLD_PIECE_WEIGHTS) {
        throw new UnsupportedOperationException();
    }
}
