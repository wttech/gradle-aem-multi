package com.example.aem.bundle;

import com.example.aem.bundle.services.posts.Post;
import com.example.aem.bundle.services.posts.PostsService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Note that the site configuration is injected, not retrieved.
 */
@Component(service = HelloService.class, immediate = true)
public class HelloService {

    private static final Logger LOG = LoggerFactory.getLogger(HelloService.class);

    private PostsService postsService;

    @Reference
    void setPostsService(PostsService postsService) {
        this.postsService = postsService;
    }

    @Activate
    protected void activate() {
        final List<Post> posts = postsService.getPosts();

        LOG.info("Posts service contains knowledge of {} post(s).", posts.size());
    }

}
