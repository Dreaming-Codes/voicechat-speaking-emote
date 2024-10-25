package codes.dreaming.voicechatspeakingemote.plugin;

import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Pair;
import io.github.kosmx.emotes.api.events.server.ServerEmoteAPI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.List;
import java.util.UUID;

public class VoicechatEmotePlugin implements VoicechatPlugin {
    public static final codes.dreaming.voicechatspeakingemote.plugin.VoicechatEmoteConfig CONFIG = codes.dreaming.voicechatspeakingemote.plugin.VoicechatEmoteConfig.createAndLoad();
    static String PLUGIN_ID = "speaking_emote";

    @Override
    public String getPluginId() {
        return PLUGIN_ID;
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(MicrophonePacketEvent.class, this::onMicrophone);
    }

    /**
     * This method is called whenever a player sends audio to the server via the voice chat.
     *
     * @param event the microphone packet event
     */
    private void onMicrophone(MicrophonePacketEvent event) {
        // The connection might be null if the event is caused by other means
        if (event.getSenderConnection() == null) {
            return;
        }
        // Cast the generic player object of the voice chat API to an actual fabric player
        // This object should always be a fabric player object on fabric-based servers
        if (!(event.getSenderConnection().getPlayer().getPlayer() instanceof ServerPlayerEntity player)) {
            return;
        }

//        KeyframeAnimation emote = ServerEmoteAPI.getEmote(UUID.fromString(CONFIG.emoteId()));
//
//        Pair<KeyframeAnimation, Integer> playerEmote = ServerEmoteAPI.getPlayedEmote(player.getUuid());
//
//        if (playerEmote != null && playerEmote.getRight() < CONFIG.restartEmoteAfter()) {
//            return;
//        }


//        player.updateTrackedHeadRotation();
//        System.out.println("Player: " + player.getUuid() + " is speaking , headYaw: " + player.headYaw);
//
//        ServerEmoteAPI.playEmote(player.getUuid(), emote, true);
    }

}
