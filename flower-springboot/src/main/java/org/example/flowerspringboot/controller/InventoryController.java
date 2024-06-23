package org.example.flowerspringboot.controller;


import jakarta.validation.constraints.NotNull;
import org.example.flowerspringboot.pojo.*;
import org.example.flowerspringboot.service.*;
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
    @Autowired
    private LossViewService lossViewService;
    @Autowired
    private OrderViewService orderViewService;
    @Autowired
    private ReordersViewService reordersViewService;
    @Autowired
    private PurchaseViewService purchaseViewService;
    @Autowired
    private ScoreViewService scoreViewService;

    @GetMapping("reordersView")
    public Result<List<ReordersView>>reordersView(){
        return reordersViewService.reordersView();
    }

    @GetMapping("orderView")
    public Result<List<OrderView>>orderView(){
        return orderViewService.orderView();
    }
    @GetMapping("scoreView")
    public Result<List<ScoreView>>scoreView(){
        return scoreViewService.scoreView();
    }

    @GetMapping("purchaseView")
    public Result<List<PurchaseView>>purchaseView(){
        return purchaseViewService.purchaseView();
    }
    @GetMapping("lossView")
    public Result<List<LossView>>lossView(){
        return lossViewService.lossView();
    }
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
