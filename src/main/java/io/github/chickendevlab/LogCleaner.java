package io.github.chickendevlab;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class LogCleaner extends ListenerAdapter {
    private static boolean enabled;
    private static boolean autoDisable = true;
    public static void enable(){
        enabled = true;
        autoDisable = true;
    }

    public static void disable(){
        enabled = false;
    }

    public static void enable(boolean autoDisable){
        enabled = true;
        LogCleaner.autoDisable = autoDisable;
    }
    public LogCleaner(JDA instance) {
        instance.addEventListener(this);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(enabled && event.getChannel().getIdLong() == 761574771040452628L) {
            event.getMessage().delete();
            enabled = autoDisable ? true : false;
        }
    }
}
