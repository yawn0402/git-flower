package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName reorders_view
 */
@TableName(value ="reorders_view")
@Data
public class ReordersView implements Serializable {
    private Integer fid;

    private Double totalprice;

    private Long recount;

    private static final long serialVersionUID = 1L;
}