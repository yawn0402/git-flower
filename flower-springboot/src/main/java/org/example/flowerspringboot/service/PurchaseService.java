package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Loss;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.pojo.Purchase;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.utils.Result;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【purchase】的数据库操作Service
* @createDate 2024-06-06 11:02:49
*/
public interface PurchaseService extends IService<Purchase> {

    Result<String> flowerPurchase(Purchase purchase);

    Result<PageBean<Purchase>>purchaseList(Integer fid, Integer pageNum);
}
