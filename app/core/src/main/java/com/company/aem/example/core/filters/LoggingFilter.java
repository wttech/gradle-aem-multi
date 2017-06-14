package com.company.aem.example.core.filters;

import org.apache.sling.api.SlingHttpServletRequest;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Simple servlet filter component that logs incoming requests.
 */
@Component(
        service = Filter.class,
        property = {
                "sling.filter.scope=REQUEST",
                "service.ranking=-700"
        }
)
public class LoggingFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        // because this is a Sling filter, we can be assured the the request
        // is an instance of SlingHttpServletRequest
        SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
        logger.debug("request for {}, with selector {}",
                slingRequest.getRequestPathInfo().getResourcePath(),
                slingRequest.getRequestPathInfo().getSelectorString());

        chain.doFilter(request, response);
    }

    public void destroy() {
        // intentionally empty
    }

}
