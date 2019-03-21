package com.toni.sell.utils;

import com.toni.sell.VO.ProductVO;
import com.toni.sell.VO.ResultVO;

import java.util.List;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/15 0015 19:02
 * @modified By：
 */
public class ResultVOUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setData(object);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        return resultVO;
    }

    public static ResultVO error(){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setMsg("失败");
        resultVO.setCode(1);
        return resultVO;
    }

    public static ResultVO error(List list){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setMsg("失败");
        resultVO.setCode(1);
        resultVO.setData(list);
        return resultVO;
    }

    public static ResultVO error(Integer code, String data){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(data);

        return resultVO;
    }


}
