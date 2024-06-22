package org.example.flowerspringboot.mapper;

import org.example.flowerspringboot.pojo.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 陈涛
* @description 针对表【inventory】的数据库操作Mapper
* @createDate 2024-06-06 11:02:48
* @Entity org.example.flowerspringboot.pojo.Inventory
*/
public interface InventoryMapper extends BaseMapper<Inventory> {
    void toInventory(Integer realNum);

}




