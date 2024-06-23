package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.flowerspringboot.pojo.Loss;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.pojo.Purchase;
import org.example.flowerspringboot.service.PurchaseService;
import org.example.flowerspringboot.mapper.PurchaseMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【purchase】的数据库操作Service实现
* @createDate 2024-06-06 11:02:49
*/
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
    implements PurchaseService{

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public Result<String> flowerPurchase(Purchase purchase) {
        System.out.println(purchase);
        purchaseMapper.insert(purchase);
        return Result.success();
    }

    @Override
    public Result<PageBean<Purchase>> purchaseList(Integer fid, Integer pageNum) {
        PageBean<Purchase> pageBean=new PageBean<>();
        PageHelper.startPage(pageNum,5);
        LambdaQueryWrapper<Purchase> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(fid!=null){
            lambdaQueryWrapper.eq(Purchase::getFid,fid);
        }
        List<Purchase>purchaseList=purchaseMapper.selectList(lambdaQueryWrapper);
        pageBean.setItems(purchaseList);
        //通过查询结果的List强转成page
        Page<Purchase> page=( Page<Purchase>)purchaseList;
        pageBean.setTotal(page.getTotal());
        //        System.out.println(pageBean.getItems());
        return Result.success(pageBean);
    }
}




