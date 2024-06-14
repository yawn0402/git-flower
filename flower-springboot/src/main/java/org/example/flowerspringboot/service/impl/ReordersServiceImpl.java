package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.example.flowerspringboot.mapper.OrderstableMapper;
import org.example.flowerspringboot.pojo.Orderstable;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.pojo.Reorders;
import org.example.flowerspringboot.service.ReordersService;
import org.example.flowerspringboot.mapper.ReordersMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* @author 陈涛
* @description 针对表【reorders】的数据库操作Service实现
* @createDate 2024-06-06 11:02:49
*/
@Service
public class ReordersServiceImpl extends ServiceImpl<ReordersMapper, Reorders>
    implements ReordersService{

    @Autowired
    private ReordersMapper reordersMapper;
    @Autowired
    private OrderstableMapper orderstableMapper;
    @Override
    public Result<String> addReorder(Reorders reorders) {
        reordersMapper.insert(reorders);
        return Result.success();
    }

    @Override
    public Result<PageBean<Reorders>> reorderList(Integer pageNum, Integer bid, Integer rstate) {
            PageBean<Reorders> pageBean=new PageBean<>();
            PageHelper.startPage(pageNum,4);
            List<Reorders> reordersList=reordersMapper.myList(rstate);
            //紧跟着PageHelper.startPage(pageNum,4);这句的会变成page类型

            List<Reorders> allReordersList=reordersMapper.myList(rstate);


        if(bid!=null){
            reordersList.clear();
            PageHelper.startPage(pageNum,4);

                LambdaQueryWrapper <Orderstable>lambdaQueryWrapper=new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(Orderstable::getBid,bid);
                List<Orderstable>orderstableList=orderstableMapper.selectList(lambdaQueryWrapper);

                //获取到当前用户的退货信息
                Iterator<Reorders> riter=allReordersList.iterator();
                while(riter.hasNext()) {
                    Reorders reorders=riter.next();
                    Iterator<Orderstable> oiter=orderstableList.iterator();
                    while(oiter.hasNext()){
                        Orderstable orderstable=oiter.next();
                        if(reorders.getOrdid()==orderstable.getOrdid()){
                            reordersList.add(reorders);
                        }
                    }
                }

            }


        System.out.println("reordersList");
        System.out.println(reordersList);



            pageBean.setItems(reordersList);
            //通过查询结果的List强转成page
            Page<Reorders> page=( Page<Reorders>)reordersList;
            pageBean.setTotal(page.getTotal());
    //        System.out.println(pageBean.getItems());
            return Result.success(pageBean);
    }

    @Override
    public Result<String> changeReorderState(Integer rid, Integer rstate) {
        LambdaQueryWrapper<Reorders> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Reorders::getRid,rid);
        Reorders reorders=reordersMapper.selectOne(lambdaQueryWrapper);
        reorders.setRstate(rstate);
        reordersMapper.update(reorders,lambdaQueryWrapper);

        return Result.success();
    }
}




