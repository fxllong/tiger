package com.tiger.item.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author xinglongfan
 */
@Data
@NoArgsConstructor
@Table(name = "litemall_category")
public class LitemallCategory {

    public static final Boolean NOT_DELETED = false;

    public static final Boolean IS_DELETED = true;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;
    private String keywords;
    @Column(name = "`desc`")
    private String desc;
    private Integer pid;
    private String iconUrl;
    private String picUrl;
    private String level;
    private Byte sortOrder;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}