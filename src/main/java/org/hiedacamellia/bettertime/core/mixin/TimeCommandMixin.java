package org.hiedacamellia.bettertime.core.mixin;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.TimeCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TimeCommand.class)
public abstract class TimeCommandMixin {
    @Inject(method = "setTime", at = @At(value = "HEAD"), cancellable = true)
    private static void banSet(CommandSourceStack pSource, int pTime, CallbackInfoReturnable<Integer> cir){
        pSource.sendSuccess(() -> Component.translatable("commands.bettertime.banvanilla"), true);
        cir.setReturnValue(0);
    }

    @Inject(method = "queryTime", at = @At(value = "HEAD"), cancellable = true)
    private static void banQuery(CommandSourceStack pSource, int pTime, CallbackInfoReturnable<Integer> cir){
        pSource.sendSuccess(() -> Component.translatable("commands.bettertime.banvanilla"), true);
        cir.setReturnValue(0);
    }

    @Inject(method = "addTime", at = @At(value = "HEAD"), cancellable = true)
    private static void banAdd(CommandSourceStack pSource, int pTime, CallbackInfoReturnable<Integer> cir){
        pSource.sendSuccess(() -> Component.translatable("commands.bettertime.banvanilla"), true);
        cir.setReturnValue(0);
    }
}
