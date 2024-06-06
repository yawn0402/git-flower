package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Buyers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.utils.Result;

/**
* @author 陈涛
* @description 针对表【buyers】的数据库操作Service
* @createDate 2024-06-06 10:58:11
*/
public interface BuyersService extends IService<Buyers> {

    Result<String> register(String buyername, String password);

    Result login(String buyername, String password);

    Result<Buyers> buyerInfo();
}
