package karate.feature

import com.intuit.karate.KarateOptions
import com.intuit.karate.Runner
import com.intuit.karate.junit5.Karate
import net.masterthought.cucumber.Configuration
import net.masterthought.cucumber.ReportBuilder
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfSystemProperty
import org.junit.jupiter.api.condition.EnabledIfSystemProperty
import java.io.File

@KarateOptions(tags = ["~@ignore"])
class FeatureRunner {

  @Karate.Test
  @DisabledIfSystemProperty(named = "test.parallel", matches = "true")
  fun testSequential() = Karate().tags("~@ignore").relativeTo(javaClass)

  @Test
  @EnabledIfSystemProperty(named = "test.parallel", matches = "true")
  fun testParallel() {
    Runner.parallel(javaClass, Runtime.getRuntime().availableProcessors()).apply {
      generateReport(File(reportDir))
      assertTrue(failCount == 0, errorMessages)
    }
  }

  private fun generateReport(reportDir: File) {
    val jsonFiles = (reportDir.listFiles { file: File -> file.extension == "json" } ?: arrayOf())
    val config = Configuration(reportDir.parentFile, "Integration Tests");

    ReportBuilder(jsonFiles.map { it.absolutePath }.toList(), config).generateReports();
  }

}
