package org.example.flowerspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.flowerspringboot.mapper.OrderstableMapper;
import org.example.flowerspringboot.pojo.Carts;
import org.example.flowerspringboot.pojo.Orderstable;
import org.example.flowerspringboot.pojo.PageBean;
import org.example.flowerspringboot.service.CartsService;
import org.example.flowerspringboot.mapper.CartsMapper;
import org.example.flowerspringboot.utils.Result;
import org.example.flowerspringboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 陈涛
* @description 针对表【carts】的数据库操作Service实现
* @createDate 2024-06-06 11:02:48
*/
@Service
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts>
    implements CartsService{

    @Autowired
    private CartsMapper cartsMapper;
    @Autowired
    private OrderstableMapper orderstableMapper;
    @Override
    public Result<String> addCart(Carts carts) {
        System.out.println(carts.getCarprice());
        carts.setBid(ThreadLocalUtil.get());
        cartsMapper.insert(carts);
        return Result.success("添加成功");
    }

    @Override
    public Result<String> addOrder(Integer carid, Integer fid, Integer ordnum, Double ordprice) {
        Orderstable orderstable=new Orderstable();
        orderstable.setBid(ThreadLocalUtil.get());
        System.out.println("carid=");
        System.out.println(carid);
        if(carid==null||carid<1){
            orderstable.setFid(fid);
            orderstable.setOrdnum(ordnum);
            orderstable.setOrdprice(ordprice);
            orderstableMapper.insert(orderstable);
        }else{
            LambdaQueryWrapper <Carts>lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Carts::getCarid,carid);
            Carts carts=cartsMapper.selectOne(lambdaQueryWrapper);
            orderstable.setFid(carts.getFid());
            orderstable.setOrdnum(carts.getCarnum());
            orderstable.setOrdprice(carts.getCarprice());
            orderstableMapper.insert(orderstable);

            //从购物车中删除
            cartsMapper.delete(lambdaQueryWrapper);
        }
        return Result.success("购买成功");
    }

    @Override
    public Result<PageBean<Carts>> cartList(Integer pageNum) {
        PageBean<Carts> pageBean=new PageBean<>();
        PageHelper.startPage(pageNum,3);
        LambdaQueryWrapper<Carts> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Carts::getBid,ThreadLocalUtil.get());
        List<Carts>cartsList=cartsMapper.selectList(lambdaQueryWrapper);
        pageBean.setItems(cartsList);
        //通过查询结果的List强转成page
        Page<Carts> page=( Page<Carts>)cartsList;
        pageBean.setTotal(page.getTotal());
//        System.out.println(pageBean.getItems());
        return Result.success(pageBean);

    }

    @Override
    public Result<String> cartDelete(Integer carid) {
        LambdaQueryWrapper <Carts>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Carts::getCarid,carid);
        cartsMapper.delete(lambdaQueryWrapper);
        return Result.success();
    }
}




