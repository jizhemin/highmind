package com.highmind_Tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName PageController
 * @Description TODO
 * @author 61430
 * @Date 2019年4月15日 上午9:12:41
 * @version 1.0.0
 */
@Controller
public class PageController {
    @RequestMapping(value="/demo",method=RequestMethod.GET) 
    public String demo(){
        return "demo";
    }
}
