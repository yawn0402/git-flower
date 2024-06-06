package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Reorders;
import org.example.flowerspringboot.service.ReordersService;
import org.example.flowerspringboot.mapper.ReordersMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【reorders】的数据库操作Service实现
* @createDate 2024-06-06 11:02:49
*/
@Service
public class ReordersServiceImpl extends ServiceImpl<ReordersMapper, Reorders>
    implements ReordersService{

}




