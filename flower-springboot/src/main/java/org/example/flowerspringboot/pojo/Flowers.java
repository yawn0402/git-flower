package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName flowers
 */
@TableName(value ="flowers")
@Data
public class Flowers implements Serializable {
    private Integer fid;

    private String fname;

    private Double fprice;

    private Integer fnum;

    private String fintroduction;

    private String fcover;

    private Integer sid;
    private static final long serialVersionUID = 1L;
}