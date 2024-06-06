package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.PurchaseView;
import org.example.flowerspringboot.service.PurchaseViewService;
import org.example.flowerspringboot.mapper.PurchaseViewMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【purchase_view】的数据库操作Service实现
* @createDate 2024-06-06 11:03:03
*/
@Service
public class PurchaseViewServiceImpl extends ServiceImpl<PurchaseViewMapper, PurchaseView>
    implements PurchaseViewService{

}




