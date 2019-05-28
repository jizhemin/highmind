package com.highmind_Tms.tool;

import java.lang.reflect.Field;
import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

/**
 * @ClassName OverIsMergeablePlugin
 * @Description 避免生成重复代码
 * @author 61430
 * @Date 2019年4月3日 上午10:25:57
 * @version 1.0.0
 */
public class OverIsMergeablePlugin extends PluginAdapter{
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }
 
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
