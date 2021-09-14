package com.jiji.serial.facade;


import com.jiji.serial.facade.dto.SerialNoReqDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: gold
 * @date:  2021-03-04 11:46
 * @description:
 */
@FeignClient(name = "serial-api",path = "serialApi")
public interface SerialNoBuilderFacade {
    /**
     * @param serialNoReqDTO
     * @return
     */
    @PostMapping(value = "/serialNo/genNewSerialNoByDto",consumes = "application/json")
    String genNewSerialNoByDto(@RequestBody SerialNoReqDTO serialNoReqDTO);
    /**
     * @param moduleType
     * @param businessCode
     * @return
     */
    @PostMapping(value = "/serialNo/genNewSerialNo",consumes = "application/json")
    String genNewSerialNo(@RequestParam("moduleType") Integer moduleType,@RequestParam("businessCode") String businessCode);
}
