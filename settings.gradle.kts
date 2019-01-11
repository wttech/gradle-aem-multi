rootProject.name = "example"

include("aem")
include("aem:common")
include("aem:sites")
include("aem:content.init")
include("aem:content.demo")
include("aem:migration")

include("aem:assembly:app")
include("aem:assembly:content")
include("aem:assembly:full")

include("test:functional")
include("test:integration")
