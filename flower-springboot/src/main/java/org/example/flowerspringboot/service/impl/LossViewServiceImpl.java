package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.LossView;
import org.example.flowerspringboot.service.LossViewService;
import org.example.flowerspringboot.mapper.LossViewMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【loss_view】的数据库操作Service实现
* @createDate 2024-06-20 10:58:50
*/
@Service
public class LossViewServiceImpl extends ServiceImpl<LossViewMapper, LossView>
    implements LossViewService{

    @Autowired
   private LossViewMapper lossViewMapper;
    @Override
    public Result<List<LossView>> lossView() {
        List<LossView>lossViews=lossViewMapper.selectList(null);
        return Result.success(lossViews);
    }
}




