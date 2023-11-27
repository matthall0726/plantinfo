package com.discordapi.SlashCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import org.jetbrains.annotations.NotNull;

public class SlashCommand extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (event.getName().equals("suck")) {
            event.reply("How hard baby?").queue();
        } else if (event.getName().equals("fart")) {
            event.reply("WOW THAT WAS A WET ONE. TEHE.").queue();
        }
    }
}
