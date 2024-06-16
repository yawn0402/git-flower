package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Suppliers;
import org.example.flowerspringboot.service.SuppliersService;
import org.example.flowerspringboot.mapper.SuppliersMapper;
import org.example.flowerspringboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【suppliers】的数据库操作Service实现
* @createDate 2024-06-06 11:02:49
*/
@Service
public class SuppliersServiceImpl extends ServiceImpl<SuppliersMapper, Suppliers>
    implements SuppliersService{

    @Autowired
    private SuppliersMapper suppliersMapper;
    @Override
    public Result<List<Suppliers>> suppliersList() {
        List<Suppliers>suppliersList=suppliersMapper.selectList(null);
        return Result.success(suppliersList);
    }

    @Override
    public Result<String> suppliersAdd(Suppliers supplier) {
//        System.out.println("------------");
//        System.out.println(supplier.getStel());
//
//        System.out.println(supplier.getSname());
        suppliersMapper.insert(supplier);
        return Result.success();
    }
}




