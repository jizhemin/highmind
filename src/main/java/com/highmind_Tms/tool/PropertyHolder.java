package com.highmind_Tms.tool;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropertyHolderLazy
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author 61430
 * @Date 2019年3月21日 上午5:52:34
 * @version 1.0.0
 */
public class PropertyHolder {
    private static Properties prop= null;
    public static  Properties getProps() throws IOException {
        if(prop==null) {
            synchronized(PropertyHolder.class) {
                if(prop==null) {
                    prop= new Properties();
                    prop.load(PropertyHolder.class.getClassLoader().getResourceAsStream("config.properties"));
                }
            }
        }
         return prop;

    }
}
