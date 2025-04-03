package com.example.CalendarDevelop.cys.config.filter;

import com.example.CalendarDevelop.cys.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 인증 필터 클래스
 * 요청에 대한 인증 여부를 검사하는 필터
 * 인증이 필요하지 않은 경로는 제외하고, 나머지 경로는 인증 후 접근 가능
 */
@Slf4j
public class AuthenticationFilter implements Filter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 인증이 필요하지 않은 경로 목록
    private final List<String> excludedPaths = Arrays.asList(
            "/members", // POST - 회원가입
            "/login",   // POST - 로그인
            "/logout"   // GET - 로그아웃
    );

    /**
     * 필터링 수행 메서드
     * 요청 경로에 따라 인증 여부를 검사하고, 인증되지 않은 요청은 401 응답을 반환
     * @param request 서블릿 요청
     * @param response 서블릿 응답
     * @param chain 필터 체인
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // 인증이 필요하지 않은 경로는 필터링 제외
        if (isExcludedPath(requestURI, httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        // 세션에서 사용자 ID 확인
        Long userId = AuthService.getLoggedInUserId(httpRequest.getSession(false));

        if (userId == null) {
            // 인증되지 않은 사용자 처리
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "UNAUTHORIZED");
            errorResponse.put("code", 401);
            errorResponse.put("message", "로그인이 필요한 서비스입니다.");

            httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return;
        }

        // 인증된 사용자는 요청 계속 진행
        chain.doFilter(request, response);
    }

    /**
     * 인증이 필요하지 않은 경로인지 확인하는 메서드
     * @param requestURI 요청 URI
     * @param method HTTP 메서드
     * @return 인증이 필요하지 않은 경로인지 여부
     */
    private boolean isExcludedPath(String requestURI, String method) {
        if ("POST".equalsIgnoreCase(method) && pathMatcher.match("/members", requestURI)) {
            return true;
        }

        return excludedPaths.stream()
                .anyMatch(path -> pathMatcher.match(path, requestURI));
    }
}