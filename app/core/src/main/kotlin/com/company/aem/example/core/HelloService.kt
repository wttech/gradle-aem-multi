package com.company.aem.example.core

import com.company.aem.example.core.services.posts.PostsService
import org.hashids.Hashids
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Reference
import org.slf4j.LoggerFactory

/**
 * Note that the site configuration is injected, not retrieved.
 */
@Component(service = arrayOf(HelloService::class), immediate = true)
class HelloService {

  companion object {
    private val LOG = LoggerFactory.getLogger(HelloService::class.java)

    private val HASHER = Hashids()
  }

  @Reference
  private lateinit var postsService: PostsService

  @Activate
  fun activate() {
    val posts = postsService.posts

    LOG.info("Posts service contains knowledge of {} post(s).", posts.size)

    val timestamp = System.currentTimeMillis()
    val hashId = HASHER.encode(timestamp)

    LOG.info("Hash ID for current timestamp: $timestamp is '$hashId'")
  }

}
