package com.brook.ups.common;

import com.brook.ups.common.constant.ConfigConstant;
import com.brook.ups.common.enums.MsgStateEnum;
import com.brook.ups.model.Account;
import com.brook.ups.model.exception.ErrorMessage;
import com.brook.ups.model.token.AccountToken;
import com.brook.ups.utils.encryption.TokenEncryptUtil;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础controller
 */
public abstract class BaseController {

    /**
     * 操作成功提示信息
     * @param msg 提示消息内容
     */
	public Map successMessage(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		map.put("flag", MsgStateEnum.SUCCESS.getValue());
		return map;
	}

    /**
     * 操作失败提示信息
     * @param msg 提示消息内容
     */
	public Map failMessage(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		map.put("flag", MsgStateEnum.ERROR.getValue());
		return map;
	}

    /**
     * 操作失败,出现异常提示信息
     * @param em 异常消息实体
     */
	public Map exceptionMessage(ErrorMessage em) {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", em);
		map.put("flag", MsgStateEnum.EXCEPTION.getValue());
		return map;
	}

    /**
     * 登录成功返回token信息
     * @param token 登录成功后的token
     * @param ac
     */
	public Map successLogin(String token, Account ac) {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		map.put("account", ac);
		map.put("result", MsgStateEnum.SUCCESS.getValue());
		return map;
	}

    /**
     * 登录失败返回提示信息
     * @param msg 提示消息内容
     */
	public Map failLogin(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		map.put("result", MsgStateEnum.ERROR.getValue());
		return map;
	}

	/**
     * 获得处理后的前台请求参数
	 */
	public Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, Object> params= ParameterHolder.getFormParameter(request);
		//自动添加optDate时间
        params.put("optDate", new Date());
        //解析token，获得当前用户所属appid
        try {
            AccountToken at = TokenEncryptUtil.parse(request.getHeader(ConfigConstant.X_TOKEN));
            if(at!=null){
                params.put(ConfigConstant.ACCOUNTID, at.getAcId());
                params.put(ConfigConstant.ACCOUNTINFO, at);
                if(!ConfigConstant.SYSTEMALL.equals(at.getAppId())){
                    params.put(ConfigConstant.ACCOUNTAPPID, at.getAppId());
                }
            }
        } catch (Exception e) {}
        return params;
	}

    /**
     * 获得前台所需要的结果集
     * @param list 结果数据
     */
	public Map<String, Object> getResultMap(List list) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
        PageInfo page = null;
		if(list instanceof PageInfo){
            page = (PageInfo) list;
        }else{
            page = new PageInfo(list);
        }
		resultMap.put(ConfigConstant.RESULT_LIST_KEY, page.getList());
		resultMap.put(ConfigConstant.PAGE_PAGE_KEY, page.getPageNum());
		resultMap.put(ConfigConstant.PAGE_SIZE_KEY, page.getSize());
		resultMap.put(ConfigConstant.PAGE_TOTAL_KEY, page.getTotal());
		return resultMap;
	}

    /**
     * 获得前台所需要的结果集,手动指定total和page
     * @param list 数据
     * @param page 页码
     * @param total 总数据条数
     * @return
     */
	public Map<String, Object> getResultMap(List list,Object page,Object total) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ConfigConstant.RESULT_LIST_KEY, list);
		if(page!=null && total!=null) {
			if(page instanceof Integer) {
				int myPage = (int)page;
				resultMap.put(ConfigConstant.PAGE_PAGE_KEY, myPage==0?1:myPage);
			}
			if(total instanceof Integer) {
				int myTotal= (int)total;
				resultMap.put(ConfigConstant.PAGE_TOTAL_KEY, myTotal);
			}
		}
		return resultMap;
	}

    /**
     * 获得用户名
     */
	public String getUserDm(){
        return null;
	}
	/**
	 * 获得用户名ID
	 */
	public String getUserId(){
        return null;
	}
	
	/**
	 * 获得用户信息
	 */
	public Map<String,Object> getUserInfo(){
        return null;
	}

}