package com.wangpf.springbootrequestAndResponse.aspect;

import com.wangyin.concrete.client.helper.*;
import com.wangyin.concrete.client.model.ConfigChangeModel;
import com.wangyin.concrete.client.model.ConfigFileChangeEvent;
import com.wangyin.concrete.client.model.KVConfigFileChangeEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WyConfigUtils {
    /**
     *配合文件列表
     * */
   private List<String> pathList;
    /**
     * 此参数用于外部调用，统一出口,兼容UCC配置，可以通过配置调整
     */
    public static final ConcurrentHashMap<String ,String> configMap=new ConcurrentHashMap<>();

    /**
     * 初始化方法，循环配置文件，进行监听
     */
    private void init() {
        try {
            for(String path:pathList){
                if(path.endsWith(".properties")) {
                    propertiesListener(path);
                }else{
                    fileListener(path);
                }
            }

        } catch (Exception e) {
            System.out.println();
        }
    }

    private void fileListener(String path){
        ConfigFileService configFileService = new ConfigFileService("ccs_public", path);
        String fileName = path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
        configMap.put(fileName,configFileService.getContent());

        // 监听数据变更
        configFileService.addChangeListener(new ConfigFileChangeListener() {
            @Override
            public void configFileChangeEvent(ConfigFileChangeEvent configFileChangeEvent) {
                configMap.put(fileName,configFileChangeEvent.getNewContent());
            }
        });
    }

    /**
     * 初始化数据，监听对应的目录
     * @param path 监听目录
     * @throws Exception 异常
     */
    private void propertiesListener(String path) throws Exception {
        //配置中心，属性配置服务服务类，
        PropertiesConfigService propertiesConfigService = new PropertiesConfigService("ccs_public", path);
        //将所有数据保存到configMap中
        for(String propertyName:propertiesConfigService.getPropertyNames()){
            String value=propertiesConfigService.getProperty(propertyName);
            configMap.put(propertyName,value);
        }

        // 监听数据变更
        propertiesConfigService.addKVConfigChangeListener(new KVConfigFileChangeListener(){
            @Override
            public void kvConfigFileChangeEvent(KVConfigFileChangeEvent configFileChangeEvent) {
                //循环更新的属性
                for(ConfigChangeModel configChangeModel : configFileChangeEvent.getChanges()) {

                    if(ChangeTypeEnum.DELETE.equals(configChangeModel.getChangeType())){
                        //状态为删除时，删除属性
                        configMap.remove(configChangeModel.getPropertyName());
                    }else{
                        //状态为添加或修改时，更新属性
                        configMap.put(configChangeModel.getPropertyName(),configChangeModel.getNewValue());
                    }
                }
            }
        });
    }

    public List<String> getPathList() {
        return pathList;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }


    public static List<String> getList(String key){
        String value=configMap.get(key);
        List<String> list=new ArrayList<>();
        if(StringUtils.isBlank(value)){
            return list;
        }
        return Arrays.asList(value.split(","));
    }

    public static Integer getInt(String key){
        String value=configMap.get(key);
        Integer rs=0;
        if(StringUtils.isBlank(value)){
            return rs;
        }
        return Integer.parseInt(value);
    }


}
