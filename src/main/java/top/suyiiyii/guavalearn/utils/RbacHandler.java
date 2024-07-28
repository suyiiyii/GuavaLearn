package top.suyiiyii.guavalearn.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.suyiiyii.guavalearn.dto.JwtData;
import top.suyiiyii.guavalearn.service.RbacService;
import top.suyiiyii.guavalearn.utils.exception.BusinessException;
import top.suyiiyii.guavalearn.utils.result.Code;


@Slf4j
@Component
public class RbacHandler implements HandlerInterceptor {

    private final RbacService rbacService;
    @Value("${jwt.secret}")
    String secret;

    ObjectMapper mapper = new ObjectMapper();

    public RbacHandler(RbacService rbacService) {
        this.rbacService = rbacService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("/login")) {
            return true;
        }
        if (request.getRequestURI().contains("/register")) {
            return true;
        }
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            throw new BusinessException(Code.UNAUTHORIZED_MISS_TOKEN);
        }
        String token = authorization.split(" ")[1];
        String dataStr = JwtUtils.parseToken(token, secret);
        JwtData jwtData = mapper.readValue(dataStr, JwtData.class);
        String username = jwtData.getUsername();
        log.info("User: " + dataStr + " is requesting: " + request.getRequestURI());
        if (!rbacService.checkPermission(username, handler.toString())) {
            throw BusinessException.of(Code.FORBIDDEN_USER_HAVE_NO_PERMISSION);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
