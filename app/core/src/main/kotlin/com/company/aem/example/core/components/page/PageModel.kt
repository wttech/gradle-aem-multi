package com.company.aem.example.core.components.page

import com.day.cq.commons.jcr.JcrConstants
import com.company.aem.example.core.services.posts.Post
import com.company.aem.example.core.services.posts.PostsService
import com.day.cq.commons.Language
import com.day.cq.commons.LanguageUtil
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.OSGiService
import org.apache.sling.models.annotations.injectorspecific.Self
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import java.io.Serializable
import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Named

@Model(
        adaptables = arrayOf(Resource::class),
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
class PageModel : Serializable {

    companion object {
        val LOG = LoggerFactory.getLogger(PageModel::class.java)
    }

    @Inject
    @field:Named("${JcrConstants.JCR_CONTENT}/${JcrConstants.JCR_TITLE}")
    lateinit var title: String

    @Inject
    @field:Named(JcrConstants.JCR_CREATED)
    lateinit var created: GregorianCalendar

    @Inject
    @field:Named(JcrConstants.JCR_CREATED_BY)
    lateinit var userId: String

    @Transient
    @Self
    private lateinit var resource: Resource

    @Transient
    @OSGiService
    private lateinit var postsService: PostsService

    private lateinit var posts: List<Post>

    @PostConstruct
    protected fun construct() {
        LOG.debug("Reading random 5 posts for page '${resource.path}'")

        posts = postsService.randomPosts(5)
    }

    val language: Language by lazy {
        LanguageUtil.getLanguage(resource.path)
    }

    val currentDate = DateTime()

}