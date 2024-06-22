package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Loss;
import org.example.flowerspringboot.service.LossService;
import org.example.flowerspringboot.mapper.LossMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}




