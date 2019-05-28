/******************************************************************
 *
 *    Java Lib For Android, Powered By personal.
 *
 *    Copyright (c) 2001-2014 Digital Telemedia Co.,Ltd
 *    http://www.d-telemedia.com/
 *
 *    Package:     com.highmind.tool
 *
 *    Filename:    JwtUtil.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     61430
 *
 *    @version:    1.0.0
 *
 *    Create at:   2019年4月17日 上午10:11:04
 *
 *    Revision:
 *
 *    2019年4月17日 上午10:11:04
 *        - first revision
 *
 *****************************************************************/
package com.highmind_Tms.tool;

import java.util.Date;
import java.util.HashMap;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @ClassName JwtUtil
 * @Description TODO
 * @author 61430
 * @Date 2019年4月17日 上午10:11:04
 * @version 1.0.0
 */
public class JwtUtil {
    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME=15*60*1000;
    /**
     * token私钥
     */
    private static final String TOKEN_SECRET="s1JwxEKV1Rdod7vMXqJB";
    private static final  String JWT_TYP = "JWT";
    private static final  String JWT_ALG = "HS256";
    
    public static String sign(Long userId,String loginId,String password) {       
        // 过期时间
        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
        // 私钥及加密算法
        Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头部信息
        HashMap<String, Object> header=new HashMap<String ,Object>();
        header.put("typ",JWT_TYP);
        header.put("alg",JWT_ALG);
        return JWT.create()
                .withHeader(header)
                .withClaim("userId", userId)
                .withClaim("username", loginId)
                .withClaim("password", password)
                .withExpiresAt(date)
                .sign(algorithm);
    }
    /**
     * 验证token
     * @Description
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        }catch(Exception e) {
            return false;
        }
    }
    /**
     * 根据token获取用户id
     * @Description
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt=JWT.decode(token);
            return jwt.getClaim("userId").asLong();
        }catch (JWTDecodeException e) {
            return null;
        }
        
        
    }
}
