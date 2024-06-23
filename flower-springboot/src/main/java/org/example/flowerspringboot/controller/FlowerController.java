package org.example.flowerspringboot.controller;


import jakarta.validation.constraints.NotNull;
import org.example.flowerspringboot.pojo.*;
import org.example.flowerspringboot.service.FlowersService;
import org.example.flowerspringboot.service.LossService;
import org.example.flowerspringboot.service.PurchaseService;
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
    @Autowired
    private LossService lossService;
    @Autowired
    private PurchaseService purchaseService;

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

    @GetMapping("lossList")
    public Result<PageBean<Loss>> lossList( Integer fid,@NotNull Integer pageNum){
        return lossService.lossList(fid,pageNum);
    }

    @GetMapping("purchaseList")
    public Result<PageBean<Purchase>> purchaseList( Integer fid,@NotNull Integer pageNum){
        return purchaseService.purchaseList(fid,pageNum);
    }


}


