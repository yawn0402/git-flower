package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.mapper.LossViewMapper;
import org.example.flowerspringboot.pojo.LossView;
import org.example.flowerspringboot.pojo.PurchaseView;
import org.example.flowerspringboot.service.PurchaseViewService;
import org.example.flowerspringboot.mapper.PurchaseViewMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【purchase_view】的数据库操作Service实现
* @createDate 2024-06-06 11:03:03
*/
@Service
public class PurchaseViewServiceImpl extends ServiceImpl<PurchaseViewMapper, PurchaseView>
    implements PurchaseViewService{

    @Autowired
    private PurchaseViewMapper purchaseViewMapper;
    @Override
    public Result<List<PurchaseView>> purchaseView() {
        List<PurchaseView>purchaseViews=purchaseViewMapper.selectList(null);
        return Result.success(purchaseViews);
    }
}




