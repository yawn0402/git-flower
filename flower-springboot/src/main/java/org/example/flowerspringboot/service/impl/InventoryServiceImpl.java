package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Inventory;
import org.example.flowerspringboot.service.InventoryService;
import org.example.flowerspringboot.mapper.InventoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【inventory】的数据库操作Service实现
* @createDate 2024-06-06 11:02:48
*/
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory>
    implements InventoryService{

}



