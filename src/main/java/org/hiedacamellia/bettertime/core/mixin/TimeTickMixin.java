package org.hiedacamellia.bettertime.core.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.storage.ServerLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import static org.hiedacamellia.bettertime.core.config.Config.showDayTitle;

@Mixin(ServerLevel.class)
public abstract class TimeTickMixin {
    @Shadow @Final private ServerLevelData serverLevelData;

    @Shadow @Final List<ServerPlayer> players;

    @Shadow public abstract ServerLevel getLevel();

    @Inject(method = "tickTime", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setDayTime(J)V"))
    protected void timeKeeping(CallbackInfo ci){
        if (!showDayTitle.get()) return;
        long worldTime = this.serverLevelData.getDayTime();
        if (worldTime % 24000L == 0) {
            for (ServerPlayer serverPlayer : this.players){
                serverPlayer.connection.send(new ClientboundSetTitlesAnimationPacket(10, 40, 10));
                serverPlayer.connection.send(new ClientboundSetTitleTextPacket(Component.translatable("title.bettertime.day",worldTime / 24000L)));
                this.getLevel().playSound(null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.PLAYER_LEVELUP, serverPlayer.getSoundSource(), 0.75F, 1.0F);;
            }
        }
    }
}
