package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Suppliers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.utils.Result;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【suppliers】的数据库操作Service
* @createDate 2024-06-06 11:02:49
*/
public interface SuppliersService extends IService<Suppliers> {

    Result<List<Suppliers>> suppliersList();

    Result<String> suppliersAdd(Suppliers supplier);
}
