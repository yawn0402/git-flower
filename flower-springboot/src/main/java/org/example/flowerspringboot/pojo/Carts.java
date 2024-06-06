package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName carts
 */
@TableName(value ="carts")
@Data
public class Carts implements Serializable {
    private Integer carid;

    private Integer bid;

    private Integer fid;

    private Integer carnum;

    private Double carprice;

    private static final long serialVersionUID = 1L;
}