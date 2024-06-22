package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName recinfo
 */
@TableName(value ="recinfo")
@Data
public class Recinfo implements Serializable {
    private Integer recid;

    private Integer bid;

    private String rectele;

    private String recaddress;

    private static final long serialVersionUID = 1L;
}