package com.discordapi.Repository;

import com.discordapi.Dataholder.PlantInfoData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Objects;

public interface PlantRepository extends MongoRepository<PlantInfoData, String> {



}
