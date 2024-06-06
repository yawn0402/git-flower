package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Buyers;
import org.example.flowerspringboot.service.BuyersService;
import org.example.flowerspringboot.mapper.BuyersMapper;
import org.example.flowerspringboot.utils.JwtHelper;
import org.example.flowerspringboot.utils.Result;
import org.example.flowerspringboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
* @author 陈涛
* @description 针对表【buyers】的数据库操作Service实现
* @createDate 2024-06-06 10:58:11
*/
@Service
public class BuyersServiceImpl extends ServiceImpl<BuyersMapper, Buyers>
    implements BuyersService{

    @Autowired
    private BuyersMapper buyersMapper;
    private JwtHelper jwtHelper=new JwtHelper();
    @Override
    public Result register(String buyername, String password) {
        Buyers buyers=new Buyers();
        buyers.setBname(buyername);
        buyers.setBpwd(password);
        buyersMapper.insert(buyers);
        return Result.success("注册成功");
    }

    @Override
    public Result login(String buyername, String password) {
        LambdaQueryWrapper<Buyers> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Buyers::getBname,buyername);
        Buyers selectOne= buyersMapper.selectOne(lambdaQueryWrapper);
        if (selectOne==null) {
            return Result.error("用户名错误");
        }
        if (StringUtils.isNotEmpty(selectOne.getBpwd())&&selectOne.getBpwd().equals(password)){

            //正确
            String token= jwtHelper.createToken(selectOne.getBid());
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @Override
    public Result<Buyers> buyerInfo() {
        LambdaQueryWrapper<Buyers>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Buyers::getBid,ThreadLocalUtil.get());
        Buyers buyers=buyersMapper.selectOne(lambdaQueryWrapper);
        buyers.setBpwd("");
        return Result.success(buyers);
    }
}




