package jsoup.test

import jsoup.base.MarkupTest
import org.junit.jupiter.api.Test

class HomeTest : MarkupTest() {

  @Test
  fun shouldHaveElementsInMarkup() {
    get("/").apply {
      select(".jumbotron").apply {
        select("h1").textMatches("English US")
        select("p").textMatches("Home Page of English US market")
      }

      select(".card").apply {
        select(".card-header").textMatches("AEM application built by")
        select(".card-body .logo").attributeMatches("src","/etc.clientlibs/example/sites/clientlibs/page/publish/resources/img/logo.png")
      }
    }
  }
}
