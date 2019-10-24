package com.brook.ups.controller.extension;

import com.brook.ups.common.BaseController;
import com.brook.ups.common.annotation.ApiVersion;
import com.brook.ups.common.enums.ExceptionCode;
import com.brook.ups.common.enums.Version;
import com.brook.ups.common.exception.UpsException;
import com.brook.ups.model.FormPlugin;
import com.brook.ups.service.extension.PluginDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 插件数据管理
 */
@Controller
@ApiVersion(Version.INNER)
@RequestMapping("/{version}/extension/plug/data")
public class PluginDataController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PluginDataService pluginDataService;


    /**
     * 获得插件数据列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public  List<FormPlugin> list(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        List<FormPlugin> list = pluginDataService.selectList(params);
        return list;
    }

    /**
     * 保存插件数据
     */
    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        if(params.get("plugId")==null){
            return this.failMessage("无法获得您要配置数据的插件！");
        }
        if(params.get("list")==null){
            return this.failMessage("没有需要配置的插件数据！");
        }
        try {
            String errMsg = pluginDataService.save(params);
            if (errMsg==null) {
                return this.successMessage("保存成功！");
            } else {
                return this.failMessage("保存失败,"+errMsg);
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.PD0101,"保存插件数据失败，"+e.getMessage(),e);
        }
    }
}