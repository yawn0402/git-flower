package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName buyers
 */
@TableName(value ="buyers")
@Data
public class Buyers implements Serializable {
    private Integer bid;

    private String bname;

    private String bpwd;

    private String bsex;

    private String bavatar;

    private String btele;

    private String baddress;

    private static final long serialVersionUID = 1L;
}