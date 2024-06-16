package org.example.flowerspringboot.controller;


import jakarta.validation.constraints.NotNull;
import org.example.flowerspringboot.pojo.Flowers;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.pojo.Purchase;
import org.example.flowerspringboot.pojo.Suppliers;
import org.example.flowerspringboot.service.FlowersService;
import org.example.flowerspringboot.service.PicturesService;
import org.example.flowerspringboot.service.PurchaseService;
import org.example.flowerspringboot.service.SuppliersService;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("saller")
@CrossOrigin
@Validated
public class SallerController {
    @Autowired
    private FlowersService flowersService;
    @Autowired
    private SuppliersService suppliersService;
    @Autowired
    private PicturesService picturesService;

    @Autowired
    private PurchaseService purchaseService;


        @GetMapping("/flowerList")
    public Result<PageBean<Flowers>>sallerFlowerList(@NotNull Integer pageNum,String fname,Integer sid){
        return flowersService.sallerFlowerList(pageNum, fname, sid);
    }

    @GetMapping("/suppliersList")
    public Result<List<Suppliers>>suppliersList(){
        return suppliersService.suppliersList();
    }


    @PostMapping("suppliersAdd")
    public Result<String>suppliersAdd(@NotNull @RequestBody Suppliers supplier){
            return  suppliersService.suppliersAdd(supplier);
    }

    @PostMapping("flowerPurchase")
    public Result<String>flowerPurchase(@NotNull @RequestBody Purchase purchase){
            return purchaseService.flowerPurchase(purchase);
    }
    @DeleteMapping("flowerDelete")
    public Result<String>flowerDelete(@NotNull Integer fid){
            return flowersService.flowerDelete(fid);
    }

}
