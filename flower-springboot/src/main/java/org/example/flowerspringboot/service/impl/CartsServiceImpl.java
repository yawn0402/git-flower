package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Carts;
import org.example.flowerspringboot.service.CartsService;
import org.example.flowerspringboot.mapper.CartsMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【carts】的数据库操作Service实现
* @createDate 2024-06-06 11:02:48
*/
@Service
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts>
    implements CartsService{

}




