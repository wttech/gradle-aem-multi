package com.example.aem.bundle.components.page

import com.day.cq.wcm.api.NameConstants
import com.example.aem.bundle.common.JsonUtils
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingAllMethodsServlet

/**
 * @see <http://localhost:4502/content/geometrixx-outdoors/en.details.json>
 */
@SlingServlet(
        resourceTypes = arrayOf(NameConstants.NT_PAGE),
        selectors = arrayOf("details"),
        extensions = arrayOf("json")
)
class PageDetailsServlet : SlingAllMethodsServlet() {

    override fun doGet(request: SlingHttpServletRequest, response: SlingHttpServletResponse) {
        val page = request.resource.adaptTo(PageModel::class.java)
        val json = JsonUtils.GSON.toJson(page)

        response.writer.use { it.write(json) }
    }

}