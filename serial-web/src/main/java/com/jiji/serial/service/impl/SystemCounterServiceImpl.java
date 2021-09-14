package com.jiji.serial.service.impl;

import com.jiji.common.utils.BeanConvertHelper;
import com.jiji.serial.constant.SerialNoConstant;
import com.jiji.serial.dto.SystemCounterDTO;
import com.jiji.serial.entity.SystemCounter;
import com.jiji.serial.mapper.SystemCounterMapper;
import com.jiji.serial.service.SystemCounterService;
import com.jiji.serial.vo.SystemCounterVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 计数器表:
 *
 * @author code-generator
 * @version 1.0.0 2017-06-30 10:52:29
 */
@Service
public class SystemCounterServiceImpl implements SystemCounterService {

    @Resource
    private SystemCounterMapper systemCounterMapper;

    @Override
    public SystemCounterVO queryById(String counterId) {
        SystemCounter entity = this.systemCounterMapper.selectById(counterId);
        return BeanConvertHelper.copyProperties(entity, SystemCounterVO.class);
    }

    @Override
    public boolean insert(SystemCounterDTO systemCounter) {
        SystemCounter entity = BeanConvertHelper.copyProperties(systemCounter, SystemCounter.class);
        return this.systemCounterMapper.insert(entity) == 1;
    }

    @Override
    public boolean updateById(SystemCounterDTO systemCounter) {
        SystemCounter entity = BeanConvertHelper.copyProperties(systemCounter, SystemCounter.class);
        return this.systemCounterMapper.updateById(entity) == 1;
    }

    @Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public SystemCounterVO incr(String counterId, String counterName, Long value) {
		SystemCounter systemCounter = this.systemCounterMapper.lockById(counterId); //查询加锁 for update
		value = value == null ? 1 : value;
		if (systemCounter != null) {
			Long currentValue = systemCounter.getValue() + value;
			// 当存在最大值限制，且当前值超过最大值的时候，当前值需要恢复为初始值，即value
			if (null != systemCounter.getMaxValue() && currentValue > systemCounter.getMaxValue()) {
				systemCounter.setValue(value);
			} else {
				systemCounter.setValue(currentValue);
			}
			systemCounter.setUpdateTime(new Date());
			this.systemCounterMapper.updateById(systemCounter);
		} else {
			systemCounter = new SystemCounter();
			systemCounter.setCounterId(counterId);
			systemCounter.setCounterName(counterName);
			systemCounter.setValue(Long.parseLong(value.toString()));
			systemCounter.setMaxValue(SerialNoConstant.MAX_SERIAL_NO);
			systemCounter.setCreateUser(SerialNoConstant.SYSTEM_CREATE_USER);
			systemCounter.setCreateTime(new Date());
			systemCounter.setUpdateTime(new Date());
			this.systemCounterMapper.insert(systemCounter);
		}
		return BeanConvertHelper.copyProperties(systemCounter, SystemCounterVO.class);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void modify(String counterId, String counterName, Long value) {
		SystemCounter systemCounter = this.systemCounterMapper.lockById(counterId);  //查询加锁 for update
		value = value == null ? 0 : value;
		if (systemCounter != null && !value.equals(systemCounter.getValue()) ) {
			systemCounter.setValue(value);
			systemCounter.setUpdateTime(new Date());
            this.systemCounterMapper.updateById(systemCounter);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public SystemCounterVO incrWithInitCounter(SystemCounterDTO counterCond, Integer interval) {
		SystemCounter counterModle = this.systemCounterMapper.lockById(counterCond.getCounterId());  //查询加锁 for update
		interval = interval == null ? 0 : interval;

		if (counterModle != null) {
			Long currentValue = counterModle.getValue() + interval;
			// 当存在最大值限制，且当前值超过最大值的时候，当前值需要恢复为初始值，即counterCond.getValue()
			if (null != counterModle.getMaxValue() && (currentValue > counterModle.getMaxValue() || currentValue < 0)) {
				counterModle.setValue(counterCond.getValue());
			} else {
				counterModle.setValue(currentValue);
			}

			counterModle.setUpdateTime(new Date());
			this.systemCounterMapper.updateById(counterModle);
		} else {
			counterModle = new SystemCounter();
			counterModle.setCounterId(counterCond.getCounterId());
			counterModle.setCounterName(counterCond.getCounterName());
			counterModle.setValue(counterCond.getValue());
			counterModle.setMaxValue(counterCond.getMaxValue());
			counterModle.setCreateUser(SerialNoConstant.SYSTEM_CREATE_USER);
			counterModle.setCreateTime(new Date());
			counterModle.setUpdateTime(new Date());
			this.systemCounterMapper.insert(counterModle);
		}

		return BeanConvertHelper.copyProperties(counterModle, SystemCounterVO.class);
	}

	@Override
	@Transactional
	public SystemCounterVO genDailyCounter(String counterId, String counterName, Long value) {
		SystemCounter counterModle = this.systemCounterMapper.lockById(counterId);  //查询加锁 for update
		value = value == null ? 1 : value;

		Date date = new Date();

		// 如果counter不存在，新增一个
		if (counterModle == null) {
			counterModle = new SystemCounter();
			counterModle.setCounterId(counterId);
			counterModle.setCounterName(counterName);
			counterModle.setValue(value);
			counterModle.setUpdateTime(date);
			counterModle.setCreateTime(date);
			counterModle.setCreateUser(SerialNoConstant.SYSTEM_CREATE_USER);
			counterModle.setMaxValue(SerialNoConstant.MAX_SERIAL_NO);
			this.systemCounterMapper.insert(counterModle);
		}
		// 如果当前时间比counter的更新日期比小,counter递增，但不更新updateTime [多机器时间不一致]
		else if (date.getTime() <= counterModle.getUpdateTime().getTime()) {
			counterModle.setValue(counterModle.getValue() + value);
			this.systemCounterMapper.updateById(counterModle);
		}
		// 如果跨天了，重置counter
		else if ((date.getTime() / 86400000) > (counterModle.getUpdateTime().getTime() / 86400000)) {
			counterModle.setUpdateTime(date);
			counterModle.setValue(value);
			this.systemCounterMapper.updateById(counterModle);
		}
		// 没有跨天，counter递增
		else {
			counterModle.setUpdateTime(date);
			counterModle.setValue(counterModle.getValue() + value);
			this.systemCounterMapper.updateById(counterModle);
		}

		return BeanConvertHelper.copyProperties(counterModle, SystemCounterVO.class);
	}
}