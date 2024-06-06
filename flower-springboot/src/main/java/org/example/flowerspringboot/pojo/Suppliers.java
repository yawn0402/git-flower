package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName suppliers
 */
@TableName(value ="suppliers")
@Data
public class Suppliers implements Serializable {
    private Integer sid;

    private String sname;

    private String stel;

    private static final long serialVersionUID = 1L;
}