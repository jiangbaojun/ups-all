package com.brook.ups.service.extension;

import com.brook.ups.common.BaseService;
import com.brook.ups.common.ServiceInterface;
import com.brook.ups.mapper.extension.PluginDataMapper;
import com.brook.ups.mapper.extension.PluginMapper;
import com.brook.ups.model.FormPlugin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PluginService extends BaseService implements ServiceInterface {
 
    @Resource
    private PluginMapper pluginMapper;

    @Resource
    private PluginDataMapper pluginDataMapper;

    @Override
    public int getNameCount(Map<String, Object> params) {
        return pluginMapper.getNameCount(params);
    }
    @Override
    public int add(Map<String, Object> params) {
        return pluginMapper.add(params);
    }
    @Override
    public int update(Map<String, Object> params) {
        return pluginMapper.update(params);
    }
    @Override
    public int delete(Map<String, Object> params) {
        return pluginMapper.delete(params);
    }

    /**
     * 查询插件列表
     */
    public List<FormPlugin> selectList(Map<String, Object> params) {
        startPage(params);
        List<FormPlugin> list = pluginMapper.selectList(params);
        return list;
    }

    /**
     * 添加插件
     */
    @Transactional
    public String addPlug(Map<String, Object> params) {
        //生成插件id
        params.put("id", getDataId());
        int num = getNameCount(params);
        if(num>0){
            return "已存在该插件名称，请重使用其他名称！";
        }
        int i = add(params);
        if (i > 0) {
            return null;
        } else {
            return "添加插件失败！";
        }
    }
    /**
     * 编辑插件
     */
    @Transactional
    public String updatePlug(Map<String, Object> params) {
        params.put("plugId",params.get("id"));
        //判断引用情况
        String ref= getPlugInRef(params);
        if(ref!=null){
            return "该插件已被引用，不可以编辑。引用属性如下："+ref;
        }
        //编辑插件
        int i = update(params);
        if (i > 0) {
            return null;
        }else{
            return "编辑插件失败";
        }
    }
    /**
     * 禁用插件
     */
    @Transactional
    public String disablePlug(Map<String, Object> params) {
        params.put("plugId",params.get("id"));
        //判断引用情况
        String ref= getPlugInRef(params);
        if(ref!=null){
            return "该插件已被引用，不可以禁用。引用属性如下："+ref;
        }
        //编辑插件
        params.put("isAvailable","0");
        int i = update(params);
        if (i > 0) {
            return null;
        }else{
            return "禁用插件失败";
        }
    }
    /**
     * 启用插件
     */
    @Transactional
    public String enablePlug(Map<String, Object> params) {
        //编辑插件
        params.put("isAvailable","1");
        int i = update(params);
        if (i > 0) {
            return null;
        }else{
            return "启用插件失败";
        }
    }

    /**
     * 删除插件，同时删除插件数据
     */
    @Transactional
    public String deletePlug(Map<String, Object> params) {
        params.put("plugId",params.get("id"));
        //判断引用情况
        String ref= getPlugInRef(params);
        if(ref!=null){
            return "该插件已被引用，不可以删除。引用属性如下："+ref;
        }
        //删除插件
        int i = delete(params);
        if (i > 0) {
            //删除插件数据
            pluginDataMapper.delete(params);
            return null;
        }else{
            return "删除插件失败";
        }
    }

    /**
     * 根据插件id获得插件信息
     */
    public FormPlugin getPlugInById(Map<String, Object> params) {
        return pluginMapper.getPlugInById(params);
    }

    /**
     * 获得插件引用情况
     */
    private String getPlugInRef(Map<String, Object> params){
        List<Map<String,Object>> list = pluginMapper.getPlugInRef(params);
        boolean flag= false;
        if(list!=null && list.size()>0){
            StringBuffer bufr = new StringBuffer();
            for(Map<String,Object> map:list){
                String attrName = (String)map.get("attrName");
                bufr.append(attrName+",");
            }
            if(bufr.length()>0){
                String str = bufr.toString();
                return str.substring(0,str.length()-1);
            }
        }
        return null;
    }
}