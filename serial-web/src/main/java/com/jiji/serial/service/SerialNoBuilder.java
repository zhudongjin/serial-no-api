package com.jiji.serial.service;

import com.jiji.common.exception.BusinessException;
import com.jiji.common.exception.CommonErrorCodes;
import com.jiji.common.redis.RedisUtil;
import com.jiji.serial.constant.SerialNoConstant;
import com.jiji.serial.vo.SystemCounterVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


/**
 * 业务编号统一生成器
 */
@Service

public class SerialNoBuilder {

    private final static Logger logger = LoggerFactory.getLogger(SerialNoBuilder.class);

    @Resource
    SystemCounterService systemCounterService;
    @Resource
    RedisUtil redisUtil;

    //编号起始自增序列位数
    private int serialNoLength = 7;

    /***
     *
     * @param moduleType  枚举类型
     * @param businessCode 自定义编号不能大于8个字符小于4个字符
     * @return
     */
    @Transactional(rollbackFor = Exception.class )
    public String genNewSerialNo(Integer moduleType,String businessCode) {
        if (StringUtils.isBlank(businessCode) || null == moduleType || (businessCode.length() < SerialNoConstant.MIN_CODE_LENGTH && businessCode.length() > SerialNoConstant.MAX_CODE_LENGTH)) {
            logger.error("平台编码格式错误：moduleType：{} ：businessCode：{}",moduleType,businessCode);
            throw new BusinessException(CommonErrorCodes.SERVER_BUSY.toErrorCode());
        }
        SystemCounterVO counter = this.systemCounterService.incr(SerialNoConstant.SYSTEM_SERI_COUNT_CODE + SerialNoConstant.MARK_POINT + moduleType, "机构号计数器" + moduleType, 1L);
        //提前1000个序列通知叮叮更换序列
        if (counter == null || counter.getValue() > (SerialNoConstant.MAX_ORG_SERI - 1000L)) {
            logger.error("机构号序列即将达到最大值["+SerialNoConstant.MAX_ORG_SERI+"]，请尽快调整系统参数["+SerialNoConstant.MAX_ORG_SERI+"]");
        }
        //生成失败告警
        if (counter == null || counter.getValue() > SerialNoConstant.MAX_ORG_SERI) {
            logger.error("机构号序列超过最大值["+SerialNoConstant.MAX_ORG_SERI+"]，请立即调整系统参数["+SerialNoConstant.CONF_SYSTEM_PLATFORM_CODE+"]");
            throw new BusinessException(CommonErrorCodes.SERVER_BUSY.toErrorCode());
        }
        String serialPart = StringUtils.leftPad(String.valueOf(counter.getValue()), serialNoLength, "0");
        return businessCode + moduleType + serialPart + System.currentTimeMillis();
    }
}
