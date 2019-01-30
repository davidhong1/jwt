package com.yuan.jwt.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: mac
 * @date: 2019-01-21
 * @description: We should use OncePerRequestFilter since we are doing a database call, there is no point in doing this more than once
 */
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * 注意不要在这个方法里面直接return，要让filterChain.doFilter执行下去
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(httpServletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                // 传值给controller，controller可使用@PreAuthorize注解进行认证
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception ex) {
            log.info("ex.getMessage={}", ex.getMessage());
            // log.info("ex.value={}, ex.getMessage={}", ex.getHttpStatus().value(), ex.getMessage());
            // this is very important, since it guarantees the user is not authenticated at all
            // SecurityContextHolder.clearContext();
            // httpServletResponse.sendError(ex.getHttpStatus().value(), ex.getMessage());
            // return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
