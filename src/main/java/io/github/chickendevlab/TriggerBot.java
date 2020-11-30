package io.github.chickendevlab;

import io.github.chickendevlab.listeners.DestroyListener;
import io.github.chickendevlab.listeners.TriggerListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import javax.security.auth.login.LoginException;
import java.util.Scanner;


public class TriggerBot {
    private JDA instance;

    public TriggerBot(String token) throws LoginException, InterruptedException {
        this.instance = JDABuilder.createDefault(token).setStatus(OnlineStatus.INVISIBLE)/*.setActivity(Activity.playing("Muss sich mit einem Haufen nervender kleiner Kinder abgeben"))*/.build();
        this.instance.awaitReady();
        new TriggerListener(instance);
        new DestroyListener(instance);
        new RoleAddListener(instance);
        new LogCleaner(instance);
        CmdLineParser.init(instance);
        Guild g = instance.getGuildById(761551875589406730L);
        System.out.println(g.getTextChannels());
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        do {
            String s = scanner.next();
            if (s.equals("q")) b = false;
            CmdLineParser.parse(s, instance);

        } while (b);
    }

    public static void main(String[] args) throws LoginException, InterruptedException {
        String token = System.getenv("BOT_TOKEN");
        new TriggerBot(token);
    }
}
