package com.brook.ups.controller.system;

import com.brook.ups.common.BaseController;
import com.brook.ups.common.annotation.ApiVersion;
import com.brook.ups.common.enums.ExceptionCode;
import com.brook.ups.common.enums.Version;
import com.brook.ups.common.exception.UpsException;
import com.brook.ups.model.Application;
import com.brook.ups.service.system.ApplicationService;
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
import java.util.UUID;

/**
 * 应用管理
 */
@Controller
@ApiVersion(Version.INNER)
@RequestMapping("/{version}/system/application")
public class ApplicationController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationService applicationService;


    /**
     * 获得应用列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        List<Application> list = applicationService.selectList(params);
        return getResultMap(list);
    }

    /**
     * 根据条件获得应用有效列表
     */
    @RequestMapping("/getApplications")
    @ResponseBody
    public Map<String, Object> getApplications(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        List<Application> list = applicationService.getApplications(params);
        return getResultMap(list);
    }
    /**
     * 添加、编辑应用
     */
    @RequestMapping("/addOrEdit")
    @ResponseBody
    public Map<String, Object> addOrEdit(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> params = this.getParameterMap(request);
        if("1".equals(params.get("isAdd"))){
            resultMap = add(request, response);
        }else{
            resultMap = update(request, response);
        }
        return resultMap;
    }

    /**
     * 添加应用
     */
    private Map<String, Object> add(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        //生成应用id
        params.put("id", UUID.randomUUID().toString().replaceAll("-",""));
        try {
            int num = applicationService.getNameCount(params);
            if(num>0){
                return this.failMessage("已存在该应用名称，请重使用其他名称！");
            }
            int i = applicationService.add(params);
            if (i > 0) {
                return this.successMessage("添加成功！");
            } else {
                return this.failMessage("添加失败！");
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.A00101,"添加应用失败，"+e.getMessage(),e);
        }
    }
    /**
     * 修改应用
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        try {
            int i = applicationService.update(params);
            if (i > 0) {
                return this.successMessage("编辑成功！");
            } else {
                return this.failMessage("编辑失败！");
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.A00201,"编辑应用失败，"+e.getMessage(),e);
        }
    }
    /**
     * 删除应用
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        try {
            int i = applicationService.delete(params);
            if (i > 0) {
                return this.successMessage("删除成功！");
            } else {
                return this.failMessage("删除失败！");
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.A00301,"删除应用失败，"+e.getMessage(),e);
        }
    }

}