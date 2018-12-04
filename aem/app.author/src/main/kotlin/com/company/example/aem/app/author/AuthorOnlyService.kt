package com.company.example.aem.app.author

import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.slf4j.LoggerFactory

@Component(
  immediate = true,
  service = [AuthorOnlyService::class]
)
class AuthorOnlyService {

  @Activate
  fun activate() {
    LOG.info("Starting author only service.")
  }

  fun deactivate() {
    LOG.info("Stopping author only service.")
  }

  companion object {
      val LOG = LoggerFactory.getLogger(AuthorOnlyService::class.java)
  }

}
