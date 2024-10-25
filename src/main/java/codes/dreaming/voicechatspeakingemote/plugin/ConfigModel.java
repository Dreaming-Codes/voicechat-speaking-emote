package codes.dreaming.voicechatspeakingemote.plugin;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Sync(Option.SyncMode.OVERRIDE_CLIENT)
@Config(name = "voicechat-emote", wrapperName = "VoicechatEmoteConfig")
public class ConfigModel {
    public String emoteId = "ebfb1e69-330a-4970-8bca-f5625c90681a";
    public Integer restartEmoteAfter = 12;
}
