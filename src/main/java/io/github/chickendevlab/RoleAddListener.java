package io.github.chickendevlab;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class RoleAddListener extends ListenerAdapter {

    public RoleAddListener(JDA instance) {
        instance.addEventListener(this);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(!event.getMessage().getContentDisplay().startsWith("-addrole")) return;
        LogCleaner.enable();
        if (!event.getMessage().getMentionedMembers().isEmpty() && !event.getMessage().getMentionedRoles().isEmpty()) {
            for (Role r : event.getMessage().getMentionedRoles())
                for (Member m : event.getMessage().getMentionedMembers()) {

                    event.getGuild().addRoleToMember(m, r).queue();
                }
        }
        event.getMessage().delete().queue((unused -> {

        }));
    }
}
