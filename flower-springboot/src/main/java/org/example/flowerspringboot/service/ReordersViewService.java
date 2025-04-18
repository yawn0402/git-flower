package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.ReordersView;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.utils.Result;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【reorders_view】的数据库操作Service
* @createDate 2024-06-06 11:03:03
*/
public interface ReordersViewService extends IService<ReordersView> {

    Result<List<ReordersView>> reordersView();
}
