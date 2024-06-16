package org.example.flowerspringboot.service;

import org.example.flowerspringboot.pojo.Commentstable;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.utils.Result;

/**
* @author 陈涛
* @description 针对表【commentstable】的数据库操作Service
* @createDate 2024-06-06 11:02:48
*/
public interface CommentstableService extends IService<Commentstable> {

    Result<PageBean<Commentstable>> getComment(Integer pageNum,Integer bid,String toSearch);

    Result<String> addComment(Commentstable commentstable);

    Result<String> commentDelete(Integer comid);
}
