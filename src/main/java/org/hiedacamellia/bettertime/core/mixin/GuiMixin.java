package org.hiedacamellia.bettertime.core.mixin;


import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.hiedacamellia.bettertime.Bettertime.*;
import static org.hiedacamellia.bettertime.core.config.Config.*;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "render",at = @At("TAIL"))
    private void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci){

        if (!showDateHud.get()) return;
        Minecraft minecraft = Minecraft.getInstance();
        Level level = minecraft.level;
        Font font = minecraft.font;

        if (level == null) return;
        MutableComponent time = Component.translatable("hud.bettertime.time", getDays(level), getHours(level) + hourAdd.get(), getMinutes(level) + minAdd.get());
        guiGraphics.drawString(
                font,
                time,
                dateX.get(),
                dateY.get(),
                dateColor.get()
        );
    }
}
