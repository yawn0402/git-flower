package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.http.impl.client.RequestWrapper;
import org.example.flowerspringboot.pojo.Flowers;
import org.example.flowerspringboot.pojo.Inventory;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.service.InventoryService;
import org.example.flowerspringboot.mapper.InventoryMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【inventory】的数据库操作Service实现
* @createDate 2024-06-06 11:02:48
*/
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory>
    implements InventoryService{

    @Autowired
    private InventoryMapper inventoryMapper;


    @Override
    public Result<PageBean<Inventory>> list(String time, Integer pageNum) {
        LambdaQueryWrapper<Inventory>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if ((!time.isEmpty())&&(!time.isBlank())){
            lambdaQueryWrapper.like(Inventory::getItime,time);
        }
        PageBean<Inventory> pageBean=new PageBean<>();
        PageHelper.startPage(pageNum,5);
        List<Inventory>inventoryList=inventoryMapper.selectList(lambdaQueryWrapper);

        pageBean.setItems(inventoryList);
        //通过查询结果的List强转成page
        Page<Inventory> page=( Page<Inventory>)inventoryList;
        pageBean.setTotal(page.getTotal());

        return Result.success(pageBean);
    }

    @Override
    public Result<String> add(Integer realnum) {
        inventoryMapper.toInventory(realnum);
        return Result.success();
    }

    @Override
    public Result< List<Inventory>> allList() {
       List<Inventory> inventoryList=inventoryMapper.selectList(null);
        return Result.success(inventoryList);
    }
}




