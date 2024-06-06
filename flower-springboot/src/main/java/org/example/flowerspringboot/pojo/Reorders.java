package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName reorders
 */
@TableName(value ="reorders")
@Data
public class Reorders implements Serializable {
    private Integer rid;

    private Integer ordid;

    private Double rprice;

    private Integer rstate;

    private String reason;

    private Date rtime;

    private static final long serialVersionUID = 1L;
}