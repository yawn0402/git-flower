package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.flowerspringboot.pojo.Carts;
import org.example.flowerspringboot.pojo.Commentstable;
import org.example.flowerspringboot.pojo.Orderstable;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.service.OrderstableService;
import org.example.flowerspringboot.mapper.OrderstableMapper;
import org.example.flowerspringboot.utils.Result;
import org.example.flowerspringboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【orderstable】的数据库操作Service实现
* @createDate 2024-06-06 11:02:49
*/
@Service
public class OrderstableServiceImpl extends ServiceImpl<OrderstableMapper, Orderstable>
    implements OrderstableService{

    @Autowired
    private OrderstableMapper orderstableMapper;
    @Override
    public Result<PageBean<Orderstable>> orderList(Integer pageNum,Integer bid, Integer state) {
        PageBean<Orderstable> pageBean=new PageBean<>();
        PageHelper.startPage(pageNum,4);
        List<Orderstable> orderstableList=orderstableMapper.myList(bid,  state);
        pageBean.setItems(orderstableList);
        //通过查询结果的List强转成page
        Page<Orderstable> page=( Page<Orderstable>)orderstableList;
        pageBean.setTotal(page.getTotal());
//        System.out.println(pageBean.getItems());
        return Result.success(pageBean);
    }

    @Override
    public Result<String> changeOrdstate(Integer ordid, Integer ordstate) {
        LambdaQueryWrapper<Orderstable> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Orderstable::getOrdid,ordid);
        Orderstable orderstable=orderstableMapper.selectOne(lambdaQueryWrapper);
        orderstable.setOrdstate(ordstate);
        orderstableMapper.update(orderstable,lambdaQueryWrapper);

        return Result.success();
    }
}




