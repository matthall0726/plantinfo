package com.discordapi.Controller;

import com.discordapi.Request.PlantRequestBody;
import com.discordapi.Response.PlantResponse;
import com.mongodb.MongoWriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.discordapi.SlashCommands.SlashCommand.SlashCommandSetup;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DiscordController {

    private static MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        DiscordController.mongoTemplate = mongoTemplate;
    }


    public static Logger LOGGER = LoggerFactory.getLogger(DiscordController.class);

    PlantResponse plantResponse;

    public static boolean isDuplicate(String name) {
        Query findPlants = new Query();
        findPlants.addCriteria(Criteria.where("plantInfoData.name").is(name));
        if (mongoTemplate.exists(findPlants, PlantRequestBody.class, "plantInfo")) {
            return true;
        }
        return false;
    }

    @PostMapping(value = "postInfo", produces = "application/json")
    @ResponseBody
    public static void postInfo(@RequestBody PlantRequestBody plantRequestBody) {

        try {
            Query findPlants = new Query();
            findPlants.addCriteria(Criteria.where("plantInfoData.name").is(plantRequestBody.getPlantInfoData().getName()));

            if (mongoTemplate.exists(findPlants, PlantRequestBody.class, "plantInfo")) {
                System.err.println("Duplicate Plant Name");
            } else {
                mongoTemplate.insert(plantRequestBody, "plantInfo");
            }

        } catch (MongoWriteException e) {

            if (e.getError().getCode() == 11000) {
                LOGGER.error("Duplicate key error: The name already exists.", e);
            } else {
                // Handle other write errors
                LOGGER.error("ERROR While inserting document", e);
            }
        } catch (Exception e) {
            // Handle other exceptions
            LOGGER.error("Unknown Error Caught", e);
        }


    }
    @CrossOrigin
    @GetMapping("getInfo/{name}")
    @ResponseBody
    public static List<PlantRequestBody> getInfo(@PathVariable String name) {
        List<PlantRequestBody> response;
        Query findPlants = new Query();
        System.out.println(mongoTemplate.collectionExists("plantInfo"));
        findPlants.addCriteria(Criteria.where("plantInfoData.name").is(name));

        response = mongoTemplate.find(findPlants, PlantRequestBody.class, "plantInfo");
        if (response.isEmpty()) {
            System.out.println("No documents found for name: " + name);
        } else {
            System.out.println(response);
        }

        return response;

    }

}
