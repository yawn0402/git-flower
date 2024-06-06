package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName orderstable
 */
@TableName(value ="orderstable")
@Data
public class Orderstable implements Serializable {
    private Integer ordid;

    private Integer bid;

    private Integer fid;

    private Integer ordnum;

    private Double ordprice;

    private Date ctime;

    private Integer ordstate;

    private static final long serialVersionUID = 1L;
}