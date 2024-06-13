package org.example.flowerspringboot;


import org.example.flowerspringboot.mapper.BuyersMapper;
import org.example.flowerspringboot.mapper.FlowersMapper;
import org.example.flowerspringboot.pojo.Buyers;
import org.example.flowerspringboot.pojo.Flowers;
import org.example.flowerspringboot.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class FlowerSpringbootApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private BuyersMapper buyersMapper;
    @Autowired
    private FlowersMapper flowersMapper;

    @Test
    void contextLoads() {
//     List<Buyers>buyers=buyersMapper.selectList(null);
        List<Flowers> flowersList = flowersMapper.selectList(null);

        try {
            System.out.println(dataSource.getConnection());
            System.out.println(flowersList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testToken() {
        JwtHelper jwtHelper = new JwtHelper();
        System.out.println(jwtHelper.createToken(2));
    }
}
