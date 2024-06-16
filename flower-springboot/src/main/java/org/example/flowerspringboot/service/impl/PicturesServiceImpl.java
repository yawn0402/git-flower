package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Pictures;
import org.example.flowerspringboot.service.PicturesService;
import org.example.flowerspringboot.mapper.PicturesMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【pictures】的数据库操作Service实现
* @createDate 2024-06-06 11:02:49
*/
@Service
public class PicturesServiceImpl extends ServiceImpl<PicturesMapper, Pictures>
    implements PicturesService{

    @Autowired
    private PicturesMapper picturesMapper;
    @Override
    public Result<String> picsAdd(Pictures pictures) {
        picturesMapper.insert(pictures);
        return Result.success();
    }

    @Override
    public Result<String> picDelete(Integer pid) {
        LambdaQueryWrapper<Pictures>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pictures::getPid,pid);
        picturesMapper.delete(lambdaQueryWrapper);
        return Result.success();
    }
}




