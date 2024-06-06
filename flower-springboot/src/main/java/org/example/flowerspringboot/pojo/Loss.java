package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName loss
 */
@TableName(value ="loss")
@Data
public class Loss implements Serializable {
    private Integer lid;

    private Integer fid;

    private Integer lnum;

    private Date ltime;

    private static final long serialVersionUID = 1L;
}