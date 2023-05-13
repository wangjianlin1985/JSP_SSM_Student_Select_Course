// 
// 
// 

package com.cms.intercept;

import org.springframework.web.servlet.ModelAndView;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import com.cms.entity.Auth;
import com.cms.entity.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

public class SecurityInterceptor implements HandlerInterceptor
{
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        final HttpSession session = request.getSession();
        final User user = (User)session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/");
            return false;
        }
        final String url = request.getRequestURI();
        final List<Auth> list = user.getUrlList();
        for (final Auth auth : list) {
            if (url.indexOf(auth.getUrl()) >= 0) {
                return true;
            }
        }
        response.sendRedirect(String.valueOf(request.getContextPath()) + "/404");
        return false;
    }
    
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
    }
    
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
    }
}
