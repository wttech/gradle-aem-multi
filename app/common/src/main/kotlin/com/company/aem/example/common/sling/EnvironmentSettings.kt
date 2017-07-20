package com.company.aem.example.common.sling

import org.apache.sling.settings.SlingSettingsService
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Reference

/**
 * Provides environment related data like AEM instance type.
 */
@Component(
  service = arrayOf(EnvironmentSettings::class),
  immediate = true
)
class EnvironmentSettings {

  companion object {
    const val MODE_AUTHOR = "author"

    const val MODE_PUBLISH = "publish"
  }

  @Reference
  private lateinit var settings: SlingSettingsService

  fun isMode(mode: String): Boolean {
    return settings.runModes.any { it.equals(mode, ignoreCase = true) }
  }

  val author: Boolean
    get() = isMode(MODE_AUTHOR)

  val publish: Boolean
    get() = isMode(MODE_PUBLISH)

}
