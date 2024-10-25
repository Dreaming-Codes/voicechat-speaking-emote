package codes.dreaming.voicechatspeakingemote.mixin;

import codes.dreaming.voicechatspeakingemote.plugin.VoicechatEmotePlugin;
import io.github.kosmx.emotes.main.emotePlay.EmotePlayer;
import io.github.kosmx.emotes.main.mixinFunctions.IPlayerEntity;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = AbstractClientPlayerEntity.class, priority = 1500)
public abstract class ForceEmoteMixin implements IPlayerEntity {
    @Override
    public void initEmotePerspective(EmotePlayer emotePlayer) {
        if (emotePlayer.getData().getUuid().toString().equals(VoicechatEmotePlugin.CONFIG.emoteId())) {
            return;
        }
        IPlayerEntity.super.initEmotePerspective(emotePlayer);
    }
}
