package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Carts;
import org.example.flowerspringboot.pojo.Orderstable;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.utils.Result;

/**
* @author 陈涛
* @description 针对表【orderstable】的数据库操作Service
* @createDate 2024-06-06 11:02:49
*/
public interface OrderstableService extends IService<Orderstable> {

    Result<PageBean<Orderstable>> orderList(Integer pageNum,Integer bid, Integer state);

    Result<String> changeOrdstate(Integer ordid, Integer ordstate);
}
