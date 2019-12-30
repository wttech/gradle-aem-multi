package com.company.example.aem.sites.services.posts

import com.company.example.aem.common.JsonUtils
import com.google.gson.reflect.TypeToken
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.metatype.annotations.AttributeDefinition
import org.osgi.service.metatype.annotations.Designate
import org.osgi.service.metatype.annotations.ObjectClassDefinition
import org.slf4j.LoggerFactory
import java.net.URL as Url

@Component(
  service = [(PostsService::class)],
  immediate = true
)
@Designate(ocd = PostsService.Config::class)
class PostsService {

  private lateinit var config: Config

  @Activate
  fun activate(config: Config) {
    this.config = config
  }

  val posts: List<Post>
    get() {
      if (config.url.isNullOrBlank()) {
        LOG.error("Cannot read posts as of URL is not configured!")
        return listOf()
      }

      return try {
        JsonUtils.GSON.fromJson<List<Post>>(
          Url(config.url).openStream().bufferedReader().use { it.readText() },
          object : TypeToken<List<Post>>() {}.type
        )
      } catch (e: Exception) {
        LOG.error("Cannot read posts! Cause: ${e.message}", e)
        listOf()
      }
    }

  fun randomPosts(count: Int): List<Post> {
    if (count <= 0 || count > posts.size) {
      return listOf()
    }

    val shuffled = posts.toMutableList()
    shuffled.shuffle()

    return shuffled.subList(0, count)
  }

  @ObjectClassDefinition(
    name = "Posts Service Config",
    description = "Customize endpoint URL for service which is returning posts"
  )
  annotation class Config(
    @get:AttributeDefinition(
      name = "Data source URL for posts.",
      description = "Endpoint must return JSON with list of objects containing 'userId', 'id', 'title' and 'body'."
    ) val url: String = "https://jsonplaceholder.typicode.com/posts"
  )

  companion object {
    private val LOG = LoggerFactory.getLogger(PostsService::class.java)
  }
}
