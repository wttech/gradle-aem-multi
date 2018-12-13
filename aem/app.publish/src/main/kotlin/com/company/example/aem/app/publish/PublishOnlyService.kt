package com.company.example.aem.app.publish

import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.slf4j.LoggerFactory

@Component(
  immediate = true,
  service = [PublishOnlyService::class]
)
class PublishOnlyService {

  @Activate
  fun activate() {
    LOG.info("Starting publish only service.")
  }

  fun deactivate() {
    LOG.info("Stopping publish only ser vice.")
  }

  companion object {
      val LOG = LoggerFactory.getLogger(PublishOnlyService::class.java)
  }

}
