package com.brook.ups.controller.system;

import com.brook.ups.common.BaseController;
import com.brook.ups.common.annotation.ApiVersion;
import com.brook.ups.common.enums.ExceptionCode;
import com.brook.ups.common.enums.Version;
import com.brook.ups.common.exception.UpsException;
import com.brook.ups.model.Account;
import com.brook.ups.model.token.AccountToken;
import com.brook.ups.service.system.AccountService;
import com.brook.ups.utils.encryption.TokenEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 账户管理
 */
@Controller
@ApiVersion(Version.INNER)
@RequestMapping("/{version}/system/account")
public class AccountController extends BaseController {
	
	@Autowired
	private AccountService accountService;

    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        if(params.get("acName")==null){
           return this.failLogin("用户名为空！");
        }
        if(params.get("password")==null){
            return this.failLogin("密码为空！");
        }else{
            params.put("passwordHex", DigestUtils.md5DigestAsHex(params.get("password").toString().getBytes()));
        }
        String token = "";
        List<Account> list = accountService.getAccounts(params);
        Account ac = null;
        if(list!=null && list.size()==1){
            ac = list.get(0);
            if("0".equals(ac.getIsAvailable())){
                return this.failLogin("该账户已被禁用！");
            }
            token = TokenEncryptUtil.generateInnerToken(ac);
            if(token==null){
                return this.failLogin("登录失败，无法为您分发令牌！");
            }
        }else{
            return this.failLogin("用户或密码错误！");
        }
        return this.successLogin(token, ac);
    }

    /**
     * 根据id获得账户
     */
    @RequestMapping("/getAccountById")
    @ResponseBody
    public Account getAccountById(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> params = this.getParameterMap(request);
        Account account = accountService.getAccountById(params);
        //不返回密码
        account.setPassword("");
        return account;
    }

    /**
     * 根据token获得账户
     */
    @RequestMapping("/getAccountByToken")
    @ResponseBody
    public Account getAccountByToken(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        AccountToken at = null;
        try {
            at = TokenEncryptUtil.parse((String)params.get("token"));
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.AU0001,"解析/认证token失败",e);
        }
        if(at==null || at.getAcId()==null || at.getAppId()==null){
            return null;
        }
        params.put("id", at.getAcId());
        params.put("appId",at.getAppId());
        Account account = accountService.getAccountById(params);
        if(account==null){
            throw new UpsException(ExceptionCode.AU0003,"无法获得账户信息");
        }
        //不向前台返回密码
        account.setPassword("");
        return account;
    }
    /**
     * 登出
     */
    @RequestMapping("/logout")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response){

    }
    /**
     * 获得账户列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = this.getParameterMap(request);
        List<Account> list = accountService.selectList(params);
        return getResultMap(list);
    }

    /**
     * 添加、编辑账户
     */
    @RequestMapping("/addOrEdit")
    @ResponseBody
    public Map<String, Object> addOrEdit(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> params = this.getParameterMap(request);
        //处理密码加密
        if(params.get("password")!=null){
            params.put("passwordHex", DigestUtils.md5DigestAsHex(params.get("password").toString().getBytes()));
        }
        if("1".equals(params.get("isAdd"))){
            //默认密码123456
            if(params.get("password")==null){
                params.put("passwordHex", DigestUtils.md5DigestAsHex("123456".getBytes()));
            }
            resultMap = add(request, response, params);
        }else{
            resultMap = update(request, response, params);
        }
        return resultMap;
    }

    /**
     * 添加账户
     */
    private Map<String, Object> add(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params){
        //生成应用id
        params.put("id", UUID.randomUUID().toString().replaceAll("-",""));
        try {
            //判断是否存在
            int num = accountService.getNameCount(params);
            if(num>0){
                return this.failMessage("已存在该账户名称，请重使用其他名称！");
            }
            int i = accountService.add(params);
            if (i > 0) {
                return this.successMessage("添加成功！");
            } else {
                return this.failMessage("添加失败！");
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.AC0101,"添加账户失败，"+e.getMessage(),e);
        }
    }
    /**
     * 修改账户
     */
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params){
        try {
            int i = accountService.update(params);
            if (i > 0) {
                return this.successMessage("编辑成功！");
            } else {
                return this.failMessage("编辑失败！");
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.AC0201,"编辑账户失败，"+e.getMessage(),e);
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
            int i = accountService.delete(params);
            if (i > 0) {
                return this.successMessage("删除成功！");
            } else {
                return this.failMessage("删除失败！");
            }
        } catch (Exception e) {
            throw new UpsException(ExceptionCode.AC0301,"删除账户失败，"+e.getMessage(),e);
        }
    }

}