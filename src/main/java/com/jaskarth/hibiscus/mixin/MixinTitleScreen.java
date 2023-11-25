package com.jaskarth.hibiscus.mixin;

import com.jaskarth.hibiscus.internal.EntrypointManager;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    @Inject(method = "init", at = @At("TAIL"))
    private void postClientInit(CallbackInfo ci) {
        EntrypointManager.invoke();
    }

}
