package org.example.flowerspringboot.controller;


import jakarta.validation.constraints.NotNull;
import org.example.flowerspringboot.pojo.Inventory;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.service.InventoryService;
import org.example.flowerspringboot.service.LossService;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("inventory")
@CrossOrigin
@Validated
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private LossService lossService;

    @GetMapping("list")
    public Result<PageBean<Inventory>>list(String time,@NotNull Integer pageNum){
        return inventoryService.list(time,pageNum);
    }

    @GetMapping("allList")
    public Result<List<Inventory>>allList(){
        return inventoryService.allList();
    }
    @PostMapping("add")
    public Result<String>add( @NotNull Integer realnum){
        return inventoryService.add(realnum);
    }

    @PostMapping("loss")
    public Result<String>loss( @NotNull Integer fid ,@NotNull Integer lnum){
        return lossService.loss(fid,lnum);
    }



}
