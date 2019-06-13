package org.starfish.identity.utils;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一响应模型
 */
@Getter
@Setter
public class ResponseBean<E> implements Serializable {
  private static final long serialVersionUID = 1L;
  private String status;
  private String message;
  private E data;

  public ResponseBean(String status, String message, E data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public static <E> ResponseBean<E> success(E body) {
    return new ResponseBean<>(StandardCode.SUCCESS.toString(), "", body);
  }

  public static <E> ResponseBean<E> notFound(String message) {
    return new ResponseBean<>(StandardCode.NOT_FOUND.toString(), message, null);
  }

  public static <E> ResponseBean<E> conflict(String message) {
    return new ResponseBean<>(StandardCode.CONFLICT.toString(), message, null);
  }

  public static <E> ResponseBean<E> locked(String message) {
    return new ResponseBean<>(StandardCode.LOCKED.toString(), message, null);
  }

  public static <E> ResponseBean<E> unsupportedMediaType(String message) {
    return new ResponseBean<>(StandardCode.UNSUPPORTED_MEDIA_TYPE.toString(), message, null);
  }

  public static <E> ResponseBean<E> badRequest(String message) {
    return new ResponseBean<>(StandardCode.BAD_REQUEST.toString(), message, null);
  }

  public static <E> ResponseBean<E> forbidden(String message) {
    return new ResponseBean<>(StandardCode.FORBIDDEN.toString(), message, null);
  }

  public static <E> ResponseBean<E> unAuthorized(String message) {
    return new ResponseBean<>(StandardCode.UNAUTHORIZED.toString(), message, null);
  }

  public static <E> ResponseBean<E> serverError(String message) {
    return new ResponseBean<>(StandardCode.INTERNAL_SERVER_ERROR.toString(), message, null);
  }

  public static <E> ResponseBean<E> notImplemented(String message) {
    return new ResponseBean<>(StandardCode.NOT_IMPLEMENTED.toString(), message, null);
  }

  public static <E> ResponseBean<E> unknown(String message) {
    return new ResponseBean<>(StandardCode.UNKNOWN.toString(), message, null);
  }

  public static <E> ResponseBean<E> customFail(String code, String message) {
    return new ResponseBean<>(code, message, null);
  }
}