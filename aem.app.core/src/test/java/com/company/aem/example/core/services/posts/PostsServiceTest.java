package com.company.aem.example.core.services.posts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import java.util.List;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class PostsServiceTest {

  private AemContext context;

  @BeforeEach
  void setUp() {
    context = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);
    context.registerInjectActivateService(new PostsService());
  }

  @Test
  void shouldFetchRandomPosts() {
    final PostsService postsService = context.getService(PostsService.class);
    final List<Post> posts = postsService.randomPosts(100);

    assertEquals(100, posts.size());
  }

}
