rootProject.name = "example"

include(":env")

include(":app:aem:core")
include(":app:aem:ui.apps")
include(":app:aem:ui.content")
include(":app:aem:ui.frontend")
include(":app:aem:dispatcher")
include(":app:aem:migration")
include(":app:aem:all")

/*
include(":app:knotx:distribution") // fork of starterkit
include(":app:knotx:custom-module") // extra JAR building if needed by custom distribution
*/

include("test:functional")
include("test:integration")
include("test:performance")
