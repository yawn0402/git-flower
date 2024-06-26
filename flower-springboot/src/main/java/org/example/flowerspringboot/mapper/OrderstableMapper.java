package org.example.flowerspringboot.mapper;

import org.example.flowerspringboot.pojo.Orderstable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【orderstable】的数据库操作Mapper
* @createDate 2024-06-06 11:02:49
* @Entity org.example.flowerspringboot.pojo.Orderstable
*/
public interface OrderstableMapper extends BaseMapper<Orderstable> {

    List<Orderstable> myList(Integer bid, Integer state);
}




