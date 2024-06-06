package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName pictures
 */
@TableName(value ="pictures")
@Data
public class Pictures implements Serializable {
    private Integer pid;

    private Integer fid;

    private String paddress;

    private static final long serialVersionUID = 1L;
}