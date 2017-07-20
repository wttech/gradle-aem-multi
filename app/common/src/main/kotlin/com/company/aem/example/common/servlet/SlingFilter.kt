package com.company.aem.example.common.servlet

import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import java.io.IOException
import javax.servlet.*

/**
 * Purpose of this class is to reduce boilerplate code in all Sling filters (casts in these classes are just boring :)
 */
abstract class SlingFilter : Filter {

  override fun destroy() {
    // intentionally empty
  }

  override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
    if (request is SlingHttpServletRequest && response is SlingHttpServletResponse) {
      doFilter(request, response, chain)
    } else {
      doFilter(request, response, chain)
    }
  }

  @Throws(IOException::class, ServletException::class)
  abstract fun doFilter(request: SlingHttpServletRequest, response: SlingHttpServletResponse, chain: FilterChain)

  override fun init(p0: FilterConfig?) {
    // intentionally empty
  }
}
