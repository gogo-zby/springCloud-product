package com.hmily.product.utils;

import com.hmily.product.enums.ResultStatusEnum;
import com.hmily.product.vo.ResultVO;

public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(ResultStatusEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultStatusEnum.SUCCESS.getMessage());
        return resultVO;
    }
}
