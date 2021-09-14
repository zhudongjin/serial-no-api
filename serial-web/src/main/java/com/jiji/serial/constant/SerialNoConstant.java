package com.jiji.serial.constant;

/**
 * @Auther: gold
 * @Date: 2020/08/21/10:58
 * @Description:
 */

public class SerialNoConstant {

    /**
     * 禁止实例化，实际上用接口更好
     */
    private SerialNoConstant(){}
    /***
     * 公共默认值 N
     */
    public static final String DEFAULT_N = "N";
    /***
     * 公共默认值 N
     */
    public static final String DEFAULT_Y = "Y";
    /***
     * 公共默认值 N
     */
    public static final Integer DEFAULT_AMOUNT = 0;

    public static final String WOLF_RBAC_TOKEN = "x-rbac-token";

    public static final Long MAX_SERIAL_NO = Long.MAX_VALUE;
    /** 系统用户id,用来给不能确定创建用户的对象设置创建者 */
    public static final Long SYSTEM_CREATE_USER = 0L;
    /** 机构编号计数器id */
    public static final String SYSTEM_SERI_COUNT_CODE = "system.org.no";
    /** . **/
    public static final String MARK_POINT = ".";
    /** 机构号支持的最大值 */
    public static final Long MAX_ORG_SERI = 9999999L;
    /** 系统平台编号 */
    public static final String CONF_SYSTEM_PLATFORM_CODE = "system.platform.code";
    /** 最大长度code */
    public static final int MAX_CODE_LENGTH = 8;
    /** 最小长度code */
    public static final int MIN_CODE_LENGTH = 4;
}
