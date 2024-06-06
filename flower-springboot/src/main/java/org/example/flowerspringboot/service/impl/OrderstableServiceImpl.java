package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Orderstable;
import org.example.flowerspringboot.service.OrderstableService;
import org.example.flowerspringboot.mapper.OrderstableMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【orderstable】的数据库操作Service实现
* @createDate 2024-06-06 11:02:49
*/
@Service
public class OrderstableServiceImpl extends ServiceImpl<OrderstableMapper, Orderstable>
    implements OrderstableService{

}




