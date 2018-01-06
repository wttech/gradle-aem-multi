package com.company.aem.example.core.components.page

import com.company.aem.example.common.JsonUtils
import com.day.cq.commons.jcr.JcrConstants
import com.day.cq.wcm.api.NameConstants
import com.google.common.net.MediaType
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingAllMethodsServlet
import org.osgi.service.component.annotations.Component
import javax.servlet.Servlet

/**
 * @see <http://[host]:[port]/content/we-retail/us/en.details.json>
 */
@Component(
  service = arrayOf(Servlet::class),
  property = arrayOf(
    "sling.servlet.extensions=json",
    "sling.servlet.selectors=details",
    "sling.servlet.resourceTypes=${NameConstants.NT_PAGE}"
  )
)
class PageDetailsServlet : SlingAllMethodsServlet() {

  override fun doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse) {
    val page = request.resource.getChild(JcrConstants.JCR_CONTENT)?.adaptTo(PageModel::class.java)

    response.contentType = MediaType.JSON_UTF_8.toString()
    response.writer.use { it.write(JsonUtils.GSON.toJson(page)) }
  }

}
