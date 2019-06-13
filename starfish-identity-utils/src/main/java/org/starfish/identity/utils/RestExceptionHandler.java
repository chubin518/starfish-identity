package org.starfish.identity.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

  /**
   * shiro 认证授权异常
   */
  @ExceptionHandler(ShiroException.class)
  public ResponseBean<Object> handle401(ShiroException e) {
    LOGGER.error("UNAUTHORIZED", e);
    return ResponseBean.unAuthorized(e.getMessage());
  }

  /**
   * 参数验证异常
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseBean<Object> validate(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();
    if (bindingResult.hasErrors()) {
      FieldError error = bindingResult.getFieldError();
      if (null != error) {
        return ResponseBean.badRequest(error.getDefaultMessage());
      }
    }
    return ResponseBean.badRequest("参数有误");
  }

  /**
   * 其他异常
   * 
   * @param request
   * @param ex
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResponseBean<Object> exception(HttpServletRequest request, Throwable ex) {
    LOGGER.error("UNAUTHORIZED", ex);
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    return ResponseBean.customFail(statusCode.toString(), ex.getMessage());
  }
}