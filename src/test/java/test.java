import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.highmind_Tms.service.HolidayService;

import cn.hutool.core.date.DateUtil;

/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     
 *
 *    Filename:    test.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年5月15日 下午3:49:38
 *
 *    Revision:
 *
 *    2019年5月15日 下午3:49:38
 *        - first revision
 *
 *****************************************************************/

/**
 * @ClassName test
 * @Description TODO
 * @author 61430
 * @Date 2019年5月15日 下午3:49:38
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) 
public class test {
    @Autowired
    private HolidayService holidayService;
    @Test
    public void test1() {
        File file=new File("D:\\document\\qqfile\\614303219\\FileRecv\\节假日.xls");
        int result=holidayService.readAndInsertHoliday(file,1L);
        System.out.println("结构"+result);
        
    }
    @Test
    public void test0() {
        Date d;
 
         
        String str1 = "2019/6/1";
        d = DateUtil.parse(str1);
        p1("字符串",str1, "日期格式",d);
         
    
    }
    private void p1(String type1, Object value1, String type2, Object value2) {
        System.out.printf("\t%s的:\t\"%s\" %n\t被转换为----->%n\t%s的 :\t\"%s\" %n%n",type1,value1, type2, value2);
    }
}
