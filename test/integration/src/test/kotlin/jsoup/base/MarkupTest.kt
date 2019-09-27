package jsoup.base

import org.apache.commons.io.FilenameUtils
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.junit.jupiter.api.Assertions.assertTrue

abstract class MarkupTest {

  val publishUrl get() = System.getProperty("test.publishUrl")

  fun get(path: String) = Jsoup.connect("$publishUrl$path").get()

  fun assertText(expectedPattern: String, actualText: String) {
    assertTrue(FilenameUtils.wildcardMatch(expectedPattern, actualText)) {
      "Text '$actualText' does not match wildcard pattern '$expectedPattern'"
    }
  }

  fun Elements.textMatches(expectedPattern: String) {
    assertText(expectedPattern, text().trim())
  }

  fun Elements.attributeMatches(attribute: String, expectedPattern: String) {
    assertText(expectedPattern, attr(attribute).trim())
  }
}

