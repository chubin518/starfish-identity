package org.starfish.identity.admin.shiro;

import org.apache.shiro.web.filter.PathMatchingFilter;

public class URLPathMatchingFilter extends PathMatchingFilter {

//    @Autowired
//    PermissionService permissionService;
//
//    @Override
//    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
//            throws Exception {
//        String requestURI = getPathWithinApplication(request);
//
//        System.out.println("requestURI:" + requestURI);
//
//        Subject subject = SecurityUtils.getSubject();
//        // 如果没有登录，就跳转到登录页面
//        if (!subject.isAuthenticated()) {
//            WebUtils.issueRedirect(request, response, "/login");
//            return false;
//        }
//
//        // 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
//        boolean needInterceptor = permissionService.needInterceptor(requestURI);
//        if (!needInterceptor) {
//            return true;
//        } else {
//            boolean hasPermission = false;
//            String userName = subject.getPrincipal().toString();
//            Set<String> permissionUrls = permissionService.listPermissionURLs(userName);
//            for (String url : permissionUrls) {
//                // 这就表示当前用户有这个权限
//                if (url.equals(requestURI)) {
//                    hasPermission = true;
//                    break;
//                }
//            }
//
//            if (hasPermission)
//                return true;
//            else {
//                UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");
//
//                subject.getSession().setAttribute("ex", ex);
//
//                WebUtils.issueRedirect(request, response, "/unauthorized");
//                return false;
//            }
//
//        }
//
//    }
}
