package org.starfish.identity.admin.shirojwt;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt 过滤器
 */
public class JwtAuthenticationFilter extends AccessControlFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        if (null != getSubject(request, response)
                && getSubject(request, response).isAuthenticated()) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (this.isJwtSubmission(request)) {
            //创建令牌
            AuthenticationToken token = createToken(request, response);
            try {
                Subject subject = getSubject(request, response);
                subject.login(token);
                return true;
            } catch (AuthenticationException e) {
                LOGGER.error(e.getMessage(), e);
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            }
        }
        return false;//打住，访问到此为止
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String token = "";
        if (request instanceof HttpServletRequest) {
            token = WebUtils.toHttp(request).getHeader(JwtConstants.Authorization);
        }
        LOGGER.info("createToken  ：" + token);
        return new JwtAuthenticationToken(token);
    }

    private boolean isJwtSubmission(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            String token = WebUtils.toHttp(request).getHeader(JwtConstants.Authorization);
            return !StringUtils.isEmpty(token);
        }
        return false;
    }
}
