package io.github.chickendevlab.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class TriggerListener extends ListenerAdapter {
    private JDA instance;
    public TriggerListener(JDA instance){
        this.instance = instance;
        instance.addEventListener(this);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentDisplay();
        if(msg.startsWith("-ghostping"))
            if(!event.getMessage().getMentionedMembers().isEmpty()){
                for(Category ca : event.getGuild().getCategories()){
                    for(TextChannel ch : ca.getTextChannels()){
                        for(Member u : event.getMessage().getMentionedMembers())
                        ch.sendMessage(new MessageBuilder().setContent(u.getAsMention()).build()).submit().thenAccept(message -> message.delete().queue());
                    }
                }
                event.getMessage().delete().queue();
            }
    }
}
