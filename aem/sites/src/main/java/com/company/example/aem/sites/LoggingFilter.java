package com.company.example.aem.sites;

import com.company.example.aem.common.servlet.SlingFilter;
import com.company.example.aem.common.sling.EnvironmentSettings;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple servlet filter component that logs incoming requests only on author instances.
 * <p>
 * TODO Remember to delete that component, because it is only for demonstrating purposes.
 */
@Component(
  immediate = true,
  service = Filter.class,
  property = {
    "sling.filter.scope=REQUEST",
    "service.ranking=-700"
  }
)
public class LoggingFilter extends SlingFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

  @Reference
  private EnvironmentSettings settings;

  @Override
  public void doFilter(SlingHttpServletRequest request, SlingHttpServletResponse response, FilterChain chain)
    throws IOException, ServletException {
    if (settings.getAuthor()) {
      LOGGER.info("Request for '{}', with selector '{}'",
        request.getRequestPathInfo().getResourcePath(),
        request.getRequestPathInfo().getSelectorString()
      );
    }

    chain.doFilter(request, response);
  }
}
