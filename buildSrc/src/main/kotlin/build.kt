object Build {

  const val AEM_VERSION = "6.5.0"

  const val KOTLIN_VERSION = "1.3.50"
}

class LighthouseConfig {

  val suites: List<Suite> = listOf()

  class Suite {

    lateinit var name: String

    val paths: List<String> = listOf()

    val baseUrls: List<String> = listOf()

    val args: List<String> = listOf()
  }
}

