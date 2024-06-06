package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName purchase_view
 */
@TableName(value ="purchase_view")
@Data
public class PurchaseView implements Serializable {
    private Integer fid;

    private Double totalprice;

    private Long totalnum;

    private static final long serialVersionUID = 1L;
}