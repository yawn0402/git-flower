package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Orderstable;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.pojo.Reorders;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.utils.Result;

/**
* @author 陈涛
* @description 针对表【reorders】的数据库操作Service
* @createDate 2024-06-06 11:02:49
*/
public interface ReordersService extends IService<Reorders> {


    Result<String> addReorder(Reorders reorders);

    Result<PageBean<Reorders>> reorderList(Integer pageNum, Integer bid, Integer rstate);

    Result<String> changeReorderState(Integer rid, Integer rstate);
}
