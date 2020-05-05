package com.brook.ups.controller.api;

import com.brook.ups.common.BaseController;
import com.brook.ups.service.system.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 账户管理
 */

@Api(value = "业务系统", description = "缴费用户注册、下单支付服务API")
@Controller
@RequestMapping("/v1/system/account")
public class RoleController extends BaseController {
	
	@Autowired
	private AccountService accountService;

    @ApiOperation(value = "一个测试API", notes = "第一个测试API")
    @RequestMapping(value = "/test/{path}", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "path", value = "url上的数据", required = true, paramType = "path", dataType = "Long"),
        @ApiImplicitParam(name = "query", value = "query类型参数", required = true, paramType = "query", dataType = "String"),
        @ApiImplicitParam(name = "name", value = "form参数", required = true, paramType = "form", dataType = "String"),
        @ApiImplicitParam(name = "apiKey", value = "header中的数据", required = true, paramType = "header", dataType = "String")
    })
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        return params;
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "path", value = "url上的数据", required = true, paramType = "form", dataType = "Long")
    })
    @RequestMapping(value="/t1",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> t1(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        return params;
    }

}