package org.starfish.identity.admin.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * jwt token 工具类
 */
public class JwtUtils {

  /**
   * token 过期时间
   */
  private static final long EXPIRE_TIME = 5 * 60 * 1000;

  /**
   * 校验 jwt token是否正确
   * 
   * @param token
   * @param account
   * @param password
   * @return
   */
  public static boolean verify(String token, String account, String password) {
    try {
      // 根据密码生成JWT校验器
      Algorithm algorithm = Algorithm.HMAC256(password);
      JWTVerifier verifier = JWT.require(algorithm).withClaim("account", account).build();
      // 校验token
      DecodedJWT jwt = verifier.verify(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 无需secret解密获取token中的信息
   * 
   * @param token
   * @return
   */
  public static String getInfoToken(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim("account").asString();
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 生成token信息
   * 
   * @param account
   * @param password
   * @return
   */
  public static String create(String account, String password) {
    Date date = new Date(System.currentTimeMillis());
    Algorithm algorithm = Algorithm.HMAC256(password);
    return JWT.create().withClaim("account", account).withExpiresAt(date).sign(algorithm);
  }
}