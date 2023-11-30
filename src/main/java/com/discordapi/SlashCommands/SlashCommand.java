package com.discordapi.SlashCommands;

import com.discordapi.GoogleCloud.CredentialsProvider;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Collections;


@Configuration
public class SlashCommand extends ListenerAdapter {



    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("suck")) {
            event.reply("How hard baby?").queue();
        } else if (event.getName().equals("fart")) {
            event.reply("WOW THAT WAS A WET ONE. TEHE.").queue();
        }
    }

    static public void SlashCommandSetup(String discordToken) {
        JDA jda = JDABuilder.createLight(discordToken, Collections.emptyList())
                .addEventListeners(new SlashCommand())
                .setActivity(Activity.watching("PORN"))
                .build();


        jda.updateCommands().addCommands(
                Commands.slash("suck", "How hard baby?").setGuildOnly(true),
                Commands.slash("fart", "WOW THAT WAS A WET ONE. TEHE.").setGuildOnly(true)
        ).queue();
    }
}
