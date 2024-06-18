package com.discordapi.Repository;

import com.discordapi.Dataholder.PlantInfoData;
import com.discordapi.Request.PlantRequestBody;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Objects;

public interface PlantRepository extends MongoRepository<PlantRequestBody, String> {

    @Query("{plantInfoData.name:'?0'}")
    String findPlantByName(String name);




}
