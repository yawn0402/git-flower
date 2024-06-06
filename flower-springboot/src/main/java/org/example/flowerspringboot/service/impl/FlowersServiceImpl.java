package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.flowerspringboot.pojo.Flowers;
import org.example.flowerspringboot.service.FlowersService;
import org.example.flowerspringboot.mapper.FlowersMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈涛
* @description 针对表【flowers】的数据库操作Service实现
* @createDate 2024-06-06 11:02:48
*/
@Service
public class FlowersServiceImpl extends ServiceImpl<FlowersMapper, Flowers>
    implements FlowersService{

}




