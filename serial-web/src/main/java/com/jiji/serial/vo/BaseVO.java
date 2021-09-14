package com.jiji.serial.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: gold
 * @date: 2020-12-10 14:58
 * @description:
 */
@Data
public class BaseVO {
    /**
     * 创建用户
     */
    private Long createUser;
    /**
     * 创建用户名称
     */
    private String createUserName;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改用户
     */
    private Long updateUser;

    /**
     * 修改用户名称
     */
    private String updateUserName;
    /**
     * 最后一次更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    /** 删除标识, 删除标识：N：正常 Y：删除 */
    private String deleteFlag;
}
