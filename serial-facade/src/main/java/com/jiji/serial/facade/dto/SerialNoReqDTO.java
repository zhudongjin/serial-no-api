package com.jiji.serial.facade.dto;

import lombok.Data;

/**
 * @author zhudo
 * @version 1.0
 * @description: TODO
 * @date 2021/9/14 13:01
 */
@Data
public class SerialNoReqDTO {

    /** 模块类型枚举定义 */
    private Integer moduleType;
    /** 业务编码 */
    private String businessCode;
}
