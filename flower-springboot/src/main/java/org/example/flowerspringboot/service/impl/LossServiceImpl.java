package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.validation.constraints.NotNull;
import org.example.flowerspringboot.pojo.Loss;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.pojo.Reorders;
import org.example.flowerspringboot.service.LossService;
import org.example.flowerspringboot.mapper.LossMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【loss】的数据库操作Service实现
* @createDate 2024-06-06 11:02:48
*/
@Service
public class LossServiceImpl extends ServiceImpl<LossMapper, Loss>
    implements LossService{
    @Autowired
    private LossMapper lossMapper;

    @Override
    public Result<String> loss(Integer fid, Integer lnum) {
        Loss loss=new Loss();
        loss.setFid(fid);
        loss.setLnum(lnum);
        lossMapper.insert(loss);
        return Result.success();
    }

    @Override
    public Result<PageBean<Loss>> lossList(Integer fid,@NotNull Integer pageNum) {
        PageBean<Loss> pageBean=new PageBean<>();
        PageHelper.startPage(pageNum,5);
        LambdaQueryWrapper<Loss>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(fid!=null){
            lambdaQueryWrapper.eq(Loss::getFid,fid);
        }
        List<Loss>lossList=lossMapper.selectList(lambdaQueryWrapper);
        pageBean.setItems(lossList);
        //通过查询结果的List强转成page
        Page<Loss> page=( Page<Loss>)lossList;
        pageBean.setTotal(page.getTotal());
        //        System.out.println(pageBean.getItems());
        return Result.success(pageBean);
    }
}




