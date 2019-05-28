/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind_Tms.exception
 *
 *    Filename:    AddException.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年5月21日 上午9:12:16
 *
 *    Revision:
 *
 *    2019年5月21日 上午9:12:16
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.exception;

/**
 * @ClassName AddException
 * @Description TODO
 * @author 61430
 * @Date 2019年5月21日 上午9:12:16
 * @version 1.0.0
 */
public class AddException extends Exception{
    /**
     * @Field @serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;
    public AddException(){
        
    }
    public AddException(String msg){
        super(msg);
    }
}
