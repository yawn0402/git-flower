package org.example.flowerspringboot.controller;

import jakarta.validation.constraints.Pattern;
import org.example.flowerspringboot.pojo.Buyers;
import org.example.flowerspringboot.service.BuyersService;
import org.example.flowerspringboot.utils.Result;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("buyer")
@CrossOrigin
@Validated
public class BuyerController {
    @Autowired
    private BuyersService buyersService;

    @GetMapping("buyerInfo")
    public Result<Buyers> buyerInfo(){
        return buyersService.buyerInfo();

    }
    @PostMapping("login")
    public Result<String> login(@Pattern(regexp = "^\\S{3,16}$") String buyername, String password){
        Result result=buyersService.login(buyername,password);
        return result;
    }

    @PostMapping("register")
    public Result<String> register(@Pattern(regexp = "^\\S{3,16}$") String buyername, String password){
        Result<String> result=buyersService.register(buyername,password);
        return result;
    }

    @PutMapping("rePwd")
    public Result<String> rePwd(@RequestParam String newPwd){
        Result result=buyersService.rePwd(newPwd);
        return result;
    }
    @PutMapping("reBaseInfo")
    public Result<String> reBaseInfo(@RequestBody Buyers buyers){
        Result result=buyersService.reBaseInfo(buyers);
        return result;
    }

    @PatchMapping("avatar")
    public Result<String> updataavatar(@RequestParam  String avatarUrl){
        Result<String> result=buyersService.updataavatar(avatarUrl);
        return result;
    }

}
