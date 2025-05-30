package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.OrderView;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.utils.Result;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【order_view】的数据库操作Service
* @createDate 2024-06-06 11:03:03
*/
public interface OrderViewService extends IService<OrderView> {

    Result<List<OrderView>> orderView();
}
