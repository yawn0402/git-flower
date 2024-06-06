package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.OrderView;
import org.example.flowerspringboot.service.OrderViewService;
import org.example.flowerspringboot.mapper.OrderViewMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【order_view】的数据库操作Service实现
* @createDate 2024-06-06 11:03:03
*/
@Service
public class OrderViewServiceImpl extends ServiceImpl<OrderViewMapper, OrderView>
    implements OrderViewService{

}




