package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName purchase
 */
@TableName(value ="purchase")
@Data
public class Purchase implements Serializable {
    private Integer purid;

    private Integer fid;

    private Integer sid;

    private Double purprice;

    private Integer purnum;

    private Date purtime;

    private static final long serialVersionUID = 1L;
}