rootProject.name = "example"

include("aem")
include("aem:common")
include("aem:sites")
include("aem:site.init")
include("aem:site.demo")
include("aem:migration")

include("aem:assembly:app")
include("aem:assembly:content")
include("aem:assembly:full")

include("test:functional")
include("test:integration")
