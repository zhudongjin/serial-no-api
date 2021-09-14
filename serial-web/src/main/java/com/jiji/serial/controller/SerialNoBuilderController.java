package com.jiji.serial.controller;

import com.jiji.common.utils.DateHelper;
import com.jiji.serial.facade.SerialNoBuilderFacade;
import com.jiji.serial.facade.dto.SerialNoReqDTO;
import com.jiji.serial.service.SerialNoBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: gold
 * @date: 2021-03-04 11:48
 * @description:
 */
@RestController
public class SerialNoBuilderController implements SerialNoBuilderFacade {

    @Autowired
    SerialNoBuilder serialNoBuilder;

    @Override
    public String genNewSerialNoByDto(SerialNoReqDTO dto) {
        if(null == dto.getModuleType() || 0 == dto.getModuleType()){
            dto.setModuleType(0);
        }
        if(StringUtils.isBlank(dto.getBusinessCode())){
            dto.setBusinessCode(DateHelper.getDateToStr(new Date(),DateHelper.YEAR_MONTH_DAY_FORMAT));
        }
        return serialNoBuilder.genNewSerialNo(dto.getModuleType(),dto.getBusinessCode());
    }

    @Override
    public String genNewSerialNo(Integer moduleType, String businessCode) {
        SerialNoReqDTO dto = new SerialNoReqDTO();
        dto.setModuleType(moduleType);
        dto.setBusinessCode(businessCode);
        return genNewSerialNoByDto(dto);
    }
}
