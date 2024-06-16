package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.utils.Result;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【flowers】的数据库操作Service
* @createDate 2024-06-06 11:02:48
*/
public interface FlowersService extends IService<Flowers> {

    Result<FlowerList> getFlowerList();

    Result<Flowers> detail(String fid);

    Result<List<Pictures>> pics(Integer fid);

    Result <List<Commentstable>> comment(Integer fid);

    Result<FlowerList> searchFlower(String toSearch);

    Result<PageBean<Flowers>> sallerFlowerList(Integer pageNum, String fname, Integer sid);


    Result<String> flowerDelete(Integer fid);
}
