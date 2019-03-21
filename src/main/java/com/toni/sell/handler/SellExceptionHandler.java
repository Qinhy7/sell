package com.toni.sell.handler;

import com.toni.sell.config.ProjectUrlConfig;
import com.toni.sell.exception.SellException;
import com.toni.sell.exception.SellerAuthorizeException;
import com.toni.sell.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ：qinhy
 * @date ：Created in 2019/3/20 0020 10:20
 * 拦截异常处理类
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常，指定异常的类型
    //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        // 跳转到微信登录页面
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    // 使用@ResponseStatus 修改http返回的状态码
    @ResponseBody
    @ExceptionHandler(value = SellException.class)
    public Object handlerSellException(SellException sellException){
        return ResultVOUtils.error(sellException.getCode(),sellException.getMessage());
    }

}
