package com.brook.ups.controller.basic;

import com.brook.ups.common.BaseController;
import com.brook.ups.common.annotation.ApiVersion;
import com.brook.ups.common.enums.Version;
import com.brook.ups.model.user.Tuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 资源管理
 */
@Controller
@ApiVersion(Version.INNER)
@RequestMapping("/{version}/basic/resource")
public class ResourceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Autowired
//	private ApplicationService applicationService;


    /**
     * 获得应用列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, String[]> params = request.getParameterMap();
        int limit = request.getParameter("limit")==null?100:Integer.parseInt(request.getParameter("limit"));
        int offset = request.getParameter("offset")==null?0:Integer.parseInt(request.getParameter("offset"));

        List<Tuser> list = new ArrayList<Tuser>();
        for(int i=offset;i<offset+limit;i++) {
//            Tuser u = new Tuser(""+i, "张三"+i, "18812341234", "研发人员", "研发部", new Date(), "181"+i);
            Tuser u = new Tuser(""+i, "张三"+i, i%90, new Date());
            list.add(u);
        }
        resultMap.put("rows", list);
        resultMap.put("total", "100");
        return resultMap;
    }

}