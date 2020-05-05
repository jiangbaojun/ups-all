package com.brook.ups.service.extension;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.brook.ups.common.BaseService;
import com.brook.ups.common.ServiceInterface;
import com.brook.ups.common.constant.ConfigConstant;
import com.brook.ups.mapper.extension.PluginDataMapper;
import com.brook.ups.model.FormPlugin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PluginDataService extends BaseService implements ServiceInterface {
 
    @Resource
    private PluginDataMapper pluginDataMapper;

    public List<FormPlugin> selectList(Map<String, Object> params) {
        startPage(params);
        return pluginDataMapper.selectList(params);
    }

    @Override
    public int getNameCount(Map<String, Object> params) {
        return pluginDataMapper.getNameCount(params);
    }

    @Override
    public int add(Map<String, Object> params) {
        return pluginDataMapper.add(params);
    }

    /**
     * 保存插件，先删除插件数据，然后完整添加
     */
    @Transactional
    public String save(Map<String, Object> params) {
        String listStr = null;
        try {
            listStr = URLDecoder.decode(params.get("list").toString(), ConfigConstant.CODED_FORMAT);
        } catch (UnsupportedEncodingException e) {
            return "无法解析需要保存的插件数据";
        }
        List<Map<String, String>> list = JSON.parseObject(listStr, new TypeReference<List<Map<String, String>>>(){});
        for(Map<String, String> map:list){
            map.put("id", getDataId());
            if(map.get("plugDataKey")==null ||"".equals(map.get("plugDataKey"))){
                map.put("plugDataKey", new Date().getTime()+"");
            }
        }
        params.put("list",list);
        //先删除当前插件所有数据项
        delete(params);
        if(list==null || list.size()<1){
            //没有需要添加的数据，直接返回成功
            return null;
        }
        //添加完整插件数据
        int j = add(params);
        if(j>0){
            return null;
        }else{
            return "保存插件数据失败";
        }
    }

    @Override
    public int update(Map<String, Object> params) {
        return pluginDataMapper.update(params);
    }

    @Override
    public int delete(Map<String, Object> params) {
        return pluginDataMapper.delete(params);
    }
}