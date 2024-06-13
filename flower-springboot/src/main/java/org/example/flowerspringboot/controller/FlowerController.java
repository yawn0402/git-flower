package org.example.flowerspringboot.controller;


import jakarta.validation.constraints.NotNull;
import org.example.flowerspringboot.pojo.Commentstable;
import org.example.flowerspringboot.pojo.FlowerList;
import org.example.flowerspringboot.pojo.Flowers;
import org.example.flowerspringboot.pojo.Pictures;
import org.example.flowerspringboot.service.FlowersService;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flower")
@CrossOrigin
@Validated
public class FlowerController {
    @Autowired
    private FlowersService flowersService;

    @GetMapping("list")
    public Result<FlowerList> getFlowerList(){
        return flowersService.getFlowerList();
    }

    @GetMapping("search")
    public Result<FlowerList> searchFlower( @NotNull String toSearch){
        return flowersService.searchFlower(toSearch);
    }

    @GetMapping("detail")
    public Result<Flowers> detail(@NotNull  String fid){
        return flowersService.detail(fid);
    }

    @GetMapping("pics")
    public Result<List<Pictures>> pics( @NotNull  Integer fid){
        return flowersService.pics(fid);
    }
    @GetMapping("comment")
    public Result<List<Commentstable>> comment( @NotNull  Integer fid){
        return flowersService.comment(fid);
    }
}


