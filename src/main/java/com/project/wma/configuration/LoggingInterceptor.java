package com.project.wma.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class LoggingInterceptor implements AsyncHandlerInterceptor {

    private static final String REQUEST_ATTRIBUTE_START_TIME = LoggingInterceptor.class.getName() + ".requestStart";

    private static final String REQUEST_URL_MDC_KEY = "url";
    private static final String REQUEST_METHOD_MDC_KEY = "reqMethod";
    private static final String REQUEST_DURATION = "duration";
    private static final String REQUEST_STATUS = "status";

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        recordStartTime(request);
        initializeMDC(request);

        log.info("Incoming Request from user for Path {} and Method {}", request.getServletPath(), request.getMethod());

        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            @Nullable Exception ex
    ) {
        var duration = calculateDuration(request);
        enrichMDC(response, duration);

        log.info("Return status {}", response.getStatus());

        clearMDC();
    }

    private void initializeMDC(HttpServletRequest request) {
        MDC.put(REQUEST_URL_MDC_KEY, request.getRequestURL().toString());
        MDC.put(REQUEST_METHOD_MDC_KEY, request.getMethod());
    }

    private void enrichMDC(HttpServletResponse response, long duration) {
        MDC.put(REQUEST_DURATION, String.valueOf(duration));
        MDC.put(REQUEST_STATUS, String.valueOf(response.getStatus()));
    }

    private void clearMDC() {
        MDC.remove(REQUEST_METHOD_MDC_KEY);
        MDC.remove(REQUEST_URL_MDC_KEY);
        MDC.remove(REQUEST_DURATION);
        MDC.remove(REQUEST_STATUS);
    }

    private void recordStartTime(HttpServletRequest request) {
        request.setAttribute(REQUEST_ATTRIBUTE_START_TIME, System.currentTimeMillis());
    }

    private long calculateDuration(HttpServletRequest request) {
        long startTimeInMillis = (Long) request.getAttribute(REQUEST_ATTRIBUTE_START_TIME);
        long currentTimeInMillis = System.currentTimeMillis();

        return currentTimeInMillis - startTimeInMillis;
    }
}
