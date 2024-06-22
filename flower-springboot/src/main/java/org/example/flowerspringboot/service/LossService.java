package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Loss;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.utils.Result;

/**
* @author 陈涛
* @description 针对表【loss】的数据库操作Service
* @createDate 2024-06-06 11:02:48
*/
public interface LossService extends IService<Loss> {

    Result<String> loss(Integer fid, Integer lnum);
}
