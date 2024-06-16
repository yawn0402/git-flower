package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Purchase;
import org.example.flowerspringboot.service.PurchaseService;
import org.example.flowerspringboot.mapper.PurchaseMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}




