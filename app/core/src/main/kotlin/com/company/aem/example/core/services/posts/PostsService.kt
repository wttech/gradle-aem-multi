package com.company.aem.example.core.services.posts

import com.company.aem.example.common.JsonUtils
import com.google.gson.reflect.TypeToken
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.metatype.annotations.AttributeDefinition
import org.osgi.service.metatype.annotations.Designate
import org.osgi.service.metatype.annotations.ObjectClassDefinition
import java.util.*
import java.net.URL as Url

@Component(
  service = arrayOf(PostsService::class),
  immediate = true
)
@Designate(ocd = PostsService.Config::class)
class PostsService {

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

  private lateinit var config: Config

  @Activate
  fun activate(config: Config) {
    this.config = config
  }

  val posts: List<Post>
    get() {
      if (config.url.isNullOrBlank()) {
        return listOf()
      }

      return JsonUtils.GSON.fromJson<List<Post>>(
        Url(config.url).openStream().bufferedReader().use { it.readText() },
        object : TypeToken<List<Post>>() {}.type
      )
    }

  fun randomPosts(count: Int): List<Post> {
    if (count <= 0 || count > posts.size) {
      return listOf()
    }

    val shuffled = posts.toMutableList()
    Collections.shuffle(shuffled)

    return shuffled.subList(0, count)
  }

}
