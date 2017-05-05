package com.example.aem.bundle.services.posts

import com.example.aem.bundle.common.AppUtils
import com.example.aem.bundle.common.JsonUtils
import com.google.gson.reflect.TypeToken
import org.apache.felix.scr.annotations.Activate
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.Service
import java.util.*
import java.net.URL as Url

@Service(PostsService::class)
@Component(
        immediate = true,
        metatype = true,
        label = "${AppUtils.LABEL_PREFIX} Posts Service",
        description = "Serves post data from external data source."
)
class PostsService {

    companion object {
        @Property(
                name = URL,
                value = "https://jsonplaceholder.typicode.com/posts",
                label = "Data source URL for posts.",
                description = "Endpoint must return JSON with list of objects containing 'userId', 'id', 'title' and 'body'."
        )
        const val URL = "postsUrl"
    }

    private lateinit var props: Map<String, Any>

    @Activate
    private fun activate(props: Map<String, Any>) {
        this.props = props
    }

    private val url: String
        get() = props[URL] as String

    val posts: List<Post> by lazy {
        JsonUtils.GSON.fromJson<List<Post>>(
                Url(url).openStream().bufferedReader().use { it.readText() },
                object : TypeToken<List<Post>>() {}.type
        )
    }

    fun randomPosts(count: Int): List<Post> {
        val shuffled = posts.toMutableList()
        Collections.shuffle(shuffled)

        return shuffled.subList(0, count)
    }

}