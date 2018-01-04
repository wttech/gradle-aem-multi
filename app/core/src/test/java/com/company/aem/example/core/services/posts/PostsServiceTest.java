package com.company.aem.example.core.services.posts;

import static org.junit.Assert.assertEquals;

import io.wcm.testing.mock.aem.junit.AemContext;
import io.wcm.testing.mock.aem.junit.AemContextCallback;
import java.util.List;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.Rule;
import org.junit.Test;

public class PostsServiceTest {

  @Rule
  public AemContext context = new AemContext((AemContextCallback) context -> {
    context.registerInjectActivateService(new PostsService());
  }, ResourceResolverType.RESOURCERESOLVER_MOCK);

  @Test
  public void shouldFetchRandomPosts() {
    final PostsService postsService = context.getService(PostsService.class);
    final List<Post> posts = postsService.randomPosts(100);

    assertEquals(100, posts.size());
  }

}
