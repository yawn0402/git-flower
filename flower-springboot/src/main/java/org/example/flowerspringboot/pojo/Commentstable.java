package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName commentstable
 */
@TableName(value ="commentstable")
@Data
public class Commentstable implements Serializable {
    private Integer comid;

    private Integer bid;

    private Integer fid;

    private Integer score;

    private String comments;

    private Date comtime;

    private static final long serialVersionUID = 1L;
}