package com.brook.ups.controller.basic;

import com.brook.ups.common.BaseController;
import com.brook.ups.common.annotation.ApiVersion;
import com.brook.ups.common.enums.Version;
import com.brook.ups.model.User;
import com.brook.ups.service.basic.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@ApiVersion(Version.INNER)
@RequestMapping("/{version}/basic/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;


    /**
     * 获得应用列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String page_size, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<User> list = userService.selectList(params);
        return getResultMap(list);
    }

}