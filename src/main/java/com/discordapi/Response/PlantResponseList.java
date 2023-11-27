package com.discordapi.Response;

import com.discordapi.Dataholder.PlantInfoData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlantResponseList {
    List<PlantInfoData> plantInfoDataList = new ArrayList<>();
}
