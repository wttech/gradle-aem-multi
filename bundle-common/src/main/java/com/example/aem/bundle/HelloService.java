package com.example.aem.bundle;

import com.example.aem.bundle.services.posts.Post;
import com.example.aem.bundle.services.posts.PostsService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Note that the site configuration is injected, not retrieved.
 */
@Service(HelloService.class)
@Component(immediate = true)
public class HelloService {

    private static final Logger LOG = LoggerFactory.getLogger(HelloService.class);

    @Reference
    private PostsService postsService;

    @Activate
    protected void activate() {
        final List<Post> posts = postsService.getPosts();

        LOG.info("Posts service contains knowledge of {} post(s).", posts.size());
    }

}
