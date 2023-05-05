package jaskarth.hibiscus.mixin;

import jaskarth.hibiscus.api.structure.StrongholdHooks;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StrongholdPieces.class)
public class MixinStrongholdPieces {
    @Inject(method = "findAndCreatePieceFactory", at = @At("TAIL"), cancellable = true)
    private static void injectHibiscusPieces(Class<? extends StrongholdPieces.StrongholdPiece> class_, StructurePieceAccessor structurePieceAccessor, RandomSource randomSource, int i, int j, int k, @Nullable Direction direction, int l, CallbackInfoReturnable<StrongholdPieces.StrongholdPiece> cir) {
        if (cir.getReturnValue() == null) {
            StrongholdHooks.PieceGen gen = StrongholdHooks.PIECE_GENERATORS.get(class_);

            if (gen != null) {
                cir.setReturnValue(gen.createPiece(structurePieceAccessor, randomSource, i, j, k, direction, l));
            }
        }
    }
}
