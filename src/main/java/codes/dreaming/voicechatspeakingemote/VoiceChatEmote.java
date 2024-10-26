package codes.dreaming.voicechatspeakingemote;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.entity.EntityRenderers;

public class VoiceChatEmote implements ClientModInitializer {
    public static codes.dreaming.voicechatspeakingemote.VoicechatEmoteConfig CONFIG = codes.dreaming.voicechatspeakingemote.VoicechatEmoteConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
    }
}
