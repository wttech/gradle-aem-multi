package com.company.aem.example.core.components.page

import com.day.cq.wcm.api.NameConstants
import com.company.aem.example.common.JsonUtils
import com.day.cq.commons.jcr.JcrConstants
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingAllMethodsServlet
import org.osgi.service.component.annotations.Component
import javax.servlet.Servlet

/**
 * @see <http://localhost:4502/content/geometrixx-outdoors/en.details.json>
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
        val page = request.resource.getChild(JcrConstants.JCR_CONTENT).adaptTo(PageModel::class.java)
        val json = JsonUtils.GSON.toJson(page)

        response.writer.use { it.write(json) }
    }

}