package pro.melodara;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class BotListener extends ListenerAdapter {
    private final Logger LOGGER = LoggerFactory.getLogger("melodara/events");
    private final Melodara melodara;

    public BotListener(Melodara melodara) {
        this.melodara = melodara;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        melodara.getShardManager().setActivity(Activity.listening("Linkin Park"));
        melodara.getShardManager().setStatus(OnlineStatus.ONLINE);

        LOGGER.info(
                "shard {} | Startup time: {} secs; User#tag: {}",
                event.getJDA().getShardInfo().getShardId(),
                Instant.now().getEpochSecond()  - Melodara.STARTUP_TIME.getEpochSecond(),
                event.getJDA().getSelfUser().getAsTag()
        );

        melodara.getCommandManager().updateCommandsGlobally(event.getJDA());
    }
}
