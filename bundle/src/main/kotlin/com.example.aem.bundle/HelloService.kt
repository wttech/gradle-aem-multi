package com.example.aem.bundle

import com.example.aem.bundle.services.posts.PostsService
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
    }

    @Reference
    private lateinit var postsService: PostsService

    @Activate
    protected fun activate() {
        val posts = postsService.posts

        LOG.info("Posts service contains knowledge of {} post(s).", posts.size)
    }

}
