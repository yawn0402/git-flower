package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Carts;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.utils.Result;

/**
* @author 陈涛
* @description 针对表【carts】的数据库操作Service
* @createDate 2024-06-06 11:02:48
*/
public interface CartsService extends IService<Carts> {

    Result<String> addCart(Carts carts);

    Result<String> addOrder(Integer carid, Integer fid, Integer ordnum, Double ordprice);

    Result<PageBean<Carts>> cartList(Integer pageNum);

    Result<String> cartDelete(Integer carid);
}
