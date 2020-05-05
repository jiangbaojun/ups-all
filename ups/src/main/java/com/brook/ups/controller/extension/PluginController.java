package com.brook.ups.controller.extension;

import com.brook.ups.common.BaseController;
import com.brook.ups.common.annotation.ApiVersion;
import com.brook.ups.common.enums.ExceptionCode;
import com.brook.ups.common.enums.Version;
import com.brook.ups.common.exception.UpsException;
import com.brook.ups.model.FormPlugin;
import com.brook.ups.service.extension.PluginService;
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
 * 插件管理
 */
@Controller
@ApiVersion(Version.INNER)
@RequestMapping("/{version}/extension/plug")
public class PluginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PluginService pluginService;

    /**
     * 获得插件列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        List<FormPlugin> list = pluginService.selectList(params);
        return getResultMap(list);
    }

    /**
     * 获得插件列表
     */
    @RequestMapping("/getPlugInById")
    @ResponseBody
    public FormPlugin getPlugInById(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        FormPlugin formPlugin= pluginService.getPlugInById(params);
        return formPlugin;
    }

    /**
     * 添加、编辑插件
     */
    @RequestMapping("/addOrEdit")
    @ResponseBody
    public Map<String, Object> addOrEdit(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> params = this.getParameterMap(request);
        if("1".equals(params.get("isAdd"))){
            resultMap = add(request, response, params);
        }else{
            resultMap = update(request, response, params);
        }
        return resultMap;
    }

    /**
     * 添加插件
     */
    private Map<String, Object> add(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params){
        try {
            String errMsg = pluginService.addPlug(params);
            if (errMsg==null) {
                return this.successMessage("添加成功！");
            } else {
                return this.failMessage("添加失败,"+errMsg);
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.P00101,"添加插件失败，"+e.getMessage(),e);
        }
    }
    /**
     * 修改插件
     */
    private Map<String, Object> update(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params){
        try {
            String errMsg = pluginService.updatePlug(params);
            if (errMsg==null) {
                return this.successMessage("编辑成功！");
            } else {
                return this.failMessage("编辑失败,"+errMsg);
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.P00201,"编辑插件失败，"+e.getMessage(),e);
        }
    }
    /**
     * 禁用插件
     */
    @RequestMapping("/disable")
    @ResponseBody
    public Map<String, Object> disable(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        try {
            String errMsg = pluginService.disablePlug(params);
            if (errMsg==null) {
                return this.successMessage("禁用成功！");
            } else {
                return this.failMessage("禁用失败,"+errMsg);
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.P00201,"禁用插件失败，"+e.getMessage(),e);
        }
    }
    /**
     * 启用插件
     */
    @RequestMapping("/enable")
    @ResponseBody
    public Map<String, Object> enable(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        try {
            String errMsg = pluginService.enablePlug(params);
            if (errMsg==null) {
                return this.successMessage("启用成功！");
            } else {
                return this.failMessage("启用失败,"+errMsg);
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.P00201,"启用插件失败，"+e.getMessage(),e);
        }
    }

    /**
     * 删除插件
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        try {
            String errMsg = pluginService.deletePlug(params);
            if (errMsg==null) {
                return this.successMessage("删除成功！");
            } else {
                return this.failMessage("删除失败,"+errMsg);
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.P00301,"删除插件失败，"+e.getMessage(),e);
        }
    }
}