package com.imooc.server.util;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.Map;

public class JwtTokenUtil {

    //签名私钥
    private String key="jwt-key";   //token私匙，自定义就可以
    //签名的失效时间
    //private Long ttl=360000L;       //token过期时间，单位毫秒，这里是6分钟
    private Long ttl=360000L;       //token过期时间，单位毫秒，这里是6分钟
    /**
     * 设置认证token
     * id:登录用户id
     * name：登录用户名
     * map:自定义的数据
     */
    public String createJwt(String id, String name, Map<String,Object> map) {
        //1.设置失效时间(毫秒)
        long now = System.currentTimeMillis();
        long exp = now + ttl;
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);
        //3.根据map设置claims
        for(Map.Entry<String,Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        //4.设置token过期时间
        jwtBuilder.setExpiration(new Date(exp));
        //5.创建token
        String token = jwtBuilder.compact();
        return token;
    }


    /**
     * 解析token字符串获取clamis
     */
    public Claims parseJwt(String token) {
        Claims claims = null;
        try{
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }
}