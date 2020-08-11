package jsoup.test

import jsoup.base.MarkupTest
import org.junit.jupiter.api.Test

class HomeTest : MarkupTest() {

  @Test
  fun shouldHaveElementsInMarkup() {
    get("/").apply {
      select(".title").first().apply {
        select(".cmp-title__text").textMatches("Example")
      }
    }
  }
}
