package com.discordapi.SlashCommands;

import com.discordapi.Controller.DiscordController;
import com.discordapi.Request.PlantRequestBody;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


import javax.swing.text.html.Option;
import java.awt.*;
import java.util.Collections;
import java.util.List;

import static com.discordapi.Controller.DiscordController.*;


@Service
public class SlashCommand extends ListenerAdapter {

    @Autowired
    MongoTemplate mongoTemplate;



    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (event.getName().equals("suck")) {
            event.reply("How hard baby?").queue();
        } else if (event.getName().equals("fart")) {
            event.reply("WOW THAT WAS A WET ONE. TEHE.").queue();
        } else if (event.getName().equals("insertplant")) {
            // Extract information from options
            String name = event.getOption("name") != null ? event.getOption("name").getAsString().strip() : null;
            String height = event.getOption("height") != null ? event.getOption("height").getAsString().strip() : null;
            String sunLight = event.getOption("sunlight") != null ? event.getOption("sunlight").getAsString().strip() : null;
            String water = event.getOption("water") != null ? event.getOption("water").getAsString().strip() : null;
            String originDescription = event.getOption("origin") != null ? event.getOption("origin").getAsString().strip() : null;


            PlantRequestBody plantRequestBody = new PlantRequestBody();

            plantRequestBody.getPlantInfoData().setHeight(height);
            plantRequestBody.getPlantInfoData().setName(name);
            plantRequestBody.getPlantInfoData().setSunLight(sunLight);
            plantRequestBody.getPlantInfoData().setOriginDescription(originDescription);
            plantRequestBody.getPlantInfoData().setWater(water);

            try {
                if (isDuplicate(name)) {
                    EmbedBuilder responseEmbed = new EmbedBuilder()
                            .setTitle("Response Error")
                            .setDescription("The plant name already exists in the database. ")
                            .setColor(Color.RED);
                    event.replyEmbeds(responseEmbed.build()).queue();
                } else {
                    postInfo(plantRequestBody);
                    EmbedBuilder responseEmbed = new EmbedBuilder()
                            .setTitle("PlantInfo has been inserted into Database")
                            .setDescription("To retrieve the plantInfo use '/getPlant'")
                            .setColor(Color.GREEN);
                    event.replyEmbeds(responseEmbed.build()).queue();
                }
            } catch (Exception e) {
                EmbedBuilder responseEmbed = new EmbedBuilder()
                        .setTitle("Response Error")
                        .setDescription(e.toString());
                event.replyEmbeds(responseEmbed.build()).queue();
            }

        } else if (event.getName().equals("getplant")) {
            String name = event.getOption("name") != null ? event.getOption("name").getAsString() : null;

            try {
                if (isDuplicate(name)) {
                    List<PlantRequestBody> response = getInfo(name);
                    EmbedBuilder responseEmbed = new EmbedBuilder()
                            .setTitle(response.get(0).getPlantInfoData().getName())
                            .setDescription("Height: " + response.get(0).getPlantInfoData().getHeight() + "\n"
                                    + "Sun Light: " + response.get(0).getPlantInfoData().getSunLight() + "\n"
                                    + "Water: " + response.get(0).getPlantInfoData().getWater() + "\n"
                                    + "Origin: " + response.get(0).getPlantInfoData().getOriginDescription()
                            )
                            .setColor(Color.GREEN);
                    event.replyEmbeds(responseEmbed.build()).queue();
                } else {
                    EmbedBuilder responseEmbed = new EmbedBuilder()
                            .setTitle("Response Error")
                            .setDescription("Plant is not in database. You can add it by using /postinfo")
                            .setColor(Color.RED);
                    event.replyEmbeds(responseEmbed.build()).queue();
                }


            } catch (Exception e) {
                EmbedBuilder responseEmbed = new EmbedBuilder()
                        .setTitle("Response Error")
                        .setDescription(e.toString())
                                .setColor(Color.RED);
                event.replyEmbeds(responseEmbed.build()).queue();
            }

        } else if (event.getName().equals("dont-tell-squire")) {
            // Check if the user has the necessary permissions
            if (true) {
                // Get the text channel
                MessageChannel textChannel = event.getMessageChannel();

                // Fetch all messages in the channel

                EmbedBuilder responseEmbed = new EmbedBuilder()
                        .setTitle("NUKED THE CHANNEL")
                        .setImage("https://giphy.com/gifs/explosion-oe33xf3B50fsc")
                        .setColor(Color.RED);
                event.replyEmbeds(responseEmbed.build()).queue();
            }
//            } else {
//                event.reply("You don't have the required permissions to use this command.")
//                        .setEphemeral(true).queue();
//            }
        }
    }


    static public void SlashCommandSetup(String discordToken) {
        JDA jda = JDABuilder.createLight(discordToken, Collections.emptyList())
                .addEventListeners(new SlashCommand())
                .setActivity(Activity.watching("Root Bondage"))
                .build();


        jda.updateCommands().addCommands(
                Commands.slash("suck", "How hard baby?").setGuildOnly(true),
                Commands.slash("fart", "WOW THAT WAS A WET ONE. TEHE.").setGuildOnly(true),
                Commands.slash("insertplant", "Insert Plant Info Into Database")
                        .addOption(OptionType.STRING, "name", "Plant Name", true)
                        .addOption(OptionType.STRING, "height", "Average Plant Height", true)
                        .addOption(OptionType.STRING, "sunlight", "Indirect or Direct Sunlight?", true)
                        .addOption(OptionType.STRING, "water", "How much should you water?", true)
                        .addOption(OptionType.STRING, "origin", "Where does the plant originate?", true)
                        .setGuildOnly(true),
                Commands.slash("getplant", "Retrieve Plant Details")
                        .addOption(OptionType.STRING, "name", "Plant Name", true)
                        .setGuildOnly(true),
                Commands.slash("dont-tell-squire", "nuking").setGuildOnly(true)
        ).queue();
    }
}
