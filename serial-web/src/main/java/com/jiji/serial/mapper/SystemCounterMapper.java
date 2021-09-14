package com.jiji.serial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiji.serial.entity.SystemCounter;
import org.apache.ibatis.annotations.Mapper;

/**
 * 计数器表:
 *
 * @author code-generator
 * @version 1.0.0 2017-06-30 10:52:29
 */
@Mapper
public interface SystemCounterMapper extends BaseMapper<SystemCounter> {

    /**
     * 加锁查询
     * @param counterId
     * @return
     */
    SystemCounter lockById(String counterId);
}