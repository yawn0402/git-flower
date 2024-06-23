package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.ReordersView;
import org.example.flowerspringboot.service.ReordersViewService;
import org.example.flowerspringboot.mapper.ReordersViewMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【reorders_view】的数据库操作Service实现
* @createDate 2024-06-06 11:03:03
*/
@Service
public class ReordersViewServiceImpl extends ServiceImpl<ReordersViewMapper, ReordersView>
    implements ReordersViewService{
    @Autowired
    private  ReordersViewMapper reordersViewMapper;

    @Override
    public Result<List<ReordersView>> reordersView() {
        List<ReordersView> reordersViews=reordersViewMapper.selectList(null);
        return Result.success(reordersViews);
    }
}




