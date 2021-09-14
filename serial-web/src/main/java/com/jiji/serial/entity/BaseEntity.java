package com.jiji.serial.entity;

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
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 界面查询开始时间
     */
    private Date startTime;
    /**
     * 界面查询结束时间
     */
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
    private Date createTime;

    /**
     * 创建用户
     */
    private Long updateUser;

    /**
     * 修改用户名称
     */
    private String updateUserName;
    /**
     * 最后一次更新时间
     */
    private Date updateTime;

    /**排序类型**/
    private String sortType;

    /**排序字段**/
    private String sortKey;

    /**排序字段**/
    private List<String> sortSql;
    /**模糊查询字段 Y标识模糊查询 N表示精确查询**/
    private String likeFlag;
    /** 删除标识, 删除标识：N：正常 Y：删除 */
    private String deleteFlag;

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
}
