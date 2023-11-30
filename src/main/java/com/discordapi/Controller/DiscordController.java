package com.discordapi.Controller;

import com.discordapi.Dataholder.PlantInfoData;
import com.discordapi.Request.PlantRequestBody;
import com.discordapi.Response.PlantResponse;
import com.discordapi.Response.PlantResponseList;
import com.discordapi.SlashCommands.SlashCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.discordapi.SlashCommands.SlashCommand.SlashCommandSetup;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DiscordController {

    @Autowired
    MongoTemplate mongoTemplate;



    PlantResponse plantResponse;
    public DiscordController(@Value("${Discord_TOKEN}") String discordToken) {
        SlashCommandSetup(discordToken);
    }

    @PostMapping(value = "postInfo", produces = "application/json")
    @ResponseBody
    public PlantResponse postInfo(@RequestBody PlantRequestBody plantRequestBody) {

        mongoTemplate.insert(plantRequestBody, "plantInfo");
        plantResponse = new PlantResponse();
        plantResponse.setPlantInfo(plantRequestBody.getPlantInfoData());
        return plantResponse;
    }

    @CrossOrigin
    @GetMapping("getInfo/{name}")
    @ResponseBody
    public PlantInfoData getInfo(@PathVariable String name) {
        List<PlantRequestBody> response = new ArrayList<>();
        Query findPlants = new Query();
        findPlants.addCriteria(Criteria.where("plantInfoData.name").is(name));
        ObjectMapper objectMapper = new ObjectMapper();
        response = mongoTemplate.find(findPlants, PlantRequestBody.class);

        return response.get(0).getPlantInfoData();
    }


}
