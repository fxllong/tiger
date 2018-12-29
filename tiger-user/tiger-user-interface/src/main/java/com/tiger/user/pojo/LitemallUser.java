package com.tiger.user.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table(name = "litemall_user")
public class LitemallUser {

    public static final Boolean NOT_DELETED = false;

    public static final Boolean IS_DELETED = true;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String username;

    private String password;

    private Byte gender;

    private LocalDate birthday;

    private LocalDateTime lastLoginTime;

    private String lastLoginIp;

    private Byte userLevel;

    private String nickname;

    private String mobile;

    private String avatar;

    private String weixinOpenid;

    private Byte status;

    private LocalDateTime addTime;

    private LocalDateTime updateTime;

    private Boolean deleted;

}