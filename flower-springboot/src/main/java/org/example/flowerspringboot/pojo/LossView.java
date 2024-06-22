package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName loss_view
 */
@TableName(value ="loss_view")
@Data
public class LossView implements Serializable {
    private Integer fid;

    private Long lsum;

    private Long lcount;

    private static final long serialVersionUID = 1L;
}