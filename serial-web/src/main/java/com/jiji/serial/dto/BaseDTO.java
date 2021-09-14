package com.jiji.serial.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiji.common.constant.CommonsConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author gold.zhu
 * @version 1.0 2020-12-02
 */
@Data
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public BaseDTO() {

    }
    /**
     * 界面查询开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    /**
     * 界面查询结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
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
     * 当前查询用户
     */
    private Long queryUser;

    /** 页大小 */
    protected int pageSize=10;

    /** 当前页 */
    protected int currentPage=1;

    /**排序字段**/
    private List<String> sortSql;

    /** 1：支持模糊查询 **/
    private Integer isLike;

    /**
     * 最后一次更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    /** 删除标识, 删除标识：N：正常 Y：删除 */
    private String deleteFlag;
    /**模糊查询字段 Y标识模糊查询 N表示精确查询**/
    private String likeFlag;

    public Long getQueryUser() {
        return queryUser;
    }

    public void setQueryUser(Long queryUser) {
        this.queryUser = queryUser;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public BaseDTO(Long createUser, String createUserName) {
        this.createUser = createUser;
        this.createUserName = createUserName;
    }

    public void autoCompleteUser() {
        if (this.getCreateUser() == null) {
            this.setCreateUser(CommonsConstant.SYSTEM_CREATE_USER);
            this.setCreateUserName(CommonsConstant.SYSTEM_CREATE_USER_NAME);
        }
        if (this.getCreateTime() == null) {
            this.setCreateTime(new Date());
        }
        if (this.getUpdateUser() == null) {
            this.setUpdateUser(CommonsConstant.SYSTEM_CREATE_USER);
            this.setUpdateUserName(CommonsConstant.SYSTEM_CREATE_USER_NAME);
        }
        if (this.getUpdateTime() == null) {
            this.setUpdateTime(new Date());
        }
    }

    public void autoCompleteUpdateUser() {
        if (this.getUpdateUser() == null) {
            this.setUpdateUser(CommonsConstant.SYSTEM_CREATE_USER);
            this.setUpdateUserName(CommonsConstant.SYSTEM_CREATE_USER_NAME);
        }
        if (this.getUpdateTime() == null) {
            this.setUpdateTime(new Date());
        }
    }


}
