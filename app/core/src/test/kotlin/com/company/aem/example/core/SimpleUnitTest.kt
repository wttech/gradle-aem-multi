package com.company.aem.example.core

import com.company.aem.example.core.services.posts.PostsService
import org.junit.Assert.*
import org.junit.Rule
import io.wcm.testing.mock.aem.junit.AemContext
import org.apache.sling.testing.mock.sling.ResourceResolverType

import org.junit.Test

class SimpleUnitTest {

  @Rule
  @JvmField
  val context = AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK)

    @Test
    fun someTest() {
        context.registerInjectActivateService(PostsService())
    }

}
