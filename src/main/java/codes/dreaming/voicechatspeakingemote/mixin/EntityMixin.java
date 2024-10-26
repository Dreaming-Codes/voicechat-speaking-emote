package codes.dreaming.voicechatspeakingemote.mixin;

import codes.dreaming.voicechatspeakingemote.IPlayerEntitySpeakingEmoteState;
import codes.dreaming.voicechatspeakingemote.VoiceChatEmote;
import de.maxhenkel.voicechat.voice.client.ClientManager;
import de.maxhenkel.voicechat.voice.client.ClientVoicechat;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class EntityMixin {
    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"))
    private void shouldRender(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
        if (MinecraftClient.getInstance().player == abstractClientPlayerEntity) {
            return;
        }

        ClientVoicechat client = ClientManager.getClient();

        if (client == null) {
            return;
        }

        if (client.getTalkCache().isTalking(abstractClientPlayerEntity) || client.getTalkCache().isWhispering(abstractClientPlayerEntity)) {
            var playerState = ((IPlayerEntitySpeakingEmoteState) abstractClientPlayerEntity);

            float currentPitch = abstractClientPlayerEntity.getPitch();
            if (currentPitch >= VoiceChatEmote.CONFIG.pitchUpperLimit()) {
                playerState.setNoddingDelta(-VoiceChatEmote.CONFIG.pitchSpeed());
            } else if (currentPitch <= VoiceChatEmote.CONFIG.pitchLowerLimit()) {
                playerState.setNoddingDelta(VoiceChatEmote.CONFIG.pitchSpeed());
            }
            abstractClientPlayerEntity.setPitch(currentPitch + playerState.getNoddingDelta());
        }

    }

}
