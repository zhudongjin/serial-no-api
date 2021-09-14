package com.jiji.serial.service;


import com.jiji.serial.dto.SystemCounterDTO;
import com.jiji.serial.vo.SystemCounterVO;

/**
 * 计数器表: 服务接口
 *
 * @author code-generator
 * @version 1.0.0 2017-06-30 10:52:29
 */
public interface SystemCounterService {


    /**
     * 根据id查询
     *
     * @param counterId 计数器id
     * @return 计数器表
     */
    SystemCounterVO queryById(String counterId);

    /**
     * 插入数据
     *
     * @param systemCounter 计数器表
     * @return true/false
     */
    boolean insert(SystemCounterDTO systemCounter);

    /**
     * 根据主键更新
     *
     * @param systemCounter 计数器表
     * @return true/false
     */
    boolean updateById(SystemCounterDTO systemCounter);
    
    /**
     * 计数器根据指定步长自增,返回自增后的对象
     * @param counterId
     * @param counterName
     * @return
     */
    SystemCounterVO incr(String counterId, String counterName, Long value);
    
    /**
	 *  修改计算器起始值
	 *
	 * @param [counterId, counterName, value]
	 * @return void
	 **/
	void modify(String counterId, String counterName, Long value);

    /**
     *  当counterCond的counterId对应的数据不存在的时候，根据counterDto的值创建counter记录
     * <br/>
     * <br/>
     * 当counterCond的counterId对应的数据存在的时候，currval增加interval
     * <pre>
     *  	<li>如果增加后的值大于最大值，则初始化为conterDto对象的value值</li>
     *  	<li>其他情况，currval正常增加interval获取新的value</li>
     * </pre>
     * @param counterCond
     * @param interval 当前递增的值
     * @return
     */

    SystemCounterVO incrWithInitCounter(SystemCounterDTO counterCond, Integer interval);

    /**
     * 按天刷新的计数器
     * @param counterId
     * @param counterName
     * @param value
     * @return
     */
    SystemCounterVO genDailyCounter(String counterId, String counterName, Long value);
}