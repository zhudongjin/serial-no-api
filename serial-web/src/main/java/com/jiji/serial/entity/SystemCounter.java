package com.jiji.serial.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 计数器表:
 *
 * @author code-generator
 * @version 1.0.0 2017-06-30 10:52:29
 */
@Data
public class SystemCounter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 计数器id */
    @TableId
    private String counterId;
    /** 计数器名称 */
    private String counterName;
    /** 当前值 */
    private Long value;
    /** 最大值 */
    private Long maxValue;
    /** 备注 */
    private String remark;
}
