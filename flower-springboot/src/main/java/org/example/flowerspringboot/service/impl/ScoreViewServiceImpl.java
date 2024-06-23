package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.ScoreView;
import org.example.flowerspringboot.service.ScoreViewService;
import org.example.flowerspringboot.mapper.ScoreViewMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【score_view】的数据库操作Service实现
* @createDate 2024-06-06 11:03:03
*/
@Service
public class ScoreViewServiceImpl extends ServiceImpl<ScoreViewMapper, ScoreView>
    implements ScoreViewService{

    @Autowired
    private  ScoreViewMapper scoreViewMapper;
    @Override
    public Result<List<ScoreView>> scoreView() {
        List<ScoreView> scoreViews=scoreViewMapper.selectList(null);

        return Result.success(scoreViews);
    }
}




