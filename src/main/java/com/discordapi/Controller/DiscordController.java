package com.discordapi.Controller;

import com.discordapi.Dataholder.PlantInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class DiscordController {
    private PlantInfo plantInfo;

    @PostMapping("postInfo")
    public void postInfo() {
        plantInfo = new PlantInfo();


    }

    @CrossOrigin
    @GetMapping("getInfo")
    public void getInfo() {
        System.out.println("Working");

    }


}
