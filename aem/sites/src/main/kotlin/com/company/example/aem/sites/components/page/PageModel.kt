package com.company.example.aem.sites.components.page

import com.company.example.aem.sites.services.posts.Post
import com.company.example.aem.sites.services.posts.PostsService
import com.day.cq.commons.LanguageUtil
import com.day.cq.commons.jcr.JcrConstants
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.OSGiService
import org.apache.sling.models.annotations.injectorspecific.Self
import java.io.Serializable
import java.util.*
import javax.annotation.PostConstruct
import javax.inject.Inject
import javax.inject.Named

@Model(
  adaptables = [Resource::class],
  defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
class PageModel : Serializable {

  @Inject
  @field:Named(JcrConstants.JCR_TITLE)
  lateinit var title: String

  @Inject
  @field:Named(JcrConstants.JCR_DESCRIPTION)
  var description: String? = null

  @Inject
  @field:Named(JcrConstants.JCR_CREATED)
  lateinit var created: GregorianCalendar

  @Inject
  @field:Named(JcrConstants.JCR_CREATED_BY)
  lateinit var userId: String

  @Transient
  @field:Self
  private lateinit var resource: Resource

  @Transient
  @OSGiService
  private lateinit var postsService: PostsService

  lateinit var posts: List<Post>

  lateinit var languageCode: String

  @PostConstruct
  fun construct() {
    this.languageCode = determineLanguageCode()
    this.posts = postsService.randomPosts(5)
  }

  private fun determineLanguageCode(): String {
//    val languagePath = LanguageUtil.getLanguageRoot(resource.path)
//    val languagePart = languagePath.substringAfterLast("/")

//    return LanguageUtil.getLanguage(languagePart).languageCode ?: "en"
    return "en"
  }

}
