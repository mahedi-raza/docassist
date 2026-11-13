package com.docassist.api.filter;

import com.docassist.commons.constants.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final String MDC_KEY="correlationId";
    private static final Pattern VALID_ID = Pattern.compile("[A-Za-z0-9_-]{1,64}");

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp , FilterChain chain)
        throws ServletException, IOException {

        String correlationId = req.getHeader(Constants.CORRELATION_ID_HEADER);
        if (correlationId == null || !VALID_ID.matcher(correlationId).matches()) {
            correlationId = UUID.randomUUID().toString();
        }

        MDC.put(MDC_KEY, correlationId);
        try{
            resp.setHeader(Constants.CORRELATION_ID_HEADER, correlationId);
            chain.doFilter(req, resp);
        } finally {
            MDC.remove(MDC_KEY);
        }
    }





}
