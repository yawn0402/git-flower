package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Suppliers;
import org.example.flowerspringboot.service.SuppliersService;
import org.example.flowerspringboot.mapper.SuppliersMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【suppliers】的数据库操作Service实现
* @createDate 2024-06-06 11:02:49
*/
@Service
public class SuppliersServiceImpl extends ServiceImpl<SuppliersMapper, Suppliers>
    implements SuppliersService{

}




