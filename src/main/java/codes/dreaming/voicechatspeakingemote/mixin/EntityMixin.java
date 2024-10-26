package codes.dreaming.voicechatspeakingemote.mixin;

import codes.dreaming.voicechatspeakingemote.IPlayerEntitySpeakingEmoteState;
import codes.dreaming.voicechatspeakingemote.VoiceChatEmote;
import de.maxhenkel.voicechat.voice.client.ClientManager;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntityRenderer.class)
public class EntityMixin<T extends LivingEntity, M extends EntityModel<T>> {

    @Redirect(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getPitch()F"))
    private float modifyPitch(LivingEntity livingEntity) {
        if (!(livingEntity instanceof AbstractClientPlayerEntity abstractClientPlayerEntity)) {
            return livingEntity.getPitch();
        }

        ClientVoicechat client = ClientManager.getClient();

        if (client == null || !(client.getTalkCache().isTalking(abstractClientPlayerEntity) || client.getTalkCache().isWhispering(abstractClientPlayerEntity))) {
            return livingEntity.getPitch();
        }

        var playerState = ((IPlayerEntitySpeakingEmoteState) abstractClientPlayerEntity);
        float currentPitch = abstractClientPlayerEntity.getPitch();
        if (currentPitch >= VoiceChatEmote.CONFIG.pitchUpperLimit()) {
            playerState.setNoddingDelta(-VoiceChatEmote.CONFIG.pitchSpeed());
        } else if (currentPitch <= VoiceChatEmote.CONFIG.pitchLowerLimit()) {
            playerState.setNoddingDelta(VoiceChatEmote.CONFIG.pitchSpeed());
        }

        return currentPitch + playerState.getNoddingDelta();
    }
}
