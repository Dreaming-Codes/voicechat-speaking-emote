package codes.dreaming.voicechatspeakingemote;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Sync;

@Sync(Option.SyncMode.OVERRIDE_CLIENT)
@Config(name = "voicechat-emote", wrapperName = "VoicechatEmoteConfig")
public class ConfigModel {
    public Float pitchSpeed = 0.5f;
    public Float pitchUpperLimit = 10.0f;
    public Float pitchLowerLimit = -10.0f;
}
