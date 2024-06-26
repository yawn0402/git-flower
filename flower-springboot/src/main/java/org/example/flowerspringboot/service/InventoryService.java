package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.utils.Result;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【inventory】的数据库操作Service
* @createDate 2024-06-06 11:02:48
*/
public interface InventoryService extends IService<Inventory> {

    Result<PageBean<Inventory>> list(String time,Integer pageNum);

    Result<String> add(Integer realnum);

    Result< List<Inventory>> allList();
}
