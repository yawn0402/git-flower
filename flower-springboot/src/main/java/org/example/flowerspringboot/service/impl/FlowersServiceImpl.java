package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.mapper.CommentstableMapper;
import org.example.flowerspringboot.mapper.PicturesMapper;
import org.example.flowerspringboot.pojo.*;
import org.example.flowerspringboot.service.FlowersService;
import org.example.flowerspringboot.mapper.FlowersMapper;
import org.example.flowerspringboot.utils.Result;
import org.example.flowerspringboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【flowers】的数据库操作Service实现
* @createDate 2024-06-06 11:02:48
*/
@Service
public class FlowersServiceImpl extends ServiceImpl<FlowersMapper, Flowers>
    implements FlowersService{

    @Autowired
    private FlowersMapper flowersMapper;
    @Autowired
    private CommentstableMapper commentstableMapper;
    @Autowired
    private PicturesMapper picturesMapper;

    @Override
    public Result<FlowerList> getFlowerList() {
        FlowerList flowerList=new FlowerList();
        List<Flowers> items=flowersMapper.selectList(null);
        Long num = flowersMapper.selectCount(null);
        flowerList.setFlowerNum(num);
        flowerList.setItems(items);
        return Result.success(flowerList);
    }
    @Override
    public Result<FlowerList> searchFlower(String toSearch) {
        FlowerList flowerList=new FlowerList();
        LambdaQueryWrapper<Flowers> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Flowers::getFname,toSearch);
        List<Flowers> items=flowersMapper.selectList(lambdaQueryWrapper);
        Long num = flowersMapper.selectCount(lambdaQueryWrapper);
        flowerList.setFlowerNum(num);
        flowerList.setItems(items);
        return Result.success(flowerList);

    }
    @Override
    public Result<Flowers> detail(String fid) {
        LambdaQueryWrapper <Flowers>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Flowers::getFid,fid);
        Flowers flower = flowersMapper.selectOne(lambdaQueryWrapper);
        return Result.success(flower);
    }

    @Override
    public Result<List<Pictures>> pics(Integer fid) {
        LambdaQueryWrapper <Pictures>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pictures::getFid,fid);
        List<Pictures>pics=picturesMapper.selectList(lambdaQueryWrapper);
        return Result.success(pics);
    }

    @Override
    public Result<List<Commentstable>>  comment(Integer fid) {
        LambdaQueryWrapper <Commentstable>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Commentstable::getFid,fid);
        List<Commentstable>commentstableList=commentstableMapper.selectList(lambdaQueryWrapper);
        return Result.success(commentstableList);
    }


}




