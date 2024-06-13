package org.example.flowerspringboot.controller;

import jakarta.validation.constraints.NotNull;
import org.example.flowerspringboot.pojo.Carts;
import org.example.flowerspringboot.pojo.Orderstable;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.service.CartsService;
import org.example.flowerspringboot.service.OrderstableService;
import org.example.flowerspringboot.service.ReordersService;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trade")
@CrossOrigin
@Validated
public class TradeController {
    @Autowired
    private CartsService cartsService;
    @Autowired
    private OrderstableService orderstableService;
    @Autowired
    private ReordersService reordersService;

    @PostMapping("addCart")
    public Result<String>addCart(@RequestBody @NotNull Carts carts){
        return cartsService.addCart(carts);
    }


    @PostMapping("addOrder")
    public Result<String>addOrder(Integer carid, Integer fid, Integer ordnum, Double ordprice){
        return cartsService.addOrder(carid,fid,ordnum,ordprice);
    }

    @GetMapping("cartList")
    public Result<PageBean<Carts>>cartList(@NotNull Integer pageNum){
        return cartsService.cartList(pageNum);
    }

    @DeleteMapping("cartDelete")
    public Result<String>cartDelete(@NotNull Integer carid){
        return cartsService.cartDelete(carid);
    }

    @GetMapping("orderList")
    public Result<PageBean<Orderstable>>orderList( @NotNull Integer pageNum , Integer bid, Integer state){
        return orderstableService.orderList(pageNum,bid, state);
    }

    @PutMapping("changeOrdstate")
    public  Result<String>changeOrdstate(@NotNull Integer ordid,@NotNull Integer ordstate){
        return orderstableService.changeOrdstate(ordid,ordstate);
    }

}
