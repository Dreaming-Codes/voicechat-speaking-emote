package codes.dreaming.voicechatspeakingemote.mixin;

import codes.dreaming.voicechatspeakingemote.IPlayerEntitySpeakingEmoteState;
import codes.dreaming.voicechatspeakingemote.VoiceChatEmote;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(AbstractClientPlayerEntity.class)
public class PlayerEntityStateMixin implements IPlayerEntitySpeakingEmoteState {
    @Unique
    float noddingDelta = VoiceChatEmote.CONFIG.pitchSpeed();

    @Override
    public float getNoddingDelta() {
        return noddingDelta;
    }

    @Override
    public void setNoddingDelta(float delta) {
        noddingDelta = delta;
    }
}
