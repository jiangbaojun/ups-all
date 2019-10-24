package com.brook.ups.controller.user;

import com.brook.ups.common.BaseController;
import com.brook.ups.model.user.Tuser;
import com.brook.ups.service.user.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class TUserController extends BaseController {
	
	@Autowired
	private TUserService tUserService;

    /**
     * 不分页查询
     * @param request
     * @return
     */
    @RequestMapping("/all")
    @ResponseBody
    public List<Tuser> test1(HttpServletRequest request){
        return tUserService.findByUsersAll();
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Map<String, Object> test2(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);

        //路径参数version
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String version1 = (String)pathVariables.get("version");

        List<Tuser> list = tUserService.findByUsersPage(params).getList();
        return getResultMap(list);
    }

}