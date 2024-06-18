package com.discordapi.Dataholder;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
public class PlantInfoData {

    private String name;

    private String height;

    private String sunLight;

    private String water;

    private String originDescription;



}
