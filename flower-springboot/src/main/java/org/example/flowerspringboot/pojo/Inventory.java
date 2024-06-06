package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName inventory
 */
@TableName(value ="inventory")
@Data
public class Inventory implements Serializable {
    private Integer iid;

    private Integer purnum;

    private Double purprice;

    private Integer ordnum;

    private Double ordprice;

    private Double reoprice;

    private Double profit;

    private Date itime;

    private Integer realnum;

    private static final long serialVersionUID = 1L;
}