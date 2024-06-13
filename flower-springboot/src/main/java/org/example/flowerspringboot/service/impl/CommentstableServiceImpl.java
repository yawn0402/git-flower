package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.flowerspringboot.pojo.Carts;
import org.example.flowerspringboot.pojo.Commentstable;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.service.CommentstableService;
import org.example.flowerspringboot.mapper.CommentstableMapper;
import org.example.flowerspringboot.utils.Result;
import org.example.flowerspringboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【commentstable】的数据库操作Service实现
* @createDate 2024-06-06 11:02:48
*/
@Service
public class CommentstableServiceImpl extends ServiceImpl<CommentstableMapper, Commentstable>
    implements CommentstableService{

    @Autowired
    private CommentstableMapper commentstableMapper;
    @Override
    public Result<PageBean<Commentstable>> getComment(Integer pageNum,Integer bid) {
        PageBean<Commentstable> pageBean=new PageBean<>();
        PageHelper.startPage(pageNum,4);
        LambdaQueryWrapper<Commentstable> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if (bid!=null) {
            lambdaQueryWrapper.eq(Commentstable::getBid, ThreadLocalUtil.get());
        }
        List<Commentstable> commentstableList=commentstableMapper.selectList(lambdaQueryWrapper);
        pageBean.setItems(commentstableList);
        //通过查询结果的List强转成page
        Page<Commentstable> page=( Page<Commentstable>)commentstableList;
        pageBean.setTotal(page.getTotal());
//        System.out.println(pageBean.getItems());
        return Result.success(pageBean);
    }

    @Override
    public Result<String> addComment(Commentstable commentstable) {
        commentstableMapper.insert(commentstable);
        return Result.success();
    }

    @Override
    public Result<String> commentDelete(Integer comid) {
        LambdaQueryWrapper<Commentstable> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Commentstable::getComid, comid);
        commentstableMapper.delete(lambdaQueryWrapper);

        return Result.success();
    }
}




