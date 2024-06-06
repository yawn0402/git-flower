package org.example.flowerspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName score_view
 */
@TableName(value ="score_view")
@Data
public class ScoreView implements Serializable {
    private Integer fid;

    private Long totalscore;

    private Long records;

    private static final long serialVersionUID = 1L;
}